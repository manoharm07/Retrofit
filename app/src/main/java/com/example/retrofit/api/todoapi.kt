package com.example.retrofit.api

import retrofit2.Response
import retrofit2.http.GET

interface todoapi {

    @GET("/todos")
    suspend fun getTodos() : Response<List<todo>>

    //attach query parameter
    // fun gettodos(
    // @Query("key") key: String
    //): Response<List<todo>>


    // POST("/createTodo")
    // fun createTodo(
    //  @Body TODO: todo
    //  ) : Response<createTodo>

}