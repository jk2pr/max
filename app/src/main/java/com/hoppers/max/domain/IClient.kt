package com.hoppers.max.domain

interface IClient {
    suspend fun auth(userName:String, password:String): String
    suspend fun apply(jobId: String): Boolean
    suspend fun active(limit:Int, page:Int): JobResponse
    suspend fun job(jobId:String): Job?
    suspend fun jobs(limit:Int, page:Int): JobResponse

}