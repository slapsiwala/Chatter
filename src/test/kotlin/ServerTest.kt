import com.chatter.rootModule
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import junit.framework.TestCase.assertTrue
import kotlin.test.Test
import kotlin.test.assertEquals

class ServerTest {

    @Test
    fun healthEndpointReturnsOk() = testApplication {
        application { rootModule() }
        val response = client.get("/health")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("OK", response.bodyAsText())
    }

    @Test
    fun `home page is served`() = testApplication {
        application { rootModule() }
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertTrue(response.bodyAsText().contains("Chatter"))
    }
}