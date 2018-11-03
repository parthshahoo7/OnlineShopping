package edu.shah.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.shah.web.webModel.WebAccount;

@Component
public class Validation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		WebAccount webAccount = (WebAccount) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (webAccount.getPassword().length() < 8 || webAccount.getPassword().length() > 32)
			errors.rejectValue("password", "Size.webAccount.password");
		if (!webAccount.getPassword().equals(webAccount.getConfimPassword()))
			errors.rejectValue("confimPassword", "Diff.webAccount.confimPassword");
	}
}