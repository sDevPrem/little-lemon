package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.ui.theme.app.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun OnBoarding(){
    var firstName by rememberSaveable{ mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(80.dp)
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 32.dp)
            ,
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little lemon logo",
        )
        Box(
            modifier = Modifier
                .background(AppTheme.color.primary1)
                .padding(0.dp, 48.dp, 0.dp, 48.dp)
                .fillMaxWidth()
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                color = AppTheme.color.highlight1,
                text = stringResource(R.string.on_boarding_title),
                textAlign = TextAlign.Center,
                style = AppTheme.typography.subTitle
            )
        }
        Text(
            text = stringResource(R.string.on_boarding_sub_title),
            modifier = Modifier
                .padding(16.dp,40.dp),
            style = AppTheme.typography.sectionTitle
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp,0.dp, 16.dp, 16.dp),
            value = firstName,
            maxLines = 1,
            onValueChange = {firstName = it},
            label = {
                Text(text = stringResource(R.string.on_boarding_form_first_name_label))
            },

        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp),
            value = lastName,
            onValueChange = {lastName = it},
            maxLines = 1,
            label = {
                Text(text = stringResource(R.string.on_boarding_form_last_name_label))
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp),
            value = email,
            maxLines = 1,
            onValueChange = {email = it},
            label = {
                Text(text = stringResource(R.string.on_boarding_form_email_label))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.filledTonalButtonColors(containerColor = AppTheme.color.primary2)
        ) {
            Text(text = stringResource(R.string.on_boarding_form_register_btn_txt))
        }

    }
}