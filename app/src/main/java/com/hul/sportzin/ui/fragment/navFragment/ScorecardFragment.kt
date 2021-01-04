package com.hul.sportzin.ui.fragment.navFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.hul.sportzin.databinding.FragmentScorecardBinding
import com.hul.sportzin.model.Players
import com.hul.sportzin.model.SummaryData
import com.hul.sportzin.model.Team
import com.hul.sportzin.repository.SummaryRepository
import com.hul.sportzin.ui.adapter.TeamPagerAdapter
import com.hul.sportzin.ui.fragment.team.TeamFragment
import com.hul.sportzin.util.ConnectionManager
import com.hul.sportzin.util.Resource
import com.hul.sportzin.util.ViewModelFactory
import com.hul.sportzin.viewmodel.SummaryViewModel


class ScorecardFragment : Fragment() {

    private lateinit var mSummaryViewModel: SummaryViewModel
    private lateinit var mScorecardBinding: FragmentScorecardBinding
    private lateinit var mSummaryRepository: SummaryRepository
    private lateinit var mTeamPagerAdapter: TeamPagerAdapter
    private var mTeamList = arrayListOf<String>()
    private val TAG = "ScorecardFragment"
    private val mPlayerList = arrayListOf<Players>()
    private lateinit var teamA: List<Players>
    private lateinit var teamB: List<Players>
    private lateinit var mConnectionManager: ConnectionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mSummaryRepository = SummaryRepository()
        mScorecardBinding = FragmentScorecardBinding.inflate(inflater, container, false)
        mScorecardBinding.lifecycleOwner = this

        mSummaryViewModel = ViewModelProvider(
            this,
            ViewModelFactory(mSummaryRepository)
        )[SummaryViewModel::class.java]

        mTeamPagerAdapter = TeamPagerAdapter(childFragmentManager, lifecycle)

        mConnectionManager = ConnectionManager(requireActivity())

        mConnectionManager.observe(viewLifecycleOwner, Observer { isConnected ->
            if (isConnected) {
//                getData()

            } else {
                mSummaryViewModel.getTeamData()
                    .observe(viewLifecycleOwner, getSavedDataObservable)

            }
        })

        getData()

        return mScorecardBinding.root
    }

    private fun getData() {
        mSummaryViewModel.getSummaryData()
        mSummaryViewModel.mSummaryData.observe(viewLifecycleOwner, getSummaryObservable)

    }

    private val getSummaryObservable = Observer<Resource<SummaryData>> { response ->

        when (response) {

            is Resource.Loading -> {
                showProgressbar()
            }

            is Resource.Success -> {
                hideProgressbar()
                fetchData(response)
            }

            is Resource.Error -> {
                hideProgressbar()
            }
        }
    }


    private fun fetchData(response: Resource.Success<SummaryData>) {

        try {

            for ((key, value) in response.data!!.teams) {

                val team: Team = value
                val teamName = team.teamFullName

                for ((key, value) in team.player) {
                    val players: Players = value
                    mPlayerList.add(
                        Players(
                            teamName!!,
                            0,
                            players.position,
                            players.fullName,
                            players.isCaptain,
                            players.isKeeper

                        )
                    )
                }
                mTeamList.add(team.teamFullName.toString())

            }
            teamA = mSummaryViewModel.getTeamAPlayers(mPlayerList)
            teamB = mSummaryViewModel.getTeamBPlayers(mPlayerList)
            mScorecardBinding.toolbar.tvMatchTitle.text =
                teamA[0].teamName + " vs " + teamB[0].teamName
            mTeamPagerAdapter.addFragment(TeamFragment.newInstance(teamA))
            mTeamPagerAdapter.addFragment(TeamFragment.newInstance(teamB))

            if (mPlayerList.size != 0) {

                mSummaryViewModel.deleteData()
                mSummaryViewModel.addTeamData(teamA)
                mSummaryViewModel.addTeamData(teamB)
            } else {
                mSummaryViewModel.addTeamData(teamA)
                mSummaryViewModel.addTeamData(teamB)
            }
            mScorecardBinding.vpTeams.adapter = mTeamPagerAdapter

            setUpTabLayout()

        } catch (e: Exception) {

            Log.e(TAG, "Error: ${e.message}")
        }

    }

    private fun setUpTabLayout() {

        try {
            TabLayoutMediator(
                mScorecardBinding.tabTeams, mScorecardBinding.vpTeams
            ) { tab, position ->

                when (position) {
                    0 -> tab.text = teamA[position].teamName
                    1 -> tab.text = teamB[position].teamName
                }

                Log.d(TAG, "tabText: ${tab.text}")
            }
                .attach()
        } catch (e: Exception) {

            Log.e(TAG, "ERROR: ${e.cause} ${e.message}")
        }
    }

    private val getSavedDataObservable = Observer<List<Players>> { data ->

        hideProgressbar()
        mTeamPagerAdapter.notifyDataSetChanged()
        teamA = mSummaryViewModel.getTeamAPlayers(data)
        teamB = mSummaryViewModel.getTeamBPlayers(data)

        mTeamPagerAdapter.addFragment(TeamFragment.newInstance(teamA))
        mTeamPagerAdapter.addFragment(TeamFragment.newInstance(teamB))

        mScorecardBinding.toolbar.tvMatchTitle.text =
            teamA[0].teamName + " vs " + teamB[0].teamName

        mScorecardBinding.vpTeams.adapter = mTeamPagerAdapter

        setUpTabLayout()
        Log.d(TAG, "Db data: $teamA $teamB")
    }

    private fun hideProgressbar() {
        mScorecardBinding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressbar() {
        mScorecardBinding.progressBar.visibility = View.VISIBLE
    }

}