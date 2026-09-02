package com.zepto.customer.response;

public class CustomerResponse {
	    private Long id;
	    private String customerName;
	    private String customerMobileNumber;
	    private String address;
	    private String customerType;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getCustomerName() {
	        return customerName;
	    }

	    public void setCustomerName(String customerName) {
	        this.customerName = customerName;
	    }

	    public String getCustomerMobileNumber() {
	        return customerMobileNumber;
	    }

	    public void setCustomerMobileNumber(String customerMobileNumber) {
	        this.customerMobileNumber = customerMobileNumber;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public String getCustomerType() {
	        return customerType;
	    }

	    public void setCustomerType(String customerType) {
	        this.customerType = customerType;
	    }
}
