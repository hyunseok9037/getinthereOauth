package shop.mtcoding.getintherelogin.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id;
    private String username; // kakao_ssar@nate.com
    private String password; // UUID
    private String eamil; // ssar@nate.com
    private String provider; // me
}
