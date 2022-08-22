package com.umbertoemonds.dharmonie.utils.injection

import android.content.Context
import com.umbertoemonds.dharmonie.R

class Mapping(context: Context) {

    val notes = hashMapOf(
        0 to "C",
        1 to "C#",
        1 to "Db",
        2 to "D",
        3 to "D#",
        3 to "Eb",
        4 to "E",
        5 to "F",
        6 to "F#",
        6 to "Gb",
        7 to "G",
        8 to "G#",
        8 to "Ab",
        9 to "A",
        10 to "A#",
        10 to "Bb",
        11 to "B"
    )

    val modes = hashMapOf(
        0 to context.getString(R.string.major),
        1 to context.getString(R.string.minor)
    )

}