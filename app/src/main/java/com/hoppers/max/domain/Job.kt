package com.hoppers.max.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Job(val id:String?,
               val positionTitle: String?,
               val description: String?,
               val salaryRange: SalaryRange?,
               val location: Int?,
               val industry: Int?,
               val haveIApplied: Boolean = false
               ) : Parcelable