package com.hul.sportzin.ui.fragment.navFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hul.sportzin.R
import com.hul.sportzin.databinding.FragmentMatchInfoBinding
import com.hul.sportzin.repository.SummaryRepository
import com.hul.sportzin.util.ViewModelFactory
import com.hul.sportzin.viewmodel.SummaryViewModel

class MatchInfoFragment : Fragment() {

    private lateinit var mBinding: FragmentMatchInfoBinding
    private lateinit var mSummaryViewModel: SummaryViewModel
    private lateinit var mSummaryRepository: SummaryRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mSummaryRepository = SummaryRepository()
        mBinding = FragmentMatchInfoBinding.inflate(inflater, container, false)
        mBinding.lifecycleOwner = this

        mSummaryViewModel = ViewModelProvider(
            this,
            ViewModelFactory(mSummaryRepository)
        )[SummaryViewModel::class.java]

        mBinding.toolbar.tvMatchTitle.text = resources.getString(R.string.title_match_info)

        return mBinding.root
    }
}