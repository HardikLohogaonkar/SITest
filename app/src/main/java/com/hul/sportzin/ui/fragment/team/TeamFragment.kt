package com.hul.sportzin.ui.fragment.team

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hul.sportzin.databinding.FragmentTeamBinding
import com.hul.sportzin.model.Players
import com.hul.sportzin.repository.SummaryRepository
import com.hul.sportzin.ui.adapter.TeamListAdapter
import com.hul.sportzin.util.ViewModelFactory
import com.hul.sportzin.viewmodel.SummaryViewModel
import kotlinx.android.synthetic.main.fragment_team.*
import java.util.*

class TeamFragment : Fragment() {

    private lateinit var mTeamBinding: FragmentTeamBinding
    private lateinit var mSummaryViewModel: SummaryViewModel
    private lateinit var mTeamAdapter: TeamListAdapter
    private var TAG = "TeamFragment"

    companion object {
        fun newInstance(playerList: List<Players>): TeamFragment {
            val fragment = TeamFragment()
            val args = Bundle()
            args.putParcelableArrayList("players", playerList as ArrayList<out Parcelable>)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mRepository = SummaryRepository()
        mTeamBinding = FragmentTeamBinding.inflate(inflater, container, false)
        mSummaryViewModel =
            ViewModelProvider(this, ViewModelFactory(mRepository))[SummaryViewModel::class.java]
        mTeamBinding.lifecycleOwner = this

        return mTeamBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.getParcelableArrayList<Players>("players")?.let {
            mTeamAdapter = TeamListAdapter(it)
            rvPlayer.adapter = mTeamAdapter
            mTeamAdapter.notifyDataSetChanged()
        }
    }
}