package com.example.mvpdemo.model;

public class LoginModel implements IModel {

    private String mUserName = "ma";
    private String mPassWord = "123";

    public void login(String username, String password, LoginLisentener lisentener) {

        if (lisentener == null) {
            return;
        }
        if (mUserName.equals(username)&&mPassWord.equals(password)){
            lisentener.onSuccess();
        }else {
            lisentener.onFails();
        }
    }
}
