package com.kryptonictest.data.repository

import com.kryptonictest.data.remote.GithubRepositoryServices
import com.kryptonictest.domain.model.baseResponse.BaseResponse
import com.kryptonictest.domain.model.githubList.GithubRepo
import com.kryptonictest.domain.repository.GithubRepositoryRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GithubRepositoryRepImp @Inject constructor(
    private val authRepo: GithubRepositoryServices
) : GithubRepositoryRepo {
    override fun searchGithubRepository(params: HashMap<String, Any>): Flow<BaseResponse<ArrayList<GithubRepo>>> =
        flow {
            emit(authRepo.searchGithubRepository(params))
        }.flowOn(Dispatchers.IO)
}