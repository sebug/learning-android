package ch.sebug.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes
    val nameResourceId: Int,
    val numberOfParticipants: Int,
    @DrawableRes
    val imageResourceId: Int
)
