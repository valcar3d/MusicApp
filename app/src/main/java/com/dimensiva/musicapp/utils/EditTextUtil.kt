package com.dimensiva.musicapp.utils

import android.widget.EditText

var EditText.value
    get() = this.text.toString()
    set(value) {
        this.setText(value)
    }