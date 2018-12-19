package unittest

import at.jku.trafficcontrol.trafficcontrolanddetection.contract.ClientService
import at.jku.trafficcontrol.trafficcontrolanddetection.contract.ControlSystemService
import at.jku.trafficcontrol.trafficcontrolanddetection.entity.RequestHelpInformation
import at.jku.trafficcontrol.trafficcontrolanddetection.service.ControlSystemServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import java.net.URI
import java.nio.file.Files
import java.util.*
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.ws.rs.client.*
import javax.ws.rs.core.*

class ControlSystemServiceTest {
    /**
     * Given
     *  a mocked client
     * When
     *   a requestHelp is called
     * Then
     *   a call to controlSystem should be sent.
     */
    @Test
    fun requestHelp_MockedClient_ShouldPostCorrectPayload() {
        // Arrange
        val expectedResult = Files.readAllLines(File(Thread.currentThread().contextClassLoader.getResource("request-information.txt").toURI()).toPath()).joinToString()
        var actualResult: RequestHelpInformation? = null
        val classUnderTest = createWithDependencies { entity ->
            actualResult = entity.entity as RequestHelpInformation
            Response.ok().build()
        }

        // Act
        classUnderTest.requestHelp().get()

        // Assert
        Assertions.assertEquals(expectedResult, actualResult!!.payload)
    }

    companion object {
        fun createWithDependencies(function: (Entity<*>) -> Response): ControlSystemService {
            return ControlSystemServiceImpl(MockedClientService(function))
        }
    }

    class MockedClientService(private val function: (Entity<*>) -> Response) : ClientService {
        /**
         * Creates a new client.
         */
        override fun createClient(): Client {
            return MockedClient(function)
        }
    }

    class MockedInvocationBuilder(private val function: (Entity<*>) -> Response) : Invocation.Builder {
        override fun buildGet(): Invocation {
            throw IllegalArgumentException()
        }

        override fun head(): Response {
            throw IllegalArgumentException()
        }

        override fun method(p0: String?): Response {
            throw IllegalArgumentException()
        }

        override fun <T : Any?> method(p0: String?, p1: Class<T>?): T {
            throw IllegalArgumentException()
        }

        override fun <T : Any?> method(p0: String?, p1: GenericType<T>?): T {
            throw IllegalArgumentException()
        }

        override fun method(p0: String?, p1: Entity<*>?): Response {
            throw IllegalArgumentException()
        }

        override fun <T : Any?> method(p0: String?, p1: Entity<*>?, p2: Class<T>?): T {
            throw IllegalArgumentException()
        }

        override fun <T : Any?> method(p0: String?, p1: Entity<*>?, p2: GenericType<T>?): T {
            throw IllegalArgumentException()
        }

        override fun build(p0: String?): Invocation {
            throw IllegalArgumentException()
        }

        override fun build(p0: String?, p1: Entity<*>?): Invocation {
            throw IllegalArgumentException()
        }

        override fun async(): AsyncInvoker {
            throw IllegalArgumentException()
        }

        override fun get(): Response {
            throw IllegalArgumentException()
        }

        override fun <T : Any?> get(p0: Class<T>?): T {
            throw IllegalArgumentException()
        }

        override fun <T : Any?> get(p0: GenericType<T>?): T {
            throw IllegalArgumentException()
        }

        override fun cookie(p0: Cookie?): Invocation.Builder {
            throw IllegalArgumentException()
        }

        override fun cookie(p0: String?, p1: String?): Invocation.Builder {
            throw IllegalArgumentException()
        }

        override fun delete(): Response {
            throw IllegalArgumentException()
        }

        override fun <T : Any?> delete(p0: Class<T>?): T {
            throw IllegalArgumentException()
        }

        override fun <T : Any?> delete(p0: GenericType<T>?): T {
            throw IllegalArgumentException()
        }

        override fun buildPut(p0: Entity<*>?): Invocation {
            throw IllegalArgumentException()
        }

        override fun put(p0: Entity<*>?): Response {
            throw IllegalArgumentException()
        }

        override fun <T : Any?> put(p0: Entity<*>?, p1: Class<T>?): T {
            throw IllegalArgumentException()
        }

        override fun <T : Any?> put(p0: Entity<*>?, p1: GenericType<T>?): T {
            throw IllegalArgumentException()
        }

        override fun property(p0: String?, p1: Any?): Invocation.Builder {
            throw IllegalArgumentException()
        }

        override fun options(): Response {
            throw IllegalArgumentException()
        }

        override fun <T : Any?> options(p0: Class<T>?): T {
            throw IllegalArgumentException()
        }

        override fun <T : Any?> options(p0: GenericType<T>?): T {
            throw IllegalArgumentException()
        }

        override fun acceptLanguage(vararg p0: Locale?): Invocation.Builder {
            throw IllegalArgumentException()
        }

        override fun acceptLanguage(vararg p0: String?): Invocation.Builder {
            throw IllegalArgumentException()
        }

        override fun buildPost(p0: Entity<*>?): Invocation {
            throw IllegalArgumentException()
        }

        override fun cacheControl(p0: CacheControl?): Invocation.Builder {
            throw IllegalArgumentException()
        }

        override fun header(p0: String?, p1: Any?): Invocation.Builder {
            throw IllegalArgumentException()
        }

        override fun accept(vararg p0: String?): Invocation.Builder {
            throw IllegalArgumentException()
        }

        override fun accept(vararg p0: MediaType?): Invocation.Builder {
            throw IllegalArgumentException()
        }

        override fun buildDelete(): Invocation {
            throw IllegalArgumentException()
        }

        override fun headers(p0: MultivaluedMap<String, Any>?): Invocation.Builder {
            throw IllegalArgumentException()
        }

