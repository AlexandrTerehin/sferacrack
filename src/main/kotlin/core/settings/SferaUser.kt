package core.settings

/**
 * Sfera user
 *
 * @property login **********@corp.dev.vtb
 * @property email ************@dev.vtb.ru
 * @property password *********8
 */
internal data class SferaUser(
    val login: String,
    val email: String,
    val password: String
)