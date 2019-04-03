package com.example.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Sandy Luo on 2016/12/8.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView, IAlertDialogView {
    protected String TAG = getClass().getSimpleName();

    protected Activity mContext;
    protected View mView;
    private Unbinder unbinder;
    private AlertDialog mProgressDialog;
    protected T mPresent;
    protected IAlertDialogPresent iAlertDialogPresent;


    public BaseFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        processBeforeInitView();
        iAlertDialogPresent = new IAlertDialogPresentImpl(this);
        Log.d(TAG, "onCreate");
    }

    protected void processBeforeInitView() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        if (getLayoutId() != -1) {
            mView = inflater.inflate(getLayoutId(), null);
            unbinder = ButterKnife.bind(this, mView);
        }
        initPresent();
        if (mPresent != null) {
            mPresent.attachView(this);
        }
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
        initView();
        initData();
        initListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initPresent() {
    }

    protected void initListener() {
    }

    protected int getLayoutId() {
        return -1;
    }

    /**
     * 调准到另外一个Activity
     *
     * @param clazz 对应的字节码
     */
    protected void skipAnotherActivity(Class<?> clazz) {
        Intent intent = new Intent(mContext, clazz);
        startActivity(intent);
    }

    protected void skipAnotherActivity(Bundle bundle, Class<?> clazz) {
        Intent intent = new Intent(mContext, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }

        if (mPresent != null) {
            mPresent.detachView();
        }
    }

    /**
     * 显示进度条的对话框
     */
    public void showProgressDialog() {
        mProgressDialog = new AlertDialog.Builder(mContext)
                .create();
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        Window window = mProgressDialog.getWindow();
        window.setContentView(R.layout.dialog_progress);
        window.setDimAmount(0f);
        window.setBackgroundDrawableResource(android.R.color.transparent);
    }

    /**
     * 隐藏进度条对话框
     */
    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    @Override
    public void showErrorMsg(String mErrorMsg) {
        if(TextUtils.isEmpty(mErrorMsg)){
            return;
        }
        showToast(mErrorMsg);
    }


    @Override
    public void showSuccess() {
        showToast(getResources().getString(R.string.Success));
    }

    @Override
    public void showFailed() {
        showToast(getResources().getString(R.string.Failed));
    }

    @Override
    public void stateError() {

    }

    @Override
    public void onPositiveButtonClicked(int titleId) {

    }

    @Override
    public void onNegativeButtonClicked(int titleId) {

    }

    @Override
    public void errorCode(int errorCode) {

    }
}
