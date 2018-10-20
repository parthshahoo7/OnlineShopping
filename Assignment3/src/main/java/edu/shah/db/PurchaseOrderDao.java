package edu.shah.db;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.shah.model.PurchaseOrder;

@Repository
public interface PurchaseOrderDao {
	public PurchaseOrder createOrder(PurchaseOrder purchaseOrder);

	public int deleteOrder(PurchaseOrder purchaseOrder);

	public List<PurchaseOrder> getAllOrders();

	public List<PurchaseOrder> getPurchaseOrderById(Long long1);

	public List<PurchaseOrder> getPurchaseOrderByCustomerName(String name);

}
