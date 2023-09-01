package com.kryptonictest.utils.interfaces

import com.kryptonictest.domain.model.general.RepositoryFilterType


interface OnSelectRepositoryFilterListener {
    fun onSelectFilter(filterType: RepositoryFilterType)
}