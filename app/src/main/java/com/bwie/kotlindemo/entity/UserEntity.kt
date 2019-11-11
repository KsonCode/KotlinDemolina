package com.bwie.kotlindemo.entity

/**
 * 实体类，通过json to kotlin插件，alt+k
 */
data class UserEntity(
    val message: String,
    val result: Result,
    val status: String
)

data class Result(
    val headPic: String,
    val nickName: String,
    val phone: String,
    val sessionId: String,
    val sex: Int,
    val userId: Int
)