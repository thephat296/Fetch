package com.vtp.fetch.presentation.deprecated

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.vtp.fetch.databinding.ActivityRewardsBinding
import com.vtp.fetch.presentation.deprecated.utils.toast
import com.vtp.fetch.presentation.reward.BasketQuantityCtaView
import com.vtp.fetch.presentation.reward.RewardViewModel
import com.vtp.fetch.presentation.reward.TextTest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RewardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRewardsBinding

    private val viewModel: RewardViewModel by viewModels()

    @Inject
    internal lateinit var rewardAdapter: RewardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRewardsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding.rvRewardGroup) {
            adapter = rewardAdapter
            layoutManager = LinearLayoutManager(this@RewardActivity)
        }
        lifecycleScope.launch {
            viewModel.rewardUiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    rewardAdapter.submitList(it.rewards)
//                    binding.progressCircular.isVisible = it.isLoading
                    it.error?.let { throwable ->
                        toast(throwable.message.orEmpty())
                    }
                }
        }
        binding.composeView.setContent {
            val state by viewModel.rewardUiState.collectAsStateWithLifecycle()
            TextTest(state)
        }
        binding.composeView2.setContent {
            val state by viewModel.rewardUiState.collectAsStateWithLifecycle()
            val quantity = if (state.isLoading) 10 else 0
            BasketQuantityCtaView(quantity)
        }
        binding.btnClickMe.setOnClickListener {
            viewModel.toggleRewards()
        }
    }
}