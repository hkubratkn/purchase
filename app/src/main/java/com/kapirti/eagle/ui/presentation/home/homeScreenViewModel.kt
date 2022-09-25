package com.kapirti.eagle.ui.presentation.home

import androidx.lifecycle.ViewModel
import com.kapirti.eagle.data.repository.HomeScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: HomeScreenRepository
): ViewModel() {

    fun rate(){
        repository.rate()
    }

    fun share() {
        repository.share()
    }
}