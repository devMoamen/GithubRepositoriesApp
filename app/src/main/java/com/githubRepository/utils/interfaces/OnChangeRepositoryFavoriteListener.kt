package com.githubRepository.utils.interfaces

import com.githubRepository.domain.model.githubList.GithubRepo

interface OnChangeRepositoryFavoriteListener {
    fun onChange(item: GithubRepo, isRemove: Boolean)
}