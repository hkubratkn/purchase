package com.kapirti.eagle.ui.presentation.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kapirti.eagle.R
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kapirti.eagle.ui.navigation.Screen

@Composable
fun HomeScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            TopAppBarBody(navController = navController)
        }
    ) { innerPadding ->
        BodyContent(
            modifier = modifier.padding(innerPadding),
            viewModel = viewModel
        )
    }

}

@Composable
private fun TopAppBarBody(
    navController: NavController
){
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name)
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.SettingS.route)
                }
            )
        },
        backgroundColor = MaterialTheme.colors.background
    )

}

@Composable
private fun BodyContent(
    modifier: Modifier,
    viewModel: HomeScreenViewModel
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextButton(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.padding_200))
                .height(dimensionResource(id = R.dimen.padding_200))
                .background(MaterialTheme.colors.primaryVariant),
            onClick = {},
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_100))
        ){
            Text(
                text = viewModel.btnText.value,
                color = MaterialTheme.colors.primary,
                fontStyle = FontStyle.Italic
            )
        }

        /**        Text(
        text = MyService.timeForCounter.value,
        color = MaterialTheme.colors.primary,
        style = MaterialTheme.typography.h1,
        modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = dimensionResource(id = R.dimen.padding_10)),
        textAlign = TextAlign.Center
        )

        Text(
        text = stringResource(id = R.string.three_times_twenty),
        fontFamily = FontFamily.SansSerif,
        color = MaterialTheme.colors.error,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = dimensionResource(id = R.dimen.padding_10)),
        textAlign = TextAlign.Center
        )



        Row(
        modifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.padding_20))
        ) {

        Button(
        modifier = Modifier
        .fillMaxWidth(0.5f)
        .padding(dimensionResource(id = R.dimen.padding_5)),
        enabled = MyService.btnStateStop.value,
        onClick = {
        MyService.stopService(context)
        }
        ) { Text(text = stringResource(id = R.string.stop)) }
        Button(
        modifier = Modifier
        .fillMaxWidth()
        .padding(dimensionResource(id = R.dimen.padding_5)),
        enabled = MyService.btnStateStart.value,
        onClick = {
        MyService.startService(context, context.getString(R.string.service_start))
        }
        ) { Text(text = stringResource(id = R.string.start)) }

        }

        }*/
    }
}