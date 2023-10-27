package site.nomoreparties.stellarburgers.api;

import io.restassured.RestAssured;
import site.nomoreparties.stellarburgers.api.json.UserResponse;
import site.nomoreparties.stellarburgers.api.json.UserRequest;

public class LoginAndDeleteUserApi {
    UserApi userApi = new UserApi();
    public void deleteUser(UserRequest user) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        var response = userApi.loginUser(user);
        var token = response.body().as(UserResponse.class).getAccessToken();
        userApi.deleteUser(token);
    }
}
