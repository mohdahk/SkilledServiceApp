package com.servicenow.skilledservice.view.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.servicenow.skilledservice.model.Worker;
import com.servicenow.skilledservice.view.Activities.BaseActivity;
import com.servicenow.skilledservice.view.Adapters.SearchWorkerAdapter;
import com.servicenow.skilledservice.viewmodel.SearchWorkersViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import skilledservice.servicenow.com.R;

public class SearchWorkersFragment extends BaseFragment {

    private SearchWorkersViewModel searchWorkViewModel;
    private SearchWorkerAdapter adapter;
    private BaseActivity baseActivity;
    private View mRootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        mRootView =  inflater.inflate(R.layout.fragment_searchworkers, container, false);
        return mRootView;
    }



    public static BaseFragment newInstance(){
        return new SearchWorkersFragment();
    }

    private void intializeUI(){
        final RecyclerView workersListView = mRootView.findViewById(R.id.workers_list);
        workersListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        workersListView.setHasFixedSize(true);
        this.adapter = new SearchWorkerAdapter(getActivity());
        workersListView.setAdapter(this.adapter);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intializeUI();
        baseActivity = (BaseActivity) getActivity();

        this.searchWorkViewModel = ViewModelProviders.of(this).get(SearchWorkersViewModel.class);
        this.searchWorkViewModel.getWorkersData().observe(this, new Observer<List<Worker>>() {
            @Override
            public void onChanged(List<Worker> workers) {

                baseActivity.showProgressDialog(getActivity(),getResources().getString(R.string.loading));

                if(workers != null || !workers.isEmpty()){
                    adapter.setData(workers);
                }

                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        baseActivity.hideProgressDialog();
                    }
                },2000) ;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuInflater inflater1 = getActivity().getMenuInflater();
        inflater1.inflate(R.menu.menu, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.app_bar_search));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(onQueryTextListener);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setLayoutParams(new ActionBar.LayoutParams(Gravity.LEFT));
        super.onCreateOptionsMenu(menu, inflater);
    }

    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String skill) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String skill) {

            searchWorkViewModel.searchWorkersData(skill.toLowerCase()).observe(getActivity(), new Observer<List<Worker>>() {
                @Override
                public void onChanged(List<Worker> workers) {
                    adapter.setData(workers);
                }
            });

            return false;
        }
    };
}

