package org.example.springappproject.auth;
import org.springframework.util.AntPathMatcher;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

// base64認証を行うフィルタークラス
@Component
public class Base64AuthenticationFilter extends OncePerRequestFilter {

    private final Base64Service base64Service;
    private final AuthenticationManager authenticationManager;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    public Base64AuthenticationFilter(Base64Service base64Service, AuthenticationManager authenticationManager) {
        this.base64Service = base64Service;
        this.authenticationManager = authenticationManager;
    }

    // 認証対象のURLリスト
    private final List<String> protectedUrls = List.of(
            "/users/**",
            "/close/**"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 認証が必要ないもとは認証しない
        String requestPath = request.getRequestURI();
        boolean isPermitted = protectedUrls.stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, requestPath));

        if (!isPermitted) {
            filterChain.doFilter(request, response);
            return;
        }


        //　リクエストからトークンを取得
        String token = base64Service.getBase64FromRequest(request);

        // トークンが存在しない
        if(token == null){
            sendUnauthorizedResponse(response);
            return;
        }

        // 認証除法を取得
        String[] authData = base64Service.getUserIdAndPassword(token);
        if(authData == null){
            sendUnauthorizedResponse(response);
            return;
        }
        String userId = authData[0];
        String password = authData[1];

        try {
            // 認証処理
            UsernamePasswordAuthenticationToken authRequest =  new UsernamePasswordAuthenticationToken(userId, password);

            Authentication authResult = authenticationManager.authenticate(authRequest);


            // 認証成功
            SecurityContextHolder.getContext().setAuthentication(authResult);

        } catch (AuthenticationException ex) {
            // 認証失敗
            sendUnauthorizedResponse(response);
        }

        // フィルター連鎖の続行
        filterChain.doFilter(request, response);
    }


    // 401レスポンスを返すメソッド
    private void sendUnauthorizedResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\":\"Authentication failed\"}");
    }
}
