package com.example.mvpdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvpdemo.MainActivity;
import com.example.mvpdemo.R;
import com.example.mvpdemo.model.ILoginView;
import com.example.mvpdemo.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    private EditText mUserNameEdit;
    private EditText mPassWordEdit;
    private Button mLoginBtn;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setView();
        setData();
    }

    private void setData() {
        this.mPresenter = new LoginPresenter(this);
    }

    private void setView() {
        this.mUserNameEdit = findViewById(R.id.et_username);
        this.mPassWordEdit = findViewById(R.id.et_password);
        this.mLoginBtn = findViewById(R.id.btn_login);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //隐藏软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

                mPresenter.login();
            }
        });
    }

    @Override
    public String getUserName() {
        return this.mUserNameEdit.getText().toString().trim();
    }

    @Override
    public String getPassWord() {
        return this.mPassWordEdit.getText().toString().trim();
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(getApplicationContext(), "登陆成功！", Toast.LENGTH_LONG).show();
        Intent intent=new Intent(LoginActivity.this, MainActivity.class); startActivity(intent);
    }

    @Override
    public void onLoginFails() {
        Toast.makeText(getApplicationContext(), "登录失败！", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //记得在销毁的时候断掉引用链，养成良好的习惯
        this.mPresenter = null;
    }
}
