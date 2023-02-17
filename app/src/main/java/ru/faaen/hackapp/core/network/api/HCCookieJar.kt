package ru.faaen.hackapp.core.network.api

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class HCCookieJar: CookieJar {

    private val cookies: HashMap<String, List<Cookie>> = hashMapOf()

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookies[url.host] ?: listOf()
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        this.cookies[url.host] = cookies
    }
}