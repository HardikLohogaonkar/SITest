package com.hul.sportzin.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.tabs.TabLayout
import com.hul.sportzin.model.Players

@BindingAdapter("tabText")
fun setTabText(tabLayout: TabLayout, text: String) {

}

@BindingAdapter("text")
fun setPlayerName(textView: TextView, players: Players) {

    when {
        players.fullName == null -> textView.text = ""

        players.isKeeper && players.isCaptain -> {
            textView.text = "${players.fullName} (c) (wk)"
        }

        players.isCaptain -> {
            textView.text = "${players.fullName} (c)"
        }
        players.isKeeper -> {
            textView.text = "${players.fullName} (wk)"
        }

        else -> textView.text = "${players.fullName}"
    }
}