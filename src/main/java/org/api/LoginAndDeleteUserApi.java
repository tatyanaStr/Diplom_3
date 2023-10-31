package org.api;

import io.restassured.RestAssured;
import org.api.json.UserRequest;
import org.api.json.UserResponse;

public class LoginAndDeleteUserApi {
    UserApi userApi = new UserApi();
    public void deleteUser(UserRequest user) {
        RestAssured.requestSpecification = UserApi.requestSpec;
        var response = userApi.loginUser(user);
        var token = response.body().as(UserResponse.class).getAccessToken();
        userApi.deleteUser(token);
    }
}
