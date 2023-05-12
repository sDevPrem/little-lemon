package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.littlelemon.data.model.User
import com.example.littlelemon.ui.theme.app.AppTheme
import com.example.littlelemon.viewmodel.ProfileVM

@Composable
fun ProfileScreen(profileVm: ProfileVM = viewModel()) {
    val user by profileVm.user.collectAsStateWithLifecycle()
    ProfileUI(user = user)
}

@Composable
fun ProfileUI(user: User?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "little lemon logo",
            modifier = Modifier
                .padding(24.dp, 16.dp)
                .fillMaxWidth()
                .height(100.dp)
        )
        Text(
            text = stringResource(R.string.user_profile_title),
            modifier = Modifier
                .padding(0.dp, 40.dp),
            style = AppTheme.typography.subTitle
        )

        UserInfoLabel(label = stringResource(R.string.user_profile_first_name_label))
        UserInfoText(info = user?.firstName ?: "")

        Spacer(modifier = Modifier.height(32.dp))

        UserInfoLabel(label = stringResource(R.string.user_profile_last_name_label))
        UserInfoText(info = user?.lastName ?: "")

        Spacer(modifier = Modifier.height(32.dp))

        UserInfoLabel(label = stringResource(R.string.user_profile_email_label))
        UserInfoText(info = user?.email ?: "")

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.filledTonalButtonColors(containerColor = AppTheme.color.primary2),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = stringResource(R.string.user_profile_log_out_btn_txt))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    ProfileUI(User("Prem", "Thakur", "prem@example.com"))
}

@Composable
fun UserInfoLabel(
    label: String
) {
    Text(
        text = label,
        style = AppTheme.typography.sectionTitle,
        color = AppTheme.color.primary1
    )
}

@Composable
fun UserInfoText(
    info: String
) {
    Text(
        text = info,
        style = AppTheme.typography.leadText,
        modifier = Modifier
            .padding(0.dp, 8.dp)
            .fillMaxWidth()
            .border(1.dp, AppTheme.color.primary1, shape = MaterialTheme.shapes.medium)
            .padding(16.dp)
    )
}