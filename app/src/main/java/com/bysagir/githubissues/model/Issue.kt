package com.bysagir.githubissues.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Issue (
    val id:Int?=null,
    val node_id:String?=null,
    val url:String?=null,
    val repository_url:String?=null,
    val labels_url:String?=null,
    val comments_url:String?=null,
    val events_url:String?=null,
    val html_url:String?=null,
    val number:Int?=null,
    val state:String?=null,
    val title:String?=null,
    val body:String?=null,
    val user:User?=null,
    val labels:List<Label>?=null,
    val assignee:User?=null,
    val assignees:List<User>?=null,
    val milestone:Milestone?=null,
    val locked:Boolean?=null,
    val active_lock_reason:String?=null,
    val comments:Int?=null,
    val pull_request:Pullrequest?=null,
    val closed_at:String?=null,
    val created_at:String?=null,
    val updated_at:String?=null,
    val author_association:String?=null,
    var relatedComments:List<Comment>?=null
): Parcelable