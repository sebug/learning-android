package ch.sebug.meow.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ch.sebug.meow.R

data class Cat(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    val age: Int,
    @StringRes val description: Int
)

val cats = listOf(
    Cat(R.drawable.cat_0, R.string.cat_0_name, 2, R.string.cat_0_description),
    Cat(R.drawable.cat_1, R.string.cat_1_name, 11, R.string.cat_1_description),
    Cat(R.drawable.cat_2, R.string.cat_2_name, 3, R.string.cat_2_description),
    Cat(R.drawable.cat_3, R.string.cat_3_name, 17, R.string.cat_3_description),
    Cat(R.drawable.cat_4, R.string.cat_4_name, 5, R.string.cat_4_description),
    Cat(R.drawable.cat_5, R.string.cat_5_name, 6, R.string.cat_5_description),
    Cat(R.drawable.cat_6, R.string.cat_6_name, 5, R.string.cat_6_description),
    Cat(R.drawable.cat_7, R.string.cat_7_name, 1, R.string.cat_7_description),
    Cat(R.drawable.cat_8, R.string.cat_8_name, 10, R.string.cat_8_description),
    Cat(R.drawable.cat_9, R.string.cat_9_name, 9, R.string.cat_9_description)
)