package edu.shah.web.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.shah.db.ProductDao;
import edu.shah.db.PurchaseOrderDao;
import edu.shah.db.CategoryDao;
import edu.shah.db.LocationDao;
import edu.shah.model.LineItem;
import edu.shah.model.Product;
import edu.shah.model.PurchaseOrder;
import edu.shah.model.ShoppingCart;

@Scope("session")
@Controller
public class ProductController {
	private LineItem lineItem;

	@Autowired
	private ShoppingCart shoppingCart = null;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private LocationDao locationDao;

	@Autowired
	private CategoryDao categoryDao;

	private PurchaseOrder purchaseOrder;

	@Autowired
	private PurchaseOrderDao purchaseOrderDao;

	private int totalCost = 0;

// ==========================RequestMapping for "/403"=====================================
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("message", "Sorry " + username + " you don't have privileges to view this page !!!");
		return "error/403";
	}

//=========================Product Search Page Implement to create relate to registration==============
	@RequestMapping(value = "/productSearch", method = RequestMethod.GET)
	public String searchProductForm1(@Valid Product product, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "productSearch";
		}
		model.addAttribute("product", new Product());
		return "productSearch";
	}

	@RequestMapping(value = "/productSearch", method = RequestMethod.POST)
	public String searchProduct1(@Valid Product product, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "productSearch";
		}
		return "redirect:/product?id=" + product.getId();
	}

//==========================Default Page===================================================
//====================	Add this function to set home page 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String gethomePage(@Valid Product product, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "home";
		}
		model.addAttribute("product", new Product());
		return "redirect:/home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String searchProductForm(@Valid Product product, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "home";
		}
		model.addAttribute("product", new Product());
		return "home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String searchProduct(@Valid Product product, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "home";
		}
		return "redirect:/product?id=" + product.getId();
	}

//=======================List Product=======================================================
	@GetMapping("/listProducts")
	public String displayAllProducts(ShoppingCart shoppingCart, Model model) {
		List<Product> products = productDao.getAllProducts();
		for (int i = 0; i < products.size(); i++) {
			lineItem = new LineItem();
			lineItem.setProduct(products.get(i));
			shoppingCart.addLineItems(lineItem);
		}
		model.addAttribute("products", shoppingCart.getLineItems());
		return "allProducts";
	}

//=======================Add To Cart===========================================================
	@RequestMapping(value = "/listProducts", method = RequestMethod.POST)
	public String addToCart(@RequestParam(name = "id") int id, @Valid @ModelAttribute ShoppingCart shoppingCart,
			BindingResult bindingResult, Model model) {
		List<Product> products = productDao.getAllProducts();
		for (int i = 0; i < products.size(); i++) {
			lineItem = new LineItem();
			lineItem.setProduct(products.get(i));
			shoppingCart.addLineItems(lineItem);
		}
		if (bindingResult.hasErrors()) {
			model.addAttribute("products", shoppingCart.getLineItems());
			return "allProducts";
		}
		Product product = new Product();
		lineItem = new LineItem();
		product = productDao.getProductById(id);
		lineItem.setProduct(product);
		if (this.shoppingCart.getLineItems().isEmpty())
			lineItem.getOrderItem().setQuantity(shoppingCart.getQuantity());
		else {
			for (int i = 0; i < this.shoppingCart.getLineItems().size(); i++) {
				if (product.getId() == this.shoppingCart.getLineItems().get(i).getProduct().getId()) {
					int quantity = shoppingCart.getQuantity();
					lineItem.getOrderItem().setQuantity(
							quantity + this.shoppingCart.getLineItems().get(i).getOrderItem().getQuantity());
					this.shoppingCart.getLineItems().remove(i);
				} else {
					lineItem.getOrderItem().setQuantity(shoppingCart.getQuantity());
				}
			}
		}
		this.shoppingCart.addLineItems(lineItem);
		shoppingCart.setQuantity(0);
		model.addAttribute("products", shoppingCart.getLineItems());
		return "allProducts";
	}

//===================== View Cart===================================================
	@RequestMapping(value = "/viewCart", method = RequestMethod.GET)
	public String viewCart(@Valid ShoppingCart shoppingCart, BindingResult bindingResult, Model model) {
		int totalCost = 0;
		for (LineItem lineItem : this.shoppingCart.getLineItems()) {
			totalCost = totalCost
					+ (Integer.parseInt(lineItem.getProduct().getCost()) * lineItem.getOrderItem().getQuantity());
		}
		shoppingCart = this.shoppingCart;
		this.totalCost = totalCost;
		model.addAttribute("shoppingCart", shoppingCart.getLineItems());
		model.addAttribute("totalCost", this.totalCost);
		return "viewCart";
	}

//=======================CheckOut=====================================================
	@RequestMapping(value = "/viewCart", method = RequestMethod.POST)
	public String checkOut(@Valid ShoppingCart shoppingCart, BindingResult bindingResult, Model model,
			Principal principal) {
		purchaseOrder = new PurchaseOrder();
		if (bindingResult.hasErrors()) {
			model.addAttribute("shoppingCart", shoppingCart.getLineItems());
			model.addAttribute("totalCost", totalCost);
			return "viewCart";
		}
		purchaseOrder.setCustomer_name(principal.getName());
		purchaseOrder.setPo_date(LocalDate.now().toString());
		purchaseOrder.setTotalCost(Integer.toString(totalCost));
		for (int i = 0; i < this.shoppingCart.getLineItems().size(); i++) {
			purchaseOrder.addProduct_name(this.shoppingCart.getLineItems().get(i).getProduct().getName());
			purchaseOrder.addQuantity(
					Integer.toString(this.shoppingCart.getLineItems().get(i).getOrderItem().getQuantity()));
		}
		this.shoppingCart.clear();
		shoppingCart.clear();
		purchaseOrderDao.createOrder(purchaseOrder);
		model.addAttribute("purchaseOrders", purchaseOrderDao.getAllOrders());
		return "redirect:/checkout?id=" + purchaseOrder.getPo_id();
	}

