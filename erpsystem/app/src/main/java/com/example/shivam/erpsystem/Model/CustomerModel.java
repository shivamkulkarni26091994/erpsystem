package com.example.shivam.erpsystem.Model;

public class CustomerModel {
    public CustomerModel(String customerID, String customerName, String customerEmail, String customerMobileNumber, String customerAddress, String customerGst, String customerPancard) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerMobileNumber = customerMobileNumber;
        this.customerAddress = customerAddress;
        this.customerGst = customerGst;
        this.customerPancard = customerPancard;
    }

    private String customerID,customerName,customerEmail,customerMobileNumber,customerAddress,customerGst,customerPancard;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerMobileNumber() {
        return customerMobileNumber;
    }

    public void setCustomerMobileNumber(String customerMobileNumber) {
        this.customerMobileNumber = customerMobileNumber;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerGst() {
        return customerGst;
    }

    public void setCustomerGst(String customerGst) {
        this.customerGst = customerGst;
    }

    public String getCustomerPancard() {
        return customerPancard;
    }

    public void setCustomerPancard(String customerPancard) {
        this.customerPancard = customerPancard;
    }
}
