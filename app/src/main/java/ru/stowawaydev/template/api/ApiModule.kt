package ru.stowawaydev.template.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.stowawaydev.template.BuildConfig

/**
 * template header (replace it)
 */

private const val BASE_URL = BuildConfig.API_ENDPOINT

fun apiModule() = Kodein.Module("api_module") {
    bind<Retrofit.Builder>() with singleton { retrofitBuilder(instance()) }
    bind<OkHttpClient>() with singleton { okHttpClient() }
    // Backend API
    bind<BackendApi>() with singleton { backendApi(instance(), instance()) }
}

private fun retrofitBuilder(gson: Gson) = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .baseUrl(BASE_URL)

private fun okHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        builder.addInterceptor(logging)
    }
    return builder.build()
}

private fun backendApi(builder: Retrofit.Builder, okHttpClient: OkHttpClient): BackendApi =
    builder.client(okHttpClient)
        .build()
        .create(BackendApi::class.java)
