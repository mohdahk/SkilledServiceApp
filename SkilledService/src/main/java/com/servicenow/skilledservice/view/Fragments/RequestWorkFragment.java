package com.servicenow.skilledservice.view.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.servicenow.skilledservice.model.WorkRequest;
import com.servicenow.skilledservice.model.Worker;
import com.servicenow.skilledservice.utils.AppConstants;
import com.servicenow.skilledservice.viewmodel.RequestWorkersViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import skilledservice.servicenow.com.R;

public class RequestWorkFragment extends BaseFragment {

    private View mRootView;
    private Worker workerObject;
    private TextView nameValue;
    private TextView skillvalue;
    private TextView ratingValue;
    private Toolbar toolbar;
    private Button reqWorkBtn;
    private RequestWorkersViewModel requestWorkViewModel;

    public static RequestWorkFragment newInstance(Worker worker) {
        Bundle args = new Bundle();
        args.putParcelable(AppConstants.KEY_WORKER, worker);
        RequestWorkFragment fragment = new RequestWorkFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            workerObject = getArguments().getParcelable(AppConstants.KEY_WORKER);
        }
        this.requestWorkViewModel = ViewModelProviders.of(this).get(RequestWorkersViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_requestwork, container, false);
        initializeUI();
        return mRootView;
    }

    private void initializeUI() {
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(getActivity().getResources().getString(R.string.requestWork));
        nameValue = mRootView.findViewById(R.id.name_value);
        nameValue.setText(workerObject.getName());
        skillvalue = mRootView.findViewById(R.id.skill_value);
        skillvalue.setText(workerObject.getSpecialization());
        ratingValue = mRootView.findViewById(R.id.rating_value);
        ratingValue.setText(Long.toString(workerObject.getRatings()));
        reqWorkBtn = mRootView.findViewById(R.id.request_work);
        reqWorkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestWorkViewModel.createRequest(workerObject.getName(),workerObject.getId(),workerObject.getSpecialization());

                LiveData<List<WorkRequest>> workRequestLiveData = requestWorkViewModel.GetRequestData();
                   requestWorkViewModel.GetRequestData().observe(getActivity(), new Observer<List<WorkRequest>>() {
                       @Override
                       public void onChanged(List<WorkRequest> workRequests) {

                       }
                   });

            }
        });
    }
}