        override fun trace(): Response {
            throw IllegalArgumentException()
        }

        override fun <T : Any?> trace(p0: Class<T>?): T {
            throw IllegalArgumentException()
        }

        override fun <T : Any?> trace(p0: GenericType<T>?): T {
            throw IllegalArgumentException()
        }

        override fun acceptEncoding(vararg p0: String?): Invocation.Builder {
            throw IllegalArgumentException()
        }

        override fun post(p0: Entity<*>?): Response {
            return function.invoke(p0!!)
        }

        override fun <T : Any?> post(p0: Entity<*>?, p1: Class<T>?): T {
            throw IllegalArgumentException()
        }

        override fun <T : Any?> post(p0: Entity<*>?, p1: GenericType<T>?): T {
            throw IllegalArgumentException()
        }
    }

    class MockedWebTarget(private val function: (Entity<*>) -> Response) : WebTarget {
        override fun resolveTemplatesFromEncoded(p0: MutableMap<String, Any>?): WebTarget {
            throw IllegalArgumentException()
        }

        override fun path(p0: String?): WebTarget {
            throw IllegalArgumentException()
        }

        override fun property(p0: String?, p1: Any?): WebTarget {
            throw IllegalArgumentException()
        }

        override fun resolveTemplate(p0: String?, p1: Any?): WebTarget {
            throw IllegalArgumentException()
        }

        override fun resolveTemplate(p0: String?, p1: Any?, p2: Boolean): WebTarget {
            throw IllegalArgumentException()
        }

        override fun resolveTemplates(p0: MutableMap<String, Any>?): WebTarget {
            throw IllegalArgumentException()
        }

        override fun resolveTemplates(p0: MutableMap<String, Any>?, p1: Boolean): WebTarget {
            throw IllegalArgumentException()
        }

        override fun getUri(): URI {
            throw IllegalArgumentException()
        }

        override fun getUriBuilder(): UriBuilder {
            throw IllegalArgumentException()
        }

        override fun queryParam(p0: String?, vararg p1: Any?): WebTarget {
            throw IllegalArgumentException()
        }

        override fun getConfiguration(): Configuration {
            throw IllegalArgumentException()
        }

        override fun register(p0: Class<*>?): WebTarget {
            throw IllegalArgumentException()
        }

        override fun register(p0: Class<*>?, p1: Int): WebTarget {
            throw IllegalArgumentException()
        }

        override fun register(p0: Class<*>?, vararg p1: Class<*>?): WebTarget {
            throw IllegalArgumentException()
        }

        override fun register(p0: Class<*>?, p1: MutableMap<Class<*>, Int>?): WebTarget {
            throw IllegalArgumentException()
        }

        override fun register(p0: Any?): WebTarget {
            throw IllegalArgumentException()
        }

        override fun register(p0: Any?, p1: Int): WebTarget {
            throw IllegalArgumentException()
        }

        override fun register(p0: Any?, vararg p1: Class<*>?): WebTarget {
            throw IllegalArgumentException()
        }

        override fun register(p0: Any?, p1: MutableMap<Class<*>, Int>?): WebTarget {
            throw IllegalArgumentException()
        }

        override fun matrixParam(p0: String?, vararg p1: Any?): WebTarget {
            throw IllegalArgumentException()
        }

        override fun resolveTemplateFromEncoded(p0: String?, p1: Any?): WebTarget {
            throw IllegalArgumentException()
        }

        override fun request(): Invocation.Builder {
            return MockedInvocationBuilder(function)
        }

        override fun request(vararg p0: String?): Invocation.Builder {
            throw IllegalArgumentException()
        }

        override fun request(vararg p0: MediaType?): Invocation.Builder {
            throw IllegalArgumentException()
        }
    }

    class MockedClient(private val function: (Entity<*>) -> Response) : Client {
        override fun target(p0: String?): WebTarget {
            return MockedWebTarget(function)
        }

        override fun target(p0: URI?): WebTarget {
            throw IllegalArgumentException()
        }

        override fun target(p0: UriBuilder?): WebTarget {
            throw IllegalArgumentException()
        }

        override fun target(p0: Link?): WebTarget {
            throw IllegalArgumentException()
        }

        override fun getConfiguration(): Configuration {
            throw IllegalArgumentException()
        }

        override fun getHostnameVerifier(): HostnameVerifier {
            throw IllegalArgumentException()
        }

        override fun register(p0: Class<*>?): Client {
            throw IllegalArgumentException()
        }

        override fun register(p0: Class<*>?, p1: Int): Client {
            throw IllegalArgumentException()
        }

        override fun register(p0: Class<*>?, vararg p1: Class<*>?): Client {
            throw IllegalArgumentException()
        }

        override fun register(p0: Class<*>?, p1: MutableMap<Class<*>, Int>?): Client {
            throw IllegalArgumentException()
        }

        override fun register(p0: Any?): Client {
            throw IllegalArgumentException()
        }

        override fun register(p0: Any?, p1: Int): Client {
            throw IllegalArgumentException()
        }

        override fun register(p0: Any?, vararg p1: Class<*>?): Client {
            throw IllegalArgumentException()
        }

        override fun register(p0: Any?, p1: MutableMap<Class<*>, Int>?): Client {
            throw IllegalArgumentException()
        }

        override fun property(p0: String?, p1: Any?): Client {
            throw IllegalArgumentException()
        }

        override fun invocation(p0: Link?): Invocation.Builder {
            throw IllegalArgumentException()
        }

        override fun close() {
            throw IllegalArgumentException()
        }

        override fun getSslContext(): SSLContext {
            throw RuntimeException()
        }
    }
}