package com.hoppers.max.di

import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.hoppers.max.data.AppPreference
import com.hoppers.max.data.StorageHandler
import com.hoppers.max.domain.ApolloMaxClient
import com.hoppers.max.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(storageHandler: StorageHandler): ApolloMaxClient {
        return ApolloMaxClient(
            ApolloClient.Builder()
                //TODO Replace your BackEnd Url
                .serverUrl("https://graphql-afn8.onrender.com/")
                .okHttpClient(
                    OkHttpClient.Builder()
                        //TODO  Add Error Interceptor to handle Errors
                        .addInterceptor(Interceptor { chain ->
                            val request = chain.request().newBuilder()
                                .apply {
                                    // Using runBlocking assuming this won't impact UI thread
                                    runBlocking {
                                        addHeader("Authorization", storageHandler.getToken())
                                    }
                                }
                                .build()
                            chain.proceed(request)
                        })


                        .build()
                )
                .build()
        )
    }

    @Provides
    @Singleton
    fun provideStorageHandler(preference: AppPreference) =
        StorageHandler(preference = preference)


    @Provides
    @Singleton
    fun provideRepository(apolloClient: ApolloMaxClient): Repository {
        return Repository(apolloClient)
    }

    @Provides
    @Singleton
    fun providePreference(@ApplicationContext context: Context): AppPreference =
        AppPreference(context = context)
}