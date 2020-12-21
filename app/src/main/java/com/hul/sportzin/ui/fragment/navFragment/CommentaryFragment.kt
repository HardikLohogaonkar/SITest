package com.hul.sportzin.ui.fragment.navFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hul.sportzin.R
import com.hul.sportzin.databinding.FragmentCommentaryBinding
import com.hul.sportzin.repository.SummaryRepository
import com.hul.sportzin.util.ViewModelFactory
import com.hul.sportzin.viewmodel.SummaryViewModel

class CommentaryFragment : Fragment() {

    private lateinit var mCommentaryBinding: FragmentCommentaryBinding
    private lateinit var mSummaryViewModel: SummaryViewModel
    private lateinit var mSummaryRepository: SummaryRepository


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mSummaryRepository = SummaryRepository()
        mCommentaryBinding = FragmentCommentaryBinding.inflate(inflater, container, false)
        mCommentaryBinding.lifecycleOwner = this

        mSummaryViewModel = ViewModelProvider(
            this,
            ViewModelFactory(mSummaryRepository)
        )[SummaryViewModel::class.java]
        mCommentaryBinding.toolbar.tvMatchTitle.text = resources.getString(R.string.title_commentary)

        return mCommentaryBinding.root
    }
}