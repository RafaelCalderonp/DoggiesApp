package com.example.doggiesapp

import android.util.Log
import com.example.model.remote.ApiInterface
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

@RunWith(JUnit4::class)
class NetworkTest {

    private lateinit var service : ApiInterface
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    @After
    fun shutDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun requestBreedList() {
        runBlocking {
            enqueueResponse("api-response.json")
            val resultResponse = service.fetchBreedListCorutinas()
            val request = mockWebServer.takeRequest()
            assertNotNull(resultResponse)
            //assertThat(request.body, `is` ("/breeds/list/" ))
            assertThat(request.path, `is` ("/breeds/list/" ))
        }
    }


    @Test
    fun requestListItemString(){
        
    }


    private fun enqueueResponse (filename: String, headers: Map<String, String> = emptyMap()) {
        val source = getJson(filename)
        val mockResponse = MockResponse()
        // si tuvieramos headers
        for ((key, value ) in headers) {
          //  mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(source))
    }


    private fun getJson(path: String): String {
        val uri = javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
      //  Log.d("GetJson", file.readBytes().toString())
        return String(file.readBytes())
    }


}