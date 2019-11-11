package com.bwie.kotlindemo.api

import com.bwie.kotlindemo.entity.UserEntity
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserApiservice {

    @POST("small/user/v1/login")
    @FormUrlEncoded
    fun login(@FieldMap hashMap: HashMap<String,String>):Observable<UserEntity>

}