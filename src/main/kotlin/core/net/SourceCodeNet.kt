package core.net

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import core.consts.UrlConsts
import core.settings.SferaSetting
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


internal class SourceCodeNet {
    private val interceptor = HttpLoggingInterceptor()
        .apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    private val client = OkHttpClient.Builder()
        .authenticator(SferaAuthenticator(SferaSetting.getUser().login, SferaSetting.getUser().password))
        .addInterceptor(interceptor)
        .build()

    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(UrlConsts.SFERA)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun get(): Retrofit = retrofit
}

private class SferaAuthenticator(private val user: String, private val pass: String) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val request: Request = response.request
        return if (request.header("Authorization") != null) {
            null
        } else {
            request.newBuilder()
                .header("Authorization", Credentials.basic(user, pass))
                .build()
        }
    }
}