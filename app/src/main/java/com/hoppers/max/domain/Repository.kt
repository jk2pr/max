package com.hoppers.max.domain

import javax.inject.Inject

class Repository @Inject constructor(var api: ApolloMaxClient) {
    suspend fun doLogin(userName: String, password: String) = api.auth(userName, password)
    suspend fun findJobs(limit: Int, page: Int) = api.jobs(limit, page)
}