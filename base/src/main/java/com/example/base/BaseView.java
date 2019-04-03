package com.example.base;

/**
 * Created by skygge on 2018/10/02.
 * View基类
 */
public interface BaseView {

    void showSuccess();

    void showFailed();

    void showErrorMsg(String mErrorMsg);

    void stateError();

    void showProgressDialog();

    void dismissProgressDialog();

    void errorCode(int errorCode);
}
