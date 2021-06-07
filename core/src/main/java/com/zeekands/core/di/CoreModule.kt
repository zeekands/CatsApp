package com.zeekands.core.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.zeekands.core.data.source.local.LocalDataSource
import com.zeekands.core.data.source.local.room.CatsDao
import com.zeekands.core.data.source.local.room.CatsDatabase
import com.zeekands.core.data.source.remote.RemoteDataSource
import com.zeekands.core.data.source.remote.network.ApiService
import com.zeekands.core.utils.AppExecutors
import com.zeekands.core.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    fun provideBaseURl() = Constant.BASE_URL


    @Singleton
    @Provides
    fun providesOkHttpClient() = run {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val hostname = "thecatapi.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/BRLkuw7PJ+gTJF8jGT44hom5l2ccwZg4emp8Pc+2ZV4=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .add(hostname, "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideCatsService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    private val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
    val factory = SupportFactory(passphrase)

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        CatsDatabase::class.java,
        "favourite_db.db"
    ).fallbackToDestructiveMigration()
        .openHelperFactory(factory)
        .build()

    @Singleton
    @Provides
    fun provideFavouriteCatDao(db: CatsDatabase) = db.catsDao()

    @Singleton
    @Provides
    fun provideLocalDataSource(dao: CatsDao) = LocalDataSource(dao)

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService) = RemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideAppExecutor() = AppExecutors()
}