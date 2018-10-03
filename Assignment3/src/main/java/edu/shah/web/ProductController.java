package edu.shah.web;

import java.security.Principal;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.shah.db.ProductDao;
import edu.shah.db.CategoryDao;
import edu.shah.db.LocationDao;
import edu.shah.model.Product;

@Controller
public class ProductController {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private LocationDao locationDao;

	@Autowired
	private CategoryDao categoryDao;

//==========================RequestMapping for "/403"=====================================
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("message", "Sorry " + username + "You don't have privileges to view this page !!!");
		return "error/403";
	}

//==========================Default Page===================================================
//	Add this function to set home page 
	@GetMapping("/")
	public String gethomePage() {
		return "redirect:/home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String searchProductForm(@Valid Product product, BindingResult bindingResult, Model model) {
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
	public String displayAllProducts(Model model) {
		model.addAttribute("products", productDao.getAllProducts());
		return "allProducts";
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
		return "redirect:/product?id=" + product.getId();
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
		return "redirect:/product?id=" + product.getId();
	}
//================================Find Product==================================================

	@RequestMapping(value = "/product", params = "id")
	public String getProductbyId(@RequestParam int id, Model model) {
		if (productDao.getProductById(id) == null) {
			model.addAttribute("products", productDao.getAllProducts());
			return "allProducts";
		}
		model.addAttribute("products", productDao.getProductById(id));
		return "product";
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
