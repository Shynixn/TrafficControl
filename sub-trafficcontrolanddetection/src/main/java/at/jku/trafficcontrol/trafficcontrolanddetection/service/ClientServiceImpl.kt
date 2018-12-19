package at.jku.trafficcontrol.trafficcontrolanddetection.service

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.ClientService
import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder

/**
 * Wrapper service for creating a client.
 */
class ClientServiceImpl : ClientService {
    /**
     * Creates a new client.
     */
    override fun createClient(): Client {
        return ClientBuilder.newClient()
    }
}