package com.githubRepository.data.repository

import com.githubRepository.data.remote.GithubRepositoryServices
import com.githubRepository.domain.model.baseResponse.BaseResponse
import com.githubRepository.domain.model.githubList.GithubRepo
import com.githubRepository.domain.repository.GithubRepositoryRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GithubRepositoryRepImp @Inject constructor(
    private val githubRepositoryServices: GithubRepositoryServices
) : GithubRepositoryRepo {
    override fun searchGithubRepository(params: HashMap<String, Any>): Flow<BaseResponse<ArrayList<GithubRepo>>> =
        flow {
            emit(githubRepositoryServices.searchGithubRepository(params))
        }.flowOn(Dispatchers.IO)
}