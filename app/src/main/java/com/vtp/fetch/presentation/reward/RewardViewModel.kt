package com.vtp.fetch.presentation.reward

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vtp.fetch.domain.usecase.GetRewardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RewardViewModel @Inject constructor(
    private val getRewardsUseCase: GetRewardsUseCase
) : ViewModel() {

    private val _rewardUiState = MutableStateFlow(RewardUiState())
    val rewardUiState: StateFlow<RewardUiState> = _rewardUiState

    init {
        getRewardsUseCase()
            .map {
                RewardUiState(rewards = it)
            }
            .catch {
                emit(RewardUiState(error = it))
            }
            .onEach {
                _rewardUiState.value = it
            }
            .launchIn(viewModelScope)
    }

    fun toggleRewards() {
        _rewardUiState.value = rewardUiState.value.copy(
            isLoading = rewardUiState.value.isLoading.not()
        )
    }
}