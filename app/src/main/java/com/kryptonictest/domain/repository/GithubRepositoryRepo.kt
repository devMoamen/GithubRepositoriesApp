package com.kryptonictest.domain.repository


import com.kryptonictest.domain.model.baseResponse.BaseResponse
import com.kryptonictest.domain.model.githubList.GithubRepo
import kotlinx.coroutines.flow.Flow

interface GithubRepositoryRepo {
    fun searchGithubRepository(params: HashMap<String, Any>): Flow<BaseResponse<ArrayList<GithubRepo>>>
}