package org.example.springappproject.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

// // ユーザ認証に失敗したら401を返す
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        // ステータスコードを401に設定
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // レスポンスのContent-TypeをJSONに設定
        response.setContentType("application/json;charset=UTF-8");

        // レスポンスボディにカスタムJSONを出力
        PrintWriter out = response.getWriter();
        out.flush();
        out.close();
    }
}
