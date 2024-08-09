package core.net

import okhttp3.*

internal class SferaAuthenticator(private val user: String, private val pass: String) : Authenticator {
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

    fun getUser() = user

    fun getPass() = pass
}