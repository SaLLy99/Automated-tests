import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import io.restassured.response.Response;
import java.security.SecureRandom;


public class TestLoginAndAuthorizationOnApi {
    String baseURI = "https://bookstore.toolsqa.com/Account/v1/User";

    @Test
    public void CheckInvalidCredentials(){
        String requestBody = "{\"userName\":\"1234\",\"password\":\"test!\"}";
        Response response = null;

        try {
            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .post(baseURI);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());

        assertEquals(400, response.getStatusCode());

    }

    @Test
    public void CheckInvalidPassword(){
        String requestBody = "{\"userName\":\"Tes23t \",\"password\":\"Test1!\"}";

        Response response = null;

        try {
            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .post(baseURI);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());

        assertEquals(400, response.getStatusCode());

    }

    @Test
    public void CheckValidCredentials() {
        String generateUsername=generateRandomUsername(9);

        //String requestBody = "{\"userName\":\"+generateUsername+"\"+","+"\"+"password\":\"+generatePassword+"\}";
        //String requestBody = "{\"userName\":\"Tes23t345345345 \",\"password\":\"Test1!4534534535\"}";
        String requestBody = new JSONObject()
                .put("userName", generateUsername)
                .put("password", "Test123456789!")
                .toString();
        Response response = null;

        try {
            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .post(baseURI);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());
        assertEquals(201, response.getStatusCode());

    }

    public static String generateRandomUsername(int len)
    {
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@!1234567890";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }
}
