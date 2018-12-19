package at.jku.trafficcontrol.trafficcontrolanddetection.contract

interface AuthenticationService {
    /**
     * Does the given [base64EncodedText] authorize a user?
     */
    fun isAuthenticated(base64EncodedText: String?): Boolean
}