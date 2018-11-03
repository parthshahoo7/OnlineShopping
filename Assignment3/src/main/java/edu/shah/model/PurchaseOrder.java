package edu.shah.model;

import java.util.ArrayList;

public class PurchaseOrder {
	private String customerName;
	private String purchaseOrderDate;
	private String totalCost;
	private ArrayList<String> productName = new ArrayList<>();
	private ArrayList<String> quantity = new ArrayList<>();
	private Long po_id;
	private int line_no;

	public Long getPo_id() {
		return po_id;
	}

	public void setPo_id(Long po_id) {
		this.po_id = po_id;
	}

	public PurchaseOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PurchaseOrder(String customer_name, String po_date, String totalCost, ArrayList<String> prod_name,
			ArrayList<String> quantity) {
		super();
		this.customerName = customer_name;
		this.purchaseOrderDate = po_date;
		this.totalCost = totalCost;
		this.productName = prod_name;
		this.quantity = quantity;
	}

	public ArrayList<String> getProd_name() {
		return productName;
	}

	public void setProd_name(ArrayList<String> prod_name) {
		this.productName = prod_name;
	}

	public String getCustomer_name() {
		return customerName;
	}

	public void setCustomer_name(String customer_name) {
		this.customerName = customer_name;
	}

	public String getPo_date() {
		return purchaseOrderDate;
	}

	public void setPo_date(String po_date) {
		this.purchaseOrderDate = po_date;
	}

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	public ArrayList<String> getQuantity() {
		return quantity;
	}

	public void setQuantity(ArrayList<String> quantity) {
		this.quantity = quantity;
	}

	public int getLine_no() {
		return line_no;
	}

	public void setLine_no(int line_no) {
		this.line_no = line_no;
	}

	public void addQuantity(String quantity) {
		this.quantity.add(quantity);
	}

	public void addProduct_name(String prod_name) {
		this.productName.add(prod_name);
	}
}