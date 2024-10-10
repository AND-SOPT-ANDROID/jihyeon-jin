package org.sopt.and.ui.mypage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.mypage.component.MyPageContents
import org.sopt.and.ui.mypage.component.MyPagePromotion
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.WavveBg

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyPageScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    email: String = ""
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WavveBg)
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(
                    R.drawable.profile_default
                ),
                contentDescription = "profile image",
                modifier = Modifier.size(54.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = "${email}님",
                color = Color.White,
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "notification",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = "setting",
                tint = Color.White
            )
        }
        MyPagePromotion(
            modifier = Modifier
            .fillMaxWidth()
            .background(color = WavveBg)
            .padding(16.dp),
            title = "첫 결제 시 첫 달 100원!"
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.Black
        )
        MyPagePromotion(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = WavveBg)
                .padding(16.dp),
            title = "현재 보유하신 이용권이 없습니다."
        )
        MyPageContents(
            modifier = Modifier.fillMaxWidth()
                .weight(0.5f)
                .background(Color.Black)
                .padding(top = 16.dp),
            title = "전체 시청내역",
            information = "시청내역이 없어요.",
        )
        MyPageContents(
            modifier = Modifier.fillMaxWidth()
                .weight(0.5f)
                .background(Color.Black)
                .padding(top = 16.dp),
            title = "관심 프로그램",
            information = "관심 프로그램이 없어요.",
        )
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    ANDANDROIDTheme {
        MyPageScreen()
    }
}