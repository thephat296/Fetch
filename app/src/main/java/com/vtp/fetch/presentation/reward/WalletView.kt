package com.vtp.fetch.presentation.reward

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vtp.fetch.R

@Composable
fun WalletWidgetItemView(
    isLoading: Boolean,
    balance: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color.Gray)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.Yellow
            )
        } else {
            Column(modifier = Modifier.wrapContentSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Image(
                        modifier = Modifier.padding(start = 16.dp),
                        painter = painterResource(id = androidx.appcompat.R.drawable.btn_checkbox_checked_mtrl),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "7-Eleven Wallet",
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.black)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = balance,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Image(
                        modifier = Modifier.padding(start = 8.dp, end = 16.dp),
                        painter = painterResource(id = androidx.appcompat.R.drawable.btn_checkbox_checked_mtrl),
                        contentDescription = null
                    )
                }
                Text(
                    text = "Earn 5% cashback, 10% with Gold Pass ",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                        .background(color = Color.Green)
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.white),
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WalletWidgetItemViewPreview() {
    MaterialTheme {
        WalletWidgetItemView(false, "$0.92")
    }
}
