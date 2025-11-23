package org.example.springappproject.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
// base64のutilサービスクラス
public class Base64Service {

    // リクエストヘッダーからトークンを取得
    public String getBase64FromRequest(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token != null && token.startsWith("Basic ")){
            return token.substring(6);
        }
        return null;
    }

    // userIdとパスワードを配列で返す
    // [userId, password]
    public String[] getUserIdAndPassword(String token) {
        if (token == null) {
            return null;
        }
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(token);
            String decoded = new String(decodedBytes, StandardCharsets.UTF_8);

            // userId:password に分割
            String[] parts = decoded.split(":", 2);
            if (parts.length != 2) {
                return null; // 不正な形式
            }
            return parts; // [0]=username, [1]=password
        } catch (IllegalArgumentException e) {
            // Base64 decode に失敗した場合は null
            return null;
        }
    }



}
