package com.bysagir.githubissues.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pullrequest (
    val url:String?=null,
    val html_url:String?=null,
    val diff_url:String?=null,
    val patch_url:String?=null
):Parcelable