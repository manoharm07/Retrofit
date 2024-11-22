package com.example.retrofit.api

object fakelist {
    val exception: List<todo> = listOf(
        todo(
            completed = true,
            id = 1,
            userId = 2,
            title = "This is exception"
        ),
        todo(
            completed = true,
            id = 1,
            userId = 2,
            title = "This is networkerror"
        )
    )


    val networkerror: List<todo> = listOf(
        todo(
            completed = true,
            id = 1,
            userId = 2,
            title = "This is networkerror"
        )
    )
}