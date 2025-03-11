package com.mikelau.loungemembership.utils

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

class AlaskaUrl {

    fun openLinkExternally(context: Context, url: String?) {
        val linkIntent = Intent(Intent.ACTION_VIEW)
        linkIntent.setData(url?.toUri())
        linkIntent.addCategory(Intent.CATEGORY_BROWSABLE)
        context.startActivity(linkIntent)
    }

}