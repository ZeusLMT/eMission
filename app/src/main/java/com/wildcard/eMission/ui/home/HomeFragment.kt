package com.wildcard.eMission.ui.home

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.wildcard.eMission.ActivityViewModel
import com.wildcard.eMission.EmissionApplication
import com.wildcard.eMission.R
import com.wildcard.eMission.Utils
import com.wildcard.eMission.databinding.FragmentHomeBinding
import com.wildcard.eMission.model.Challenge
import com.wildcard.eMission.model.ChallengePack
import com.wildcard.eMission.model.CompleteStatus
import timber.log.Timber
import java.util.*

class HomeFragment : Fragment(), ChallengesListAdapter.ChallengesListListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private val activityViewModel: ActivityViewModel by activityViewModels()
    private lateinit var challengesListAdapter: ChallengesListAdapter
    /** Handler to run tests in the background */
    private var handler: Handler? = null
    private var handlerThread: HandlerThread? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.apply {
            itemTextColor = context?.getColorStateList(R.color.nav_item_color_state_list_1)
            itemIconTintList = context?.getColorStateList(R.color.nav_item_color_state_list_1)
        }

        activityViewModel.userDataUpdated.observe(this, Observer {
            homeViewModel.carbonSaved.value = activityViewModel.user.carbonSaved.toInt()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupChallengesList()

        if (homeViewModel.todayChallenges.value.isNullOrEmpty() && !(requireActivity().getSharedPreferences(
                EmissionApplication.PREF_NAME,
                Context.MODE_PRIVATE
            ).getBoolean(EmissionApplication.PREF_ONBOARDING, true))
        ) {
            generateTodayChallenges()
        }
    }

    override fun onResume() {
        super.onResume()
        setupActionBar()

        if (handler == null) {
            handlerThread = HandlerThread("inference")
            handlerThread?.start()
            handler = Handler(handlerThread?.looper!!)
        }
    }

    override fun onPause() {
        handlerThread?.quitSafely()
        try {
            handlerThread?.join()
            handlerThread = null
            handler = null
        } catch (e: InterruptedException) {
            Timber.e(e, "Exception!")
        }

        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @Synchronized
    private fun runInBackground(r: Runnable) {
        if (handler != null) {
            Timber.d("Handler is not null")
            handler?.post(r)
        } else {
            Timber.d("Handler is null")
            handlerThread = HandlerThread("inference")
            handlerThread?.start()
            handler = Handler(handlerThread?.looper!!)
            handler?.post(r)
        }
    }

    //Implement Challenges List Adapter Listener's functions
    override fun onCheckpointSelected(challenge: Challenge, position: Int) {
        val todayChallenges = homeViewModel.todayChallenges.value
        if (todayChallenges!!.contains(challenge)) {
            challenge.status = when {
                (challenge.status == CompleteStatus.UNSTARTED && !challenge.singleTask) -> CompleteStatus.ONGOING
                else -> CompleteStatus.COMPLETE
            }
        }
        challengesListAdapter.notifyItemChanged(position)

        activityViewModel.updateUserData { user ->
            user.carbonSaved += challenge.points
            user.rewardPoints += challenge.points
        }

        homeViewModel.carbonSaved.value = activityViewModel.user.carbonSaved.toInt()

        if (challenge.status == CompleteStatus.COMPLETE) {
            activityViewModel.updateUserData { user ->
                user.completed_challenges.add(challenge)
            }
        }

        if (todayChallenges.all { it.status == CompleteStatus.COMPLETE }) {
            Timber.d("Completed all challenges")
            showCompleteDialog()
        }
    }

    override fun onInfoSelected(challenge: Challenge, position: Int) {
        val language = Locale.getDefault().displayLanguage
        if (language == "suomi") {
            Toast.makeText(context, "Näytetään info haasteelle ${challenge.name_fin}", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Showing info for ${challenge.name}", Toast.LENGTH_SHORT).show()
        }

        findNavController().navigate(R.id.navigation_learning)
    }

    private fun setupActionBar() {
        val actionBar = activity?.findViewById<Toolbar>(R.id.toolbar)

        val actionBarTitle= actionBar?.findViewById<TextView>(R.id.actionbar_title)
        val actionBarSubtitle= actionBar?.findViewById<TextView>(R.id.actionbar_subtitle)
        actionBarTitle?.text = getString(R.string.title_home)
        homeViewModel.carbonSaved.observe(this, Observer { carbonSaved ->
            actionBarSubtitle?.text = getString(R.string.subtitle_home, carbonSaved)

        })
        homeViewModel.carbonSaved.value = activityViewModel.user.carbonSaved.toInt()

        Utils.setGradientTextColor(
            actionBarTitle!!,
            requireContext().getColor(R.color.colorPrimary_blue),
            requireContext().getColor(R.color.colorPrimary_green)
        )

        val optionalLayout = actionBar.findViewById<LinearLayout>(R.id.optional_layout)
        optionalLayout.removeAllViews()
    }

    private fun setupChallengesList() {
        challengesListAdapter = ChallengesListAdapter(requireContext(),this)
        binding.challengesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.challengesRecyclerView.adapter = challengesListAdapter

        homeViewModel.todayChallenges.observe(viewLifecycleOwner, Observer { challenges ->
            Timber.d("TodayChallenges changed")
            challengesListAdapter.onDataChanged(challenges)
        })
    }

    private fun generateTodayChallenges() {
        runInBackground(
            Runnable {
                val sharedPreferences =
                    requireActivity().getSharedPreferences(Utils.SHARE_PREFS, Context.MODE_PRIVATE)
                val jsonString = sharedPreferences.getString(Utils.PREF_UNLOCKED_PACK, "")!!

                if (jsonString.isNotEmpty()) {
                    val list =
                        Gson().fromJson(jsonString, Array<ChallengePack>::class.java).toList()
                    val arrayList = arrayListOf<ChallengePack>()
                    arrayList.addAll(list)
                    activityViewModel.unlockedChallengePacks = arrayList
                }

                Timber.d("Unlocked: ${activityViewModel.unlockedChallengePacks}")

                val availableChallenges = homeViewModel.allChallenges.filter { challenge ->
                    activityViewModel.unlockedChallengePacks.contains(challenge.challengePack)
                }

                val personalizedChallenges = availableChallenges.filter { challenge ->
                    when {
                        (challenge.diet.isNotEmpty() && !challenge.diet.contains(activityViewModel.user.diet)) -> false
                        (challenge.housingType.isNotEmpty() && !challenge.housingType.contains(
                            activityViewModel.user.housingType
                        )) -> false
                        (challenge.transportation.isNotEmpty() && challenge.transportation.intersect(
                            activityViewModel.user.transportation
                        ).isEmpty()) -> false
                        else -> true
                    }
                }
                val todayChallenges = arrayListOf<Challenge>()
                personalizedChallenges
                    .shuffled()
                    .subList(0, 3)
                    .forEach {
                        it.status = CompleteStatus.UNSTARTED
                        todayChallenges.add(it)
                    }

                requireActivity().runOnUiThread {
                    homeViewModel.todayChallenges.value?.clear()
                    homeViewModel.todayChallenges.value = todayChallenges
                    challengesListAdapter.notifyDataSetChanged()
                }

            }
        )
    }

    private fun showCompleteDialog() {
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }

        builder?.setMessage(R.string.all_complete_dialog_message)
            ?.setTitle(R.string.all_complete_dialog_title)
            ?.setPositiveButton(R.string.dialog_ok, null)
            ?.create()
            ?.show()
    }
}