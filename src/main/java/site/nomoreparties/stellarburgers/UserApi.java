package site.nomoreparties.stellarburgers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import site.nomoreparties.stellarburgers.json.UserRequest;

import static io.restassured.RestAssured.given;

public class UserApi {

    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://stellarburgers.nomoreparties.site/").build();

    private final String register = "/api/auth/register";
    private final String login = "/api/auth/login";
    private final String authUser = "/api/auth/user";
    public Response registerUser(UserRequest user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(register);
    }

    public Response loginUser(UserRequest user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(login);
    }

    public Response deleteUser(String token) {
        return given()
                .header("Authorization", token)
                .delete(authUser);
    }
}
