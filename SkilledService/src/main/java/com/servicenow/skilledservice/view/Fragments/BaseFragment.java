package com.servicenow.skilledservice.view.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.servicenow.skilledservice.view.Activities.BaseActivity;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
public class BaseFragment extends Fragment {

    private BaseActivity baseActivity;

    public static BaseFragment newInstance(String param1, String param2) {
        BaseFragment fragment = new BaseFragment();
        return fragment;
    }

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        baseActivity = (BaseActivity) getActivity();
    }

    protected void showProgressDialog(Context context, String message) {
        if (baseActivity != null) {
            baseActivity.showProgressDialog(baseActivity, message);
        }
    }

    protected void hideProgressDialog() {
        if (baseActivity != null) {
            baseActivity.hideProgressDialog();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onStart() {
        super.onStart();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
