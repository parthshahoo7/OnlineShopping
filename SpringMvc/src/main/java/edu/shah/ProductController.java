package edu.shah;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.shah.model.Product;
import edu.shah.model.ProductDao;

@Controller
public class ProductController {
	ProductDao productdao = new ProductDao();

	@RequestMapping(value = "/product", params = "id")
	public String getProductbyId(@RequestParam int id, Model model) {
		if (productdao.getProductById(id) == null) {
			model.addAttribute("products", productdao.getAllProducts());
			return "allProducts";
		}
		model.addAttribute("products", productdao.getProductById(id));
		return "product";
	}

	@RequestMapping(value = "/product")
	public String allProducts(Model model) {
		model.addAttribute("products", productdao.getAllProducts());
		return "allProducts";
	}

//==================================Assignment 2=========================================

	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public String showProductForm(@Valid Product product, BindingResult bindingResult) {
		return "productForm";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProduct(@Valid Product product, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "productForm";
		}
		productdao.addProduct(product);
		return "redirect:/product?id=" + product.getId();
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String confirm(@RequestParam("id") int id, Model model) {
		model.addAttribute("products", productdao.getProductById(id));
		return "product";
	}

//==================================Model Attribute==========================================

	@ModelAttribute("allCategory")
	public ArrayList<String> getAllCategory() {
		ArrayList<String> list = new ArrayList<>();
		list.add("ELECTRONIC");
		list.add("CLOTH");
		list.add("BOOK");
		return list;
	}

	@ModelAttribute("allWarehouse")
	public ArrayList<String> getAllWarehouse() {
		ArrayList<String> list = new ArrayList<>();
		list.add("WareHouse1");
		list.add("WareHouse2");
		list.add("WareHouse3");
		return list;
	}

	@ModelAttribute("allDiscount")
	public ArrayList<String> getAllDiscount() {
		ArrayList<String> list = new ArrayList<>();
		list.add("YES");
		list.add("NO");
		return list;
	}
}
