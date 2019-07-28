package com.example.mvpdemo.model;

import com.example.mvpdemo.view.IView;

/**
 * 写一个接口继承IView
 */
public interface ILoginView extends IView {
    String getUserName();
    String getPassWord();
    void onLoginSuccess();
    void onLoginFails();
}
