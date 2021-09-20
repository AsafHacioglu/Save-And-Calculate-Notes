package com.asafallahverdiyev.averages.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NoteModel(val subjectId:Int,
                     val subjectName: String,
                     val score: Float,
                     val credit: Int)
    : Parcelable {}