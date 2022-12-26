import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItems;

public class APITestByRestAssured {
    @Test
    public void getBooks(){
        int statusCode= given()
                .queryParam("title","Git Pocket Guid")
                .when()
                .get("https://bookstore.toolsqa.com/BookStore/v1/Books")
                .getStatusCode();
        Assert.assertEquals(statusCode,200);

    }


    @Test
    public void getLastBook(){
        //get amount of books from api
        Response res = given()
                .when()
                .get("https://bookstore.toolsqa.com/BookStore/v1/Books");
        JsonPath j = new JsonPath(res.asString());

        //length of JSON array, as well as count start from 0
        // choose length-1
        int responseAmount = j.getInt("books.size()") -1;
        //conduct string for jsonPath
        String path="books["+responseAmount+"].isbn";
        String isbn= JsonPath.from(given()
                .queryParam("ISBN","9781593277574")
                        .when()
                                .get("https://bookstore.toolsqa.com/BookStore/v1/Books")
                                        .then()
                                                .log().all().extract().asString()).getString(path);
       System.out.println("print isbn :"+isbn);
       Assert.assertEquals(isbn,"9781593277574");

    }
    @Test
    public void getBookPage(){
        given()
                .when()
                .get("https://bookstore.toolsqa.com/BookStore/v1/Books")
                .then().log().all()
                .assertThat()
                .body("books[0,1].pages",hasItems(234,254));

    }

    /*@Test
    public void getBookPage(){
        given()
                .when()
                .get("https://bookstore.toolsqa.com/BookStore/v1/Books")
                .then().log().all()
                .assertThat()
                .body("books[0,1].pages",hasItems(234,254));

    }*/
}
