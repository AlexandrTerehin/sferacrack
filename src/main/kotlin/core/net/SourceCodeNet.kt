package core.net

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import core.consts.UrlConsts
import core.settings.SferaSetting
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class SourceCodeNet {

    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private val client = Client(SferaSetting.user?.login.orEmpty(), SferaSetting.user?.password.orEmpty())

    private val retrofit = Retrofit.Builder()
        .baseUrl(UrlConsts.SFERA)
        .client(client.newBuilder().build())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun get(): Retrofit = retrofit

    fun getLogin() = client.getUser()

    fun getPass() = client.getPass()
}