package com.example.base;

/**
 * Created by Skygge on 2018/10/02.
 * Presenter基类
 */
public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();


}
