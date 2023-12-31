package com.githubRepository.app.bases

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isRefreshLoading = MutableLiveData<Boolean>().apply { value = false }

    val showErrorMessage = MutableLiveData<String>().apply { value = "" }
    val showSuccessMessage = MutableLiveData<String>().apply { value = "" }

}