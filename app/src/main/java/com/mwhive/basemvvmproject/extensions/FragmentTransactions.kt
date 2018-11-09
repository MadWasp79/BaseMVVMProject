package com.mwhive.basemvvmproject.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


/**
 * to add or replace fragment inside AppCompatActivity we just need to call functions like:
 *
 * addFragment(fragment, R.id.fragment_container)
 * replaceFragment(fragment, R.id.fragment_container)
 */

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}


fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction{ replace(frameId, fragment) }
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.()-> FragmentTransaction) {
    beginTransaction().func().commit()
}