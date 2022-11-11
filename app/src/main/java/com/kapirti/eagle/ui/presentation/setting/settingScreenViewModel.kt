package com.kapirti.eagle.ui.presentation.setting

import androidx.lifecycle.ViewModel
import com.kapirti.eagle.data.repository.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingScreenViewModel @Inject constructor(
    private val settingRepository: SettingRepository
): ViewModel(){

    fun share(){ settingRepository.share() }
    fun rate(){ settingRepository.rate() }
}