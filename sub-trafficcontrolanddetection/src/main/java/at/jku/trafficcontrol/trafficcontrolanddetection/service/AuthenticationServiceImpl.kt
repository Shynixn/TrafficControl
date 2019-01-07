package at.jku.trafficcontrol.trafficcontrolanddetection.service

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.AuthenticationService
import java.nio.charset.Charset
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default

/**
 * Authentication service.
 */
@Default
@ApplicationScoped
class AuthenticationServiceImpl : AuthenticationService {
    private val userName: String = "master-user"
    private val password: String = "horse-staple"

    /**
     * Does the given [base64EncodedText] authorize a user?
     */
    override fun isAuthenticated(base64EncodedText: String?): Boolean {
        if (base64EncodedText == null) {
            return false
        }

        val encodedText = Base64.getDecoder().decode(base64EncodedText.replace("Basic", "").trim()).toString(Charset.forName("UTF-8"))
        val givenUserName = encodedText.split(":")[0]
        val givenPassword = encodedText.split(":")[1]

        return givenUserName == this.userName && givenPassword == this.password
    }
}