//=====================Purchase Order Page================================================
	@RequestMapping(value = "/checkout", params = "id", method = RequestMethod.GET)
	public String checkOutConfirm(@RequestParam int id, @Valid PurchaseOrder purchaseOrder, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("purchaseOrders", purchaseOrderDao.getPurchaseOrderById(Long.valueOf(id)));
			return "checkOut";
		}
		if (id == 0) {
			model.addAttribute("purchaseOrders", purchaseOrder);
			model.addAttribute("totalCost", purchaseOrder.getTotalCost());
			return "checkOut";
		}
		List<PurchaseOrder> purchaseOrders = purchaseOrderDao.getPurchaseOrderById(Long.valueOf(id));
		model.addAttribute("purchaseOrders", purchaseOrders);
		model.addAttribute("totalCost", purchaseOrders.get(0).getTotalCost());
		return "checkOut";
	}

	@RequestMapping(value = "/checkout", params = "id", method = RequestMethod.POST)
	public String orderDetails(@RequestParam int id, @Valid PurchaseOrder purchaseOrder, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("purchaseOrders", purchaseOrderDao.getAllOrders());
			return "browsePurchaseOrders";
		}
		List<PurchaseOrder> purchaseOrders = purchaseOrderDao.getPurchaseOrderById(Long.valueOf(id));
		model.addAttribute("purchaseOrders", purchaseOrders);
		model.addAttribute("totalCost", purchaseOrders.get(0).getTotalCost());
		return "checkOut";
	}

//======================= OrderHistory==========================================
	@RequestMapping(value = "/orderHistory", method = RequestMethod.GET)
	public String orderHistory(@Valid PurchaseOrder purchaseOrder, BindingResult bindingResult, Model model,
			Principal principal) {
		model.addAttribute("purchaseOrders", purchaseOrderDao.getPurchaseOrderByCustomerName(principal.getName()));
		return "OrderHistory";
	}

//========================BrowsePurchaseOrders For Admin=====================================
	@RequestMapping(value = "/browsePurchaseOrders", method = RequestMethod.GET)
	public String viewPurchaseOrders(@Valid PurchaseOrder purchaseOrder, BindingResult bindingResult, Model model) {
		model.addAttribute("purchaseOrders", purchaseOrderDao.getAllOrders());
		return "browsePurchaseOrders";
	}

//=====================	Add Product==========================================================
	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public String addProductForm(@Valid Product product, BindingResult bindingResult, Model model) {
		model.addAttribute("allWarehouse", locationDao.getAllLocation());
		model.addAttribute("allCategory", categoryDao.getAllCategory());
		return "productForm";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProduct(@Valid Product product, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("allWarehouse", locationDao.getAllLocation());
			model.addAttribute("allCategory", categoryDao.getAllCategory());
			return "productForm";
		}
		model.addAttribute("allWarehouse", locationDao.getAllLocation().toString());
		model.addAttribute("allCategory", categoryDao.getAllCategory().toString());
		productDao.createProduct(product);
		return "redirect:/addedProduct?id=" + product.getId();
	}

//===============================Display Added Product=========================================
	@RequestMapping(value = "/addedProduct", params = "id")
	public String getAddedProductById(@RequestParam int id, Model model) {
		if (productDao.getProductById(id) == null) {
			return "redirect:/products";
		}
		model.addAttribute("products", productDao.getProductById(id));
		return "addedProduct";
	}

//===============================Delete Product================================================
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	public String deleteProductForm(@Valid Product product, BindingResult bindingResult, Model model) {
		return "deleteProduct";
	}

	@RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
	public String deleteProduct(@Valid Product product, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "deleteProduct";
		}
		productDao.deleteProduct(product);
		return "redirect:/deleteProductConfirmation";
	}

//================================ Delete Product Confirmation==================================
	@RequestMapping(value = "/deleteProductConfirmation", method = RequestMethod.GET)
	public String deleteProductConfirmation(@Valid Product product, BindingResult bindingResult, Model model) {
		return "deleteProductConfirmation";
	}

//================================Find Product==================================================
	@RequestMapping(value = "/product", params = "id")
	public String getProductbyId(@RequestParam int id, Model model) {
		if (productDao.getProductById(id) == null) {
			return "redirect:/products";
		}
		model.addAttribute("products", productDao.getProductById(id));
		return "product";
	}

//================================Display All Product If not Search Product Found================
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String displayAllProducts(@Valid Product product, BindingResult bindingResult, Model model) {
		List<Product> products = productDao.getAllProducts();
		model.addAttribute("products", products);
		return "products";
	}

//==================================Model Attribute==============================================
	@ModelAttribute("allDiscount")
	public ArrayList<String> getAllDiscount() {
		ArrayList<String> list = new ArrayList<>();
		list.add("YES");
		list.add("NO");
		return list;
	}
}