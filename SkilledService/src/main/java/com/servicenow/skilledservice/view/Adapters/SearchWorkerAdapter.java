package com.servicenow.skilledservice.view.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.servicenow.skilledservice.model.Worker;
import com.servicenow.skilledservice.utils.AppConstants;
import com.servicenow.skilledservice.view.Fragments.BaseFragment;
import com.servicenow.skilledservice.view.Fragments.RequestWorkFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import skilledservice.servicenow.com.R;

public class SearchWorkerAdapter extends RecyclerView.Adapter<SearchWorkerAdapter.WorkerViewHolder> {

    private Context context;
    private List<Worker> workerList = new ArrayList<>();


    public SearchWorkerAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.workerlistitem, parent, false);
        return new WorkerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerViewHolder holder, int position) {
            final Worker worker = workerList.get(position);
            holder.tvWokersName.setText(worker.getName());
            holder.tvRatings.setText(Long.toString(worker.getRatings()));
            holder.tvWorkersSpecialization.setText(worker.getSpecialization());
            holder.workerItemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        LaunchRequestWorkFragment(worker);
                }
            });
    }

    @Override
    public int getItemCount() {
        return workerList.size();
    }

    public void setData(List<Worker> workerList) {
        this.workerList = workerList;
        notifyDataSetChanged();
    }

    private BaseFragment LaunchRequestWorkFragment(Worker worker){
        BaseFragment RequestWorkFrag = RequestWorkFragment.newInstance(worker);

        if(!((Activity)context).isFinishing())
         {
            FragmentTransaction transaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, RequestWorkFrag, RequestWorkFrag.getClass().getCanonicalName());
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.addToBackStack(AppConstants.KEY_REQWORKFRAG);
            transaction.commit();
        }
        return RequestWorkFrag;
    }

    class WorkerViewHolder extends RecyclerView.ViewHolder {

        TextView tvWokersName;
        TextView tvWorkersSpecialization;
        TextView tvRatings;
        LinearLayout workerItemLayout;

        private WorkerViewHolder(View itemView) {
            super(itemView);

            tvWokersName = itemView.findViewById(R.id.worker_name);
            tvWorkersSpecialization = itemView.findViewById(R.id.worker_specialization);
            tvRatings = itemView.findViewById(R.id.worker_ratings);
            workerItemLayout = itemView.findViewById(R.id.ll_workeritem);

        }
    }
}
