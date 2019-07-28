package com.example.mvpdemo.presenter;

import android.util.Log;

import com.example.mvpdemo.model.ILoginView;
import com.example.mvpdemo.model.LoginLisentener;
import com.example.mvpdemo.model.LoginModel;
import com.example.mvpdemo.view.IView;

import java.lang.ref.WeakReference;

public class LoginPresenter extends PresenterFather {

    public LoginPresenter(ILoginView loginView) {
        this.mIModel = new LoginModel();
        this.mViewReference = new WeakReference<IView>(loginView);
    }

    public void login() {
        if (mIModel != null && mViewReference != null && mViewReference.get() != null) {
            ILoginView loginView = (ILoginView) mViewReference.get();
            String name = loginView.getUserName();
            String passWord = loginView.getPassWord();
            loginView = null;
            //此时LoginListener作为匿名内部类是持有外部类的引用的。
            ((LoginModel) mIModel).login(name, passWord, new LoginLisentener() {
                @Override
                public void onSuccess() {
                    if (mViewReference.get() != null) {
                        ((ILoginView) mViewReference.get()).onLoginSuccess();
                    }
                }

                @Override
                public void onFails() {
                    ((ILoginView) mViewReference.get()).onLoginFails();
                }
            });
        }
    }
}
