package com.example.shivam.erpsystem.Adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.shivam.erpsystem.Model.CategoryModel;
import com.example.shivam.erpsystem.Model.CustomerModel;
import com.example.shivam.erpsystem.R;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {

    private Context mContext;
    private List<CustomerModel> customerModelList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView customerID,customerName,customerEmail,customerMobileNumber,customerAddress,customerGst,customerPancard;
        public Button customerEdit,customerDelete;

        public MyViewHolder(View view) {
            super(view);
            customerID=view.findViewById(R.id.customerId);
                    customerName=view.findViewById(R.id.customerName);
            customerEmail=view.findViewById(R.id.customerEmail);
                    customerMobileNumber=view.findViewById(R.id.customerMobile);
            customerAddress=view.findViewById(R.id.customerAddress);
                    customerGst=view.findViewById(R.id.customerGSTNo);
            customerPancard=view.findViewById(R.id.customerPanCardNo);
            //customerEdit=view.findViewById(R.id.customerEdit);
              //      customerDelete=view.findViewById(R.id.customerDelete);
        }
    }
    public CustomerAdapter(Context mContext, List<CustomerModel> customerModelList) {
        this.mContext = mContext;
        this.customerModelList = customerModelList;
    }

    @Override
    public CustomerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.addandshowcustomer, parent, false);

        return new CustomerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomerAdapter.MyViewHolder holder, int position) {
        CustomerModel customerModel =customerModelList.get(position);
        holder.customerID.setText(customerModel.getCustomerID());
        holder.customerName.setText(customerModel.getCustomerName());
        holder.customerEmail.setText(customerModel.getCustomerEmail());
        holder.customerMobileNumber.setText(customerModel.getCustomerMobileNumber());
        holder.customerAddress.setText(customerModel.getCustomerAddress());
        holder.customerGst.setText(customerModel.getCustomerGst());
        holder.customerPancard.setText(customerModel.getCustomerPancard());
    }


    @Override
    public int getItemCount() {
        return customerModelList.size();
    }
}

