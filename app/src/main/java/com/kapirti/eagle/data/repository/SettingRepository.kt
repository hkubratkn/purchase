package com.kapirti.eagle.data.repository

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.kapirti.eagle.R
import com.kapirti.eagle.domain.Constants.SHARE_CODE

class SettingRepository(
    private val context: Context
){

    fun share() {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, SHARE_CODE)
        intent.type = "text/plain"
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.app_name)))
    }

    fun rate() {
        val shareIntent = Intent(Intent.ACTION_VIEW, Uri.parse(SHARE_CODE))
        context.startActivity(shareIntent)
    }

}