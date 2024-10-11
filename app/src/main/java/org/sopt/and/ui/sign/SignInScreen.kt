package org.sopt.and.ui.sign

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.extension.noRippleClickable
import org.sopt.and.ui.component.ServiceAccountItemRow
import org.sopt.and.ui.component.textField.WavveCommonPasswordField
import org.sopt.and.ui.component.textField.WavveCommonTextField
import org.sopt.and.ui.theme.Gray3
import org.sopt.and.ui.theme.Gray4
import org.sopt.and.ui.theme.WavveBg
import org.sopt.and.ui.theme.WavvePrimary

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
            hint = stringResource(R.string.sign_in_text_field_id_hint)
        )
        Spacer(modifier = Modifier.height(4.dp))
        WavveCommonPasswordField(
            value = password,
            onValueChange = onPasswordChange,
            hint = stringResource(R.string.sign_in_text_field_password_hint)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .noRippleClickable()
            ,
            colors = ButtonDefaults.buttonColors(
                containerColor = WavvePrimary
            ),
            onClick = {
                onSignInButtonClick()
            },
        ) {
            Text(
                text = stringResource(R.string.sign_in_text_login),
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
                text = stringResource(R.string.sign_in_text_find_id),
                fontSize = 12.sp,
                color = Gray3,
            )

            Text(
                text = stringResource(R.string.sign_in_text_divider),
                fontSize = 12.sp,
                color = Gray3,
            )

            Text(
                text = stringResource(R.string.sign_in_text_password_reset),
                fontSize = 12.sp,
                color = Gray3,
            )

            Text(
                text = stringResource(R.string.sign_in_text_divider),
                fontSize = 12.sp,
                color = Gray3,
            )

            Text(
                text = stringResource(R.string.sign_in_text_sign_up),
                fontSize = 12.sp,
                color = Gray3,
                modifier = Modifier
                    .clickable(interactionSource = remember { MutableInteractionSource() },
                        indication = null) {
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
                text = stringResource(R.string.sign_in_text_other_service),
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
            text = stringResource(R.string.sign_in_text_information),
            color = Gray3,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

    }
}