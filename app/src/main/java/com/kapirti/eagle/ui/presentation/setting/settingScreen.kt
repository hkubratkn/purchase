package com.kapirti.eagle.ui.presentation.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.kapirti.eagle.R

@Composable
fun SettingScreen(
    viewModel: SettingScreenViewModel = hiltViewModel(),
    modifier: Modifier
){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        RowBody(icon = Icons.Default.Share, text = stringResource(id = R.string.share)) {
            viewModel.share()
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_10)))
        RowBody(icon = Icons.Default.Star, text = stringResource(id = R.string.rate)) {
            viewModel.rate()
        }
    }
}

@Composable
private fun RowBody(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.padding_50))
            .clickable { onClick() }
    ){
        Icon(imageVector = icon, contentDescription = null)
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_5)))
        Text(text = text)
    }
}