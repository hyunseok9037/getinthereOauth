package shop.mtcoding.getintherelogin.dto;

@Setter
@Getter
public class TokenProperties {
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private int expiresIn;
    private String scope;
    private int refreshTokenExpiresIn;
}