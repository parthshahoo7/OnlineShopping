package edu.shah.web;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.shah.db.AccountDao;
import edu.shah.model.Account;
import edu.shah.validator.Validation;

@Controller
public class AccountController {
	@Autowired
	private AccountDao accountDao;

	@Autowired
	private Validation validation;

//============================ Account Registration================================
	@GetMapping("/register")
	public String registerPage(@Valid WebAccount webAccount, Model model) {
		model.addAttribute("webAccount", webAccount);
		return "register";
	}

	@PostMapping(value = "/register")
	public String doLogin(@Valid WebAccount webAccount, final BindingResult result, Model model) {
		validation.validate(webAccount, result);
		if (result.hasErrors()) {
			model.addAttribute("webAccount", webAccount);
			return "register";
		} else {
			String rawPassword = webAccount.getPassword();
			webAccount.setPassword(encodePassword(webAccount.getPassword()));
			accountDao.createAccount(webAccount);
			System.out.println("UserName & Password:" + webAccount.getAccount().getUserName() + "-" + rawPassword);
			autologin(webAccount);
			return "registerHome";
		}
	}

	private String encodePassword(String rawPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encryptedPassword = passwordEncoder.encode(rawPassword);
		return encryptedPassword;
	}

//=====================Auto Login Feature============================================
	private void autologin(WebAccount webAccount) {
		Authentication auth = new UsernamePasswordAuthenticationToken(webAccount.getAccount().getEmail(), null,
				getGrantedAuthorities(webAccount));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	private Collection<? extends GrantedAuthority> getGrantedAuthorities(WebAccount webAccount) {
		// TODO Auto-generated method stub
		return AuthorityUtils
				.createAuthorityList(webAccount.getAccount().getAuthority().stream().toArray(size -> new String[size]));
	}
//========================= Delete Account If ADMIN===================================

	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.GET)
	public String deleteProductForm(@Valid Account account, BindingResult bindingResult, Model model) {
		return "deleteCustomer";
	}

	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
	public String deleteProduct(@Valid Account account, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "deleteCustomer";
		}

		accountDao.deleteAccount(account.getEmail());
		return "registerHome";
	}

//========================= Model Attribute==============================================
	@ModelAttribute
	public void attribute(Model model) {
		model.addAttribute("allRoles", WebAccount.getAllRoles());
	}

}
