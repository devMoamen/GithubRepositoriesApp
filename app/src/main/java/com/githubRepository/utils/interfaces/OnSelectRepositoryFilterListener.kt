package com.githubRepository.utils.interfaces

import com.githubRepository.domain.model.general.RepositoryFilterType


interface OnSelectRepositoryFilterListener {
    fun onSelectFilter(filterType: RepositoryFilterType)
}