package com.kapirti.eagle.ui

import androidx.annotation.StringRes

data class ButtonModel(@StringRes val stringResource : Int, val onClick: () -> Unit)