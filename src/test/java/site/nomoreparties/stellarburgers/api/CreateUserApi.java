package site.nomoreparties.stellarburgers.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import site.nomoreparties.stellarburgers.RandomValue;
import site.nomoreparties.stellarburgers.api.json.UserRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;


public class CreateUserApi {
    UserApi userApi = new UserApi();
    RandomValue value = new RandomValue();
    UserRequest user = new UserRequest(value.email(), value.password(6), value.name(6));

    public UserRequest createUser() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        Response response = userApi.registerUser(user);
        response.then().assertThat().body("success", is(true))
                .and()
                .body("user.email", is(user.getEmail()))
                .and()
                .body("user.name", is(user.getName()))
                .and()
                .body("accessToken", notNullValue())
                .and()
                .body("refreshToken", notNullValue())
                .and()
                .statusCode(200);
        String email = user.getEmail();
        String password = user.getPassword();
        return new UserRequest(email, password, null);
    }
}
