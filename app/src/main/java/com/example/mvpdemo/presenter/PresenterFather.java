package com.example.mvpdemo.presenter;

import com.example.mvpdemo.model.IModel;
import com.example.mvpdemo.view.IView;

import java.lang.ref.WeakReference;

/**
 * 所有Presenter的父类，因为presenter会持有View以及Model部分，
 * 所以都写在总父类中
 */
public class PresenterFather {
    protected IModel mIModel;
    protected WeakReference<IView> mViewReference;
}
