package core.settings

internal object SferaSetting {

    private var _user: SferaUser? = null

    fun getUser() = _user

    fun setUser(user: SferaUser) {
        _user = user
    }
}