package com.kryptonictest.data.remote

import com.kryptonictest.domain.model.baseResponse.BaseResponse
import com.kryptonictest.domain.model.githubList.GithubRepo
import com.kryptonictest.network.ApiConstants.SEARCH_REPOSITORIES
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface GithubRepositoryServices {
    @GET(SEARCH_REPOSITORIES)
    suspend fun searchGithubRepository(@QueryMap params: HashMap<String, Any>): BaseResponse<ArrayList<GithubRepo>>
}