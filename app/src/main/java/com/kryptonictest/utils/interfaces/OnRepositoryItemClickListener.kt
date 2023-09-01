package com.kryptonictest.utils.interfaces

import com.kryptonictest.domain.model.githubList.GithubRepo

interface OnRepositoryItemClickListener {
    fun openRepositoryDetails(item: GithubRepo)

}