package com.example.brahmand.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.brahmand.R

data class Planet(
    @DrawableRes val imageResourceId: Int,
    @StringRes val planetName:Int,
    @StringRes val nickName:Int,
    @StringRes val distance:Int,
    @StringRes val moons:Int,
    @StringRes val mass:Int
)

val planets = listOf(
    Planet(R.drawable.mercury,R.string.mercury,R.string.nickMercury,R.string.distance_mercury,R.string.moons_mercury,R.string.mMercury),
    Planet(R.drawable.venus,R.string.venus,R.string.nickVenus,R.string.distance_venus,R.string.moons_venus,R.string.mVenus),
    Planet(R.drawable.earth,R.string.earth,R.string.nickEarth,R.string.distance_earth,R.string.moons_earth,R.string.mEarth),
    Planet(R.drawable.mars,R.string.mars,R.string.nickMars,R.string.distance_mars,R.string.moons_mars,R.string.mMars),
    Planet(R.drawable.jupiter,R.string.jupiter,R.string.nickJupiter,R.string.distance_jupiter,R.string.moons_jupiter,R.string.mJupiter),
    Planet(R.drawable.saturn,R.string.saturn,R.string.nickSaturn,R.string.distance_saturn,R.string.moons_saturn,R.string.mSaturn),
    Planet(R.drawable.uranus,R.string.uranus,R.string.nickUranus,R.string.distance_uranus,R.string.moons_uranus,R.string.mUranus),
    Planet(R.drawable.neptune,R.string.neptune,R.string.nickNeptune,R.string.distance_neptune,R.string.moons_neptune,R.string.mNeptune)
)
