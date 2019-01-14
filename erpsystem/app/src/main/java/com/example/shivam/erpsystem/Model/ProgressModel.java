package com.example.shivam.erpsystem.Model;

import android.widget.ProgressBar;

public class ProgressModel {
    public ProgressModel(int paidamount, int pendingamount, String percentage) {
        this.paidamount = paidamount;
        this.pendingamount = pendingamount;
        this.percentage = percentage;
    }

    public int getPaidamount() {
        return paidamount;
    }

    public void setPaidamount(int paidamount) {
        this.paidamount = paidamount;
    }

    public int getPendingamount() {
        return pendingamount;
    }

    public void setPendingamount(int pendingamount) {
        this.pendingamount = pendingamount;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    int paidamount,pendingamount;
    String percentage;
}
