package edu.shah;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import edu.shah.model.ProductDao;

@Controller
public class ProductController {
	ProductDao productdao = new ProductDao();

	@GetMapping(value = "/products", params = "id")
	public String getProductbyId(@RequestParam int id, Model model) {
		if (productdao.getProductById(id) == null) {
			model.addAttribute("products", productdao.getAllProducts());
			return "allProducts";
		}
		model.addAttribute("products", productdao.getProductById(id));
		return "product";
	}

	@GetMapping(value = "/products")
	public String allProducts(Model model) {
		model.addAttribute("products", new ProductDao().getAllProducts());
		return "allProducts";
	}

}
