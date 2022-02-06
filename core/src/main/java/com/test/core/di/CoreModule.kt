package com.test.core.di

import androidx.room.Room
import com.arrayyan.core.utils.AppExecutors
import com.test.core.data.ContentRepository
import com.test.core.data.source.local.LocalDataSource
import com.test.core.data.source.local.room.PostDatabase
import com.test.core.data.source.remote.RemoteDataSource
import com.test.core.data.source.remote.network.ApiService
import com.test.core.domain.repository.IContentRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<PostDatabase>().postDao() }
    factory { get<PostDatabase>().userDao() }
    factory { get<PostDatabase>().commentDao() }
    factory { get<PostDatabase>().albumDao() }

    single {
        Room.databaseBuilder(
            androidContext(),
            PostDatabase::class.java, "Post.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get(), get(), get(), get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IContentRepository> { ContentRepository(get(), get(), get()) }
}