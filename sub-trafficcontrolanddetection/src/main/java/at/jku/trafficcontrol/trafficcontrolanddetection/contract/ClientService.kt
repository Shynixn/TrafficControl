package at.jku.trafficcontrol.trafficcontrolanddetection.contract

import javax.ws.rs.client.Client

/**
 * Wrapper service for creating a client.
 */
interface ClientService {
    /**
     * Creates a new client.
     */
    fun createClient(): Client
}