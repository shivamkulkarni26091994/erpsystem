package com.example.shivam.erpsystem.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shivam.erpsystem.Model.ProgressModel;
import com.example.shivam.erpsystem.R;

import java.util.List;

public class PieChartAdapter extends RecyclerView.Adapter<PieChartAdapter.MyViewHolder> {
    private Context mContext;
    private List<ProgressModel> ProgressList;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.piechart, viewGroup, false);
        return new MyViewHolder(itemView);
    }
public PieChartAdapter(Context context,List<ProgressModel> progressList)
{
    this.mContext=context;
    this.ProgressList=progressList;
}
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
    ProgressModel progressModel=ProgressList.get(i);
    myViewHolder.paidamount.setProgress(progressModel.getPaidamount());
    myViewHolder.pendingamount.setProgress(progressModel.getPendingamount());
    myViewHolder.percentage.setText(progressModel.getPercentage());
    }

    @Override
    public int getItemCount() {
        return ProgressList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView percentage;
        public ProgressBar paidamount,pendingamount;

        public MyViewHolder(View view) {
            super(view);
            paidamount = view.findViewById(R.id.stats_progressbar);
            pendingamount=view.findViewById(R.id.background_progressbar);
            percentage=view.findViewById(R.id.number_of_calories);
        }

    }

}
