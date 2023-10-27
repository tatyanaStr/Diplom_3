package site.nomoreparties.stellarburgers.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {
    public Response registerUser(site.nomoreparties.stellarburgers.api.json.UserRequest user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post("/api/auth/register");
    }

    public Response loginUser(site.nomoreparties.stellarburgers.api.json.UserRequest user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post("/api/auth/login");
    }

    public Response deleteUser(String token) {
        return given()
                .header("Authorization", token)
                .delete("/api/auth/user");
    }
}
