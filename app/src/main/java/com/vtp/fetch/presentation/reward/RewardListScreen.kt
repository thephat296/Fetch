package com.vtp.fetch.presentation.reward

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vtp.fetch.R
import com.vtp.fetch.domain.model.Reward

@Composable
fun RewardListScreen(
    state: RewardUiState,
    onRewardClicked: (Reward) -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        TextTest(state)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            items(state.rewards, key = Reward::id) { reward ->
                RewardItem(
                    reward = reward,
                    onItemClicked = onRewardClicked
                )
            }
        }

        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 3000,
                        easing = LinearOutSlowInEasing
                    )
                )
                .height(if (state.rewards.isNotEmpty()) 100.dp else 0.dp)
                .background(Color.Blue)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Test",
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }

    }
}

@Composable
fun TextTest(state: RewardUiState) {
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = state.isLoading,
        enter = expandVertically(
            animationSpec = tween(durationMillis = 2000)
        ),
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = 2000)
        )
    ) {

        Column(
            modifier = Modifier
                /*.animateContentSize(
                    animationSpec = tween(
                        durationMillis = 3000,
                        easing = LinearOutSlowInEasing
                    )
                )*/
//                .height(if (state.rewards.isNotEmpty()) 100.dp else 0.dp)
                .height(100.dp)
                .padding(bottom = 10.dp)
                .background(Color.Blue)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Text",
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }
    }

}

val SlowOutSlowInEasing = CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f)

@Composable
fun BasketQuantityCtaView(
    quantity: Int?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val isVisible = (quantity ?: 0) > 0
    AnimatedVisibility(
        visible = isVisible,
        enter = expandVertically(
            animationSpec = tween(durationMillis = 2000),
            expandFrom = Alignment.Top
        ),
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = 2000),
            shrinkTowards = Alignment.Top
        )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 20.dp, end = 20.dp, bottom = 12.dp)
                .clip(shape = RoundedCornerShape(30.dp))
                .background(Color.Black)
                .clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "View Basket",
                modifier = Modifier.wrapContentSize(),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                text = quantity.toString(),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .width(32.dp)
                    .height(32.dp)
                    .clip(shape = CircleShape)
                    .background(Color.Gray)
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterVertically),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

/*@Preview
@Composable
private fun BasketQuantityCtaViewPreview() {
    MaterialTheme {
        BasketQuantityCtaView(quantity = 2)
    }
}*/

@Composable
fun WalletWidgetItemView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color.Gray)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .size(20.dp)
                    .padding(start = 16.dp, top = 12.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "7-Eleven Wallet",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.black)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "$0.00",
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        modifier = Modifier.padding(end = 16.dp),
                        painter = painterResource(id = androidx.appcompat.R.drawable.abc_ab_share_pack_mtrl_alpha),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Black)
                    )
                }
            }
        }
        Text(
            text = "Earn 5% cashback, 10% with Gold Pass ",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                .background(color = Color.Green)
//                    .background(color = Color(resource = R.drawable.bg_bottom_round_green))
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .align(Alignment.BottomCenter),
            style = TextStyle(
                fontSize = 16.sp,
                color = colorResource(id = R.color.white)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WalletWidgetItemViewPreview() {
    MaterialTheme {
        WalletWidgetItemView()
    }
}


