package org.example.springappproject.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// // ユーザ認証に失敗したら401を返す
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // 認証対象のURLリスト
    private final List<String> protectedUrls = List.of(
            "/users/",
            "/posts/"
    );

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        String uri = request.getRequestURI();

        boolean isProtected = protectedUrls.stream().anyMatch(uri::startsWith);


        // 指定したURL以外は404にする
        if (isProtected) {
            // 認証対象URL → 401 Unauthorized
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);


            // ステータスコードを401に設定
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            // レスポンスのContent-TypeをJSONに設定
            response.setContentType("application/json;charset=UTF-8");

            // レスポンスボディにカスタムJSONを出力
            PrintWriter out = response.getWriter();
            out.flush();
            out.close();
        }else {
            // 認証対象外 → 404 Not Found
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
