package com.bysagir.githubissues.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Label (
    val id:Int?=null,
    val node_id:String?=null,
    val url:String?=null,
    val name:String?=null,
    val description:String?=null,
    val color:String?=null,
    val default:Boolean?=null
):Parcelable