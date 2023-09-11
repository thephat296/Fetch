package com.vtp.fetch.data.repository

import com.vtp.fetch.data.remote.RewardApi
import com.vtp.fetch.data.remote.RewardDto
import com.vtp.fetch.data.remote.toReward
import com.vtp.fetch.domain.model.Reward
import com.vtp.fetch.domain.repository.RewardRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultRewardRepository @Inject constructor(
    private val rewardApi: RewardApi,
    private val ioDispatcher: CoroutineDispatcher,
) : RewardRepository {
    override fun getRewards(): Flow<List<Reward>> = flow {
        emit(
            rewardApi.getRewards()
                .map(RewardDto::toReward)
        )
    }
        .flowOn(ioDispatcher)
}
