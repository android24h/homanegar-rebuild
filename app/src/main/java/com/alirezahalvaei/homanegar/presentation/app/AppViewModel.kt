package com.alirezahalvaei.homanegar.presentation.app

import androidx.lifecycle.ViewModel
import com.alirezahalvaei.homanegar.domain.model.AppSettings
import com.alirezahalvaei.homanegar.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(val repository: SettingsRepository) : ViewModel() {
    val settings: StateFlow<AppSettings> = repository.settings

}