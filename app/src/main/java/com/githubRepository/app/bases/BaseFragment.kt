package com.githubRepository.app.bases

import androidx.fragment.app.Fragment

abstract class BaseFragment(fragmentResId: Int) : Fragment(fragmentResId) {

    fun showTopError(strMessage: String) {
        (requireActivity() as BaseActivity).showTopError(strMessage)
    }

    fun showTopSuccess(strMessage: String) {
        (requireActivity() as BaseActivity).showTopSuccess(strMessage)
    }
}