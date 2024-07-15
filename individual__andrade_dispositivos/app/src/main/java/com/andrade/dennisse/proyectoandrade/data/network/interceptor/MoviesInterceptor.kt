package com.andrade.dennisse.proyectoandrade.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class MoviesInterceptor : Interceptor {

    private val key = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNzlmM2QyMWNjM2NmYTgxNjZjNWY4OGQ1ZTdjMzYzYSIsIm5iZiI6MTcyMDg3NTg2NC4yNzc0Mywic3ViIjoiNjY2MWM3NmQ4Y2UwYWVjNGRjMDBjMWUyIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.vdZguCD8AWWz0E8JVO6uDRyj5aGhxBVeCfNYbdyx2Ao"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder().build()

            val headers = request.headers.newBuilder()
            headers.add("Authorization", key)

        val newRequest = request.newBuilder().url(url).headers(headers.build()).build()

        return chain.proceed(newRequest)
    }
}
