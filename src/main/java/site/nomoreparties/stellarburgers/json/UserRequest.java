package site.nomoreparties.stellarburgers.json;

import lombok.Getter;
import lombok.Setter;

public class UserRequest {
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String name;

    public UserRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public UserRequest(){}

}
