package com.vtp.fetch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vtp.fetch.domain.usecase.GetRewardGroupsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RewardViewModel @Inject constructor(
    getRewardsUseCase: GetRewardGroupsUseCase
) : ViewModel() {

    val rewardUiState: StateFlow<RewardUiState> =
        getRewardsUseCase()
            .map {
                RewardUiState(rewardGroups = it)
            }
            .catch {
                emit(RewardUiState(error = it))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = RewardUiState(isLoading = true)
            )
}