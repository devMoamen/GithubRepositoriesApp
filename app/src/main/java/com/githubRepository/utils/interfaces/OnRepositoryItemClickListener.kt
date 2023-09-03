package com.githubRepository.utils.interfaces

import com.githubRepository.domain.model.githubList.GithubRepo

interface OnRepositoryItemClickListener {
    fun openRepositoryDetails(item: GithubRepo)

}