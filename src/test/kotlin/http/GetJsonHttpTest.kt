import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class ApiTest {

    private val client = OkHttpClient()

    @Test
    fun testInts() {
        val req = Request.Builder().url("http://localhost:8080/api/ints").build()
        val res = client.newCall(req).execute()
        assertEquals("[1.0,2.0,3.0]", res.body?.string()?.trim())
    }

    @Test
    fun testPair() {
        val req = Request.Builder().url("http://localhost:8080/api/pair").build()
        val res = client.newCall(req).execute()
        assertEquals("""{"first":"um","second":"dois"}""", res.body?.string()?.trim())
    }

    @Test
    fun testPath() {
        val req = Request.Builder().url("http://localhost:8080/api/path/abc").build()
        val res = client.newCall(req).execute()
        assertEquals(""""abc!"""", res.body?.string()?.trim())
    }

    @Test
    fun testArgs() {
        val req = Request.Builder().url("http://localhost:8080/api/args?n=3&text=PA").build()
        val res = client.newCall(req).execute()
        assertEquals("""{"PA":"PAPAPA"}""", res.body?.string()?.trim())
    }
}
