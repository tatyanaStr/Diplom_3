package site.nomoreparties.stellarburgers;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import site.nomoreparties.stellarburgers.json.UserRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;


public class CreateUserApi {
    UserApi userApi = new UserApi();
    Faker value = new Faker();
    UserRequest user = new UserRequest(value.internet().emailAddress(), value.internet().password(6, 10), value.name().firstName());

    public UserRequest createUser() {
        RestAssured.requestSpecification = UserApi.requestSpec;
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
