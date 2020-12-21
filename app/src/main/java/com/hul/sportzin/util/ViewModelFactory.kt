package com.hul.sportzin.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hul.sportzin.repository.SummaryRepository
import com.hul.sportzin.viewmodel.SummaryViewModel

class ViewModelFactory(private val mSummaryRepository: SummaryRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return SummaryViewModel(mSummaryRepository) as T
    }
}