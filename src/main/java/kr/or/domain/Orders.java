package kr.or.domain;

public class Orders { //주문
    private int orderNumber;
    private int customerNumber;
    private int productNumber;

    public Orders() {}
    
	public Orders(int orderNumber, int customerNumber, int productNumber) {
		this.orderNumber = orderNumber;
		this.customerNumber = customerNumber;
		this.productNumber = productNumber;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}
	
}
