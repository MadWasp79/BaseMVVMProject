package  com.mwhive.basemvvmproject.utils

import android.view.MotionEvent

/**
 * Created by i7-4770k on 01.05.2018
 */

fun filterEvent(motionEvent: MotionEvent): Boolean {
    return motionEvent.action == MotionEvent.ACTION_DOWN || motionEvent.action == MotionEvent.ACTION_UP
}

fun isEventDown(motionEvent: MotionEvent): Boolean {
    return motionEvent.action == MotionEvent.ACTION_DOWN
}
