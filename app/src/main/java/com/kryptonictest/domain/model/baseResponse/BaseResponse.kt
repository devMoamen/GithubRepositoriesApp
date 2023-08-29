package com.kryptonictest.domain.model.baseResponse

data class BaseResponse<T>(
    val message: String = "",
    val items: T,
    val errors: ArrayList<Errors> = arrayListOf(),
)

data class Errors(
    val message: String = "",
)

