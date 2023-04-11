package com.hoppers.max.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SalaryRange(val min: Int, val max: Int) : Parcelable
