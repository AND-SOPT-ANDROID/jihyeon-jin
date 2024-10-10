package org.sopt.and.ui.sign

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.ui.sign.ui.component.WavveCommonPasswordField
import org.sopt.and.ui.sign.ui.component.WavveCommonTextField
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Gray3
import org.sopt.and.ui.theme.Gray4
import org.sopt.and.ui.theme.WavveBg
import org.sopt.and.ui.theme.WavvePrimary

class SignInActivity : ComponentActivity() {
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val context = LocalContext.current
                var textFieldEmail by remember {
                    mutableStateOf("")
                }
                 var textFieldPassword by remember {
                     mutableStateOf("")
                 }
                val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
                    if (result.resultCode == RESULT_OK) {
                        val data = result.data
                        email = data?.getStringExtra("EMAIL")?:""
                        password = data?.getStringExtra("PASSWORD")?:""
                    }
                }
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                Scaffold(topBar = {
                    BackButtonTopBar({  })
                }, snackbarHost = { SnackbarHost(snackbarHostState) } ,
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignInScreen(
                        modifier = Modifier.padding(innerPadding),
                        context = context,
                        email = textFieldEmail,
                        onEmailChange = { textFieldEmail = it },
                        password = textFieldPassword,
                        onPasswordChange = { textFieldPassword = it },
                        onSignUpButtonClick = {
                            launcher.launch(Intent(this, SignUpActivity::class.java))
                        },
                        onSignInButtonClick = {
                            if (email == textFieldEmail && password == textFieldPassword){
                                //val intent = Intent(context, MyActivity::class.java)
                                //startActivity(intent)
                                //finish()
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "로그인 성공"
                                    )
                                }
                            }
                            else{
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "이메일 혹은 비밀번호가 틀렸습니다."
                                    )
                                }
                            }
                            //val intent = Intent(context, MyActivity::class.java)
                            //startActivity(intent)
                            //finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SignInScreen(modifier: Modifier = Modifier,
                 context : Context,
                 email: String,
                 onEmailChange: (String) -> Unit,
                 password: String,
                 onPasswordChange: (String) -> Unit,
                 onSignUpButtonClick: () -> Unit,
                 onSignInButtonClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WavveBg)
            .padding(16.dp)
    ){
        Spacer(modifier = Modifier.height(32.dp))
        WavveCommonTextField(
            value = email,
            onValueChange = onEmailChange,
            hint = "이메일 주소 또는 아이디"
        )
        Spacer(modifier = Modifier.height(4.dp))
        WavveCommonPasswordField(
            value = password,
            onValueChange = onPasswordChange,
            hint = "비밀번호"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
            ,
            colors = ButtonDefaults.buttonColors(
                containerColor = WavvePrimary
            ),
            onClick = {
                onSignInButtonClick()
            }
        ) {
            Text(
                text = "로그인",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ){
            Text(
                text = "아이디 찾기",
                fontSize = 12.sp,
                color = Gray3,
            )

            Text(
                text = "|",
                fontSize = 12.sp,
                color = Gray3,
            )

            Text(
                text = "비밀번호 재설정",
                fontSize = 12.sp,
                color = Gray3,
            )

            Text(
                text = "|",
                fontSize = 12.sp,
                color = Gray3,
            )

            Text(
                text = "회원가입",
                fontSize = 12.sp,
                color = Gray3,
                modifier = Modifier
                    .clickable {
                        onSignUpButtonClick()
                    }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            HorizontalDivider(
                thickness = 0.5.dp,
                color = Gray4,
                modifier = Modifier
                    .weight(1f)
            )

            Text(
                text = "또는 다른 서비스 계정으로 로그인",
                color = Gray3,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            HorizontalDivider(
                thickness = 0.5.dp,
                color = Gray4,
                modifier = Modifier
                    .weight(1f)
            )
        }

        ServiceAccountItemRow()

        Spacer(Modifier.height(16.dp))

        Text(
            text = "SNS계정으로 간편하게 가입하여 서비스를 이용하실 수 있습니다. 기존 POOQ 계정 또는 Wavve 계정과는 연동되지 않으니 이용에 참고하세요.",
            color = Gray3,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackButtonTopBar(
    onBackButtonPress: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = WavveBg),
        title = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.wavve_logo),
                contentDescription = "Logo",
                tint = Color.White
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                contentDescription = "back",
                tint = Color.White,
                modifier = Modifier
                    .size(32.dp)
                    .clickable { onBackButtonPress() }
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = WavveBg,
        )
    )
}
