package core.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

internal class Client(private val login: String, private val password: String) : OkHttpClient() {

    private val interceptor = HttpLoggingInterceptor()
        .apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

    override fun newBuilder() = Builder()
        .authenticator(SferaAuthenticator(login, password))
        .addInterceptor(interceptor)

    fun getUser() = login
    fun getPass() = password
}