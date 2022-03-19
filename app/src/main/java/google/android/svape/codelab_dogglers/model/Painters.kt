package google.android.svape.codelab_dogglers.model

import androidx.annotation.DrawableRes

data class Painters(
    @DrawableRes val imageResourceId: Int,
    val name: String,
    val years: String,
    val artwork: String
)
