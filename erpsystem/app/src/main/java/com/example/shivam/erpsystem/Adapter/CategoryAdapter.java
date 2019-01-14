package com.example.shivam.erpsystem.Adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.shivam.erpsystem.Model.CategoryModel;
import com.example.shivam.erpsystem.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

private Context mContext;
private List<CategoryModel> categoryModelList;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView categoryId,categoryName;
    public AppCompatButton categoryEdit,categoryDelete;

    public MyViewHolder(View view) {
        super(view);
        categoryId=view.findViewById(R.id.categoryId);
        categoryName = view.findViewById(R.id.categoryName);
        categoryEdit= view.findViewById(R.id.categoryEdit);
        categoryDelete = view.findViewById(R.id.categoryDelete);
    }
}
    public CategoryAdapter(Context mContext, List<CategoryModel> categoryModelList) {
        this.mContext = mContext;
        this.categoryModelList = categoryModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.addandshowcategorylayout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       CategoryModel categoryModel=categoryModelList.get(position);
       holder.categoryId.setText(categoryModel.getCategory_id());
       holder.categoryName.setText(categoryModel.getCategory_name());
    }


    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }
}
