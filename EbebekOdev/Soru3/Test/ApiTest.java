import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiTest
{
    @Test
    public void isProductInfoAPICallReturnStatusCode200() throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://api2.e-bebek.com/ebebekwebservices/v2/ebebek/products/INC-000074?fields=DEFAULT")).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        if(statusCode != 200){
            Assert.fail("Status code not equal to 200");
        }
    }

    @Test
    public void isUserRegisterAPICallReturnStatusCode201() throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://api-nodejs-todolist.herokuapp.com/user/register")).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString("{\n" +
                "\t\"name\": \"duptest998\",\n" +
                "\t\"email\": \"duptest998@gmail.com\",\n" +
                "\t\"password\": \"12345678\",\n" +
                "\t\"age\": 1\n" +
                "}")).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        System.out.println(statusCode);
        if(statusCode != 201){
            Assert.fail("Status code not equal to 201");
        }
    }
}
