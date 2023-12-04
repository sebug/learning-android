package ch.sebug.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CourseTopic(
    @DrawableRes
    val imageResourceId: Int,
    @StringRes
    val nameResourceId: Int,
    val numberOfParticipants: Int
)
