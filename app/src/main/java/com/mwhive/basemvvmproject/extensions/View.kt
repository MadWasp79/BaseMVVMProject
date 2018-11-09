package com.mwhive.basemvvmproject.extensions

import android.view.View
import android.widget.EditText

//set view visibility with boolean
fun View.visibleOrGone(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

//set Hint text in EditText
fun EditText.setHintOrNothing(data: String?) {
    this.text.clear()

    if (data?.isNotEmpty() == true)
        this.hint = data
}

////set multi-part Hint text in EditText
fun EditText.setHintOrNothing(data1: String?, data2: String?) {
    if (data1?.isNotEmpty() == true || data2?.isNotEmpty() == true)
        this.hint = "$data1 $data2"
}
