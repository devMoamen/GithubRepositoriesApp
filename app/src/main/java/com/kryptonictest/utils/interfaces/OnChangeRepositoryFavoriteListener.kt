package com.kryptonictest.utils.interfaces

import com.kryptonictest.domain.model.githubList.GithubRepo

interface OnChangeRepositoryFavoriteListener {
    fun onChange(item: GithubRepo, isRemove: Boolean)
}