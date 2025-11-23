package org.example.springappproject.exception;

// トークンとパスパラメータのUserIdか異なった時の例外
public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(){
        super();
    }
}
