package com.example.spotonassignment.java

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.spotonassignment.common.Constants
import com.example.spotonassignment.data.remote.Api
import com.example.spotonassignment.data.repository.MainRepository
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@HiltAndroidTest
@RunWith(JUnit4::class)
class MainViewModelTest {
    init {
        System.setProperty("javax.net.ssl.trustStoreType", "JKS")
    }

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()
    private val server = MockWebServer()
    lateinit var repository: MainRepository
    private  lateinit var mockedResponse: String

    val gson = GsonBuilder()
        .setLenient()
        .create()

    @Before
    fun setup() {
        server.start(8080)
        var BASE_URL = server.url("/").toString()
        val okHttpClient = OkHttpClient.Builder().build()
        val api: Api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build().create(Api::class.java)

        repository = MainRepository(api)

    }

    @Test
    fun getDataFromRepositoryTest() {
        mockedResponse = Constants.rawResponse
        server.enqueue(MockResponse().setResponseCode(200).setBody(mockedResponse))
        val response = runBlocking { repository.api.getCryptoitem() }
        val json = gson.toJson(response)

        val resultResponse = JsonParser.parseString(json)
        val expectedresponse = JsonParser.parseString(mockedResponse)
        Assert.assertNotNull(response)
        Assert.assertTrue(resultResponse.equals(expectedresponse))
    }
}