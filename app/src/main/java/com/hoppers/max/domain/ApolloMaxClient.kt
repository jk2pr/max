package com.hoppers.max.domain

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.hoppers.max.*

class ApolloMaxClient(private val apolloClient: ApolloClient) : IClient {
    override suspend fun auth(userName: String, password: String): String {
        return apolloClient.mutation(AuthMutation(userName, password))
            .execute()
            .data?.auth.orEmpty()
    }

    override suspend fun apply(jobId: String): Boolean {
        return apolloClient.mutation(ApplyMutation(jobId))
            .execute()
            .data?.apply ?: false
    }

    override suspend fun active(limit: Int, page: Int): JobResponse {
        val res = apolloClient.query(ActiveQuery(Optional.present(limit), Optional.present(page)))
            .execute().data?.active
        val jobs: List<Job?> =
            res?.jobs?.map {
                it?.let {
                    Job(
                        id = it._id,
                        location = it.location,
                        description = it.description,
                        haveIApplied = it.haveIApplied ?: false,
                        industry = it.industry,
                        positionTitle = it.positionTitle,
                        salaryRange = SalaryRange(
                            min = it.salaryRange?.min ?: 0, max = it.salaryRange?.max ?: 0,
                        )
                    )
                }
            }?.toList() ?: emptyList<Job>()
        return JobResponse(
            jobs = jobs.filterNotNull(),
            hasNext = res?.hasNext ?: false,
            page = res?.page ?: 0,
            total = res?.total ?: 0,
            size = res?.size ?: 0
        )

    }

    override suspend fun job(jobId: String): Job? {

        val response = apolloClient.query(JobQuery(jobId)).execute().data?.job
        return response?.let {
            return Job(
                id = response._id,
                description = response.description,
                industry = response.industry,
                location = response.location,
                positionTitle = response.positionTitle,
                salaryRange = SalaryRange(
                    response.salaryRange?.min ?: 0,
                    response.salaryRange?.max ?: 0
                )
            )
        }
    }

    override suspend fun jobs(limit: Int, page: Int): JobResponse {
        val res = apolloClient.query(
            JobsQuery(
                limit = Optional.present(limit),
                page = Optional.present(page)
            )
        ).execute().data?.jobs
        val jobs: List<Job?> =
            res?.jobs?.map {
                it?.let {
                    Job(
                        id = it._id,
                        location = it.location,
                        description = it.description,
                        haveIApplied = it.haveIApplied ?: false,
                        industry = it.industry,
                        positionTitle = it.positionTitle,
                        salaryRange = SalaryRange(
                            min = it.salaryRange?.min ?: 0, max = it.salaryRange?.max ?: 0,
                        )
                    )
                }
            }?.toList() ?: emptyList<Job>()
        return JobResponse(
            jobs = jobs.filterNotNull(),
            hasNext = res?.hasNext ?: false,
            page = res?.page ?: 0,
            total = res?.total ?: 0,
            size = res?.size ?: 0
        )
    }
}