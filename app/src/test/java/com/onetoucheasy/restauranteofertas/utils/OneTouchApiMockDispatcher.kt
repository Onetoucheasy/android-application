package com.onetoucheasy.restauranteofertas.utils

import com.google.common.io.Resources
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.File
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit
// used in BaseNetworkMockTest
class OneTouchApiMockDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/api/restaurantsWithOffer" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/restaurantsWithOffer.json"))
//                    .setBody(getJson("/Users/ericolsson/AndroidStudioProjects/AndroidAdvanced/app/src/main/resources/json/heros.json"))
            }
            else -> MockResponse().throttleBody(1024, 5, TimeUnit.SECONDS)
        }
    }

}

internal fun getJson(path: String): String {
    val uri = Resources.getResource(path)
    val file = File(uri.path)
    return String(file.readBytes())
}