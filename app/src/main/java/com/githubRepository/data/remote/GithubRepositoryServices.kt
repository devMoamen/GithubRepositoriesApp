package com.githubRepository.data.remote

import com.githubRepository.domain.model.baseResponse.BaseResponse
import com.githubRepository.domain.model.githubList.GithubRepo
import com.githubRepository.network.ApiConstants.SEARCH_REPOSITORIES
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GithubRepositoryServices {
    @GET(SEARCH_REPOSITORIES)
    suspend fun searchGithubRepository(@QueryMap params: HashMap<String, Any>): BaseResponse<ArrayList<GithubRepo>>
}