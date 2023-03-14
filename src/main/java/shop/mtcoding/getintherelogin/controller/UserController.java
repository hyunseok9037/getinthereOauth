package shop.mtcoding.getintherelogin.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserController {

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/callback")
    public @ResponseBody String callback(String code) {
        // 1. code 값 존재 유무 확인
        if (code == null || code.isEmpty()) {
            return "bad Request";
        }

        // 2. code 값 카카오 전달 -> access token 받기
        String kakaoUrl = "https://kauth.kakao.com/oauth/token";
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> xForm = new LinkedMultiValueMap<>();
        xForm.add("grant_type", "authorization_code");
        xForm.add("client_id", "c854b81fcab245fc6a94f6709fe9886a");
        xForm.add("redirect_uri", "http://localhost:8080/callback"); // 2차 검증
        xForm.add("code", code); // 핵심

        HttpEntity<?> httpEntity = new HttpEntity<>(xForm, headers);

        ResponseEntity<String> responseEntity = rt.exchange(kakaoUrl, HttpMethod.POST, httpEntity, String.class);

        // 3. access token으로 카카오의 홍길동 resource 접근 가능해짐
        String responseBody = responseEntity.getBody();

        // 4. access token으로 파싱하고

        // 5. access token으로 email 정보 받기 (ssar@gamil.com)

        // 6. 해당 email로 회원가입되어 있는지 user 정보가 있는지 DB 조회 (X)

        // 7. 있으면 그 user 정보로 session 만들어주고, (자동로그인) (X)

        // 8. 없으면 강제 회원가입 시키고, 그 정보로 session 만들어주고, (자동로그인)

        return responseBody;
    }
}