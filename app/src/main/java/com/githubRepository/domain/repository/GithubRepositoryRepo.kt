package com.githubRepository.domain.repository


import com.githubRepository.domain.model.baseResponse.BaseResponse
import com.githubRepository.domain.model.githubList.GithubRepo
import kotlinx.coroutines.flow.Flow

interface GithubRepositoryRepo {
    fun searchGithubRepository(params: HashMap<String, Any>):
            Flow<BaseResponse<ArrayList<GithubRepo>>>
}