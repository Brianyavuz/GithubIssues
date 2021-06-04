package com.bysagir.githubissues.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
    val url: String? = null,
    val html_url: String? = null,
    val issue_url: String? = null,
    val id: Int? = null,
    val node_id: String? = null,
    val user: User? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val author_association: String? = null,
    val body: String? = null,
    val performed_via_github_app: String? = null
):Parcelable