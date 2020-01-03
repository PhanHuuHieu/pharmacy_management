package pharmacy.management.common;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.ModelMap;

import pharmacy.management.bean.WebUtils;

public class CommonContanst {
	public static final String YEAR_START = "2016";

	public static void setBlank(String s0, String s1, String s2) {
		if (s0.equals("null")) {
			s0 = "0";
		}
		if (s1.equals("null")) {
			s1 = "0";
		}
		if (s2.equals("null")) {
			s2 = "0";
		}
	}

	public static void displayUsername(ModelMap modelMap, Principal principal) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.toString(loginedUser);
		modelMap.addAttribute("username_admin", userInfo);
		String role = WebUtils.getRole(loginedUser);
		modelMap.addAttribute("name_role", role);
		if (role.equals("ROLE_ADMIN")) {
			modelMap.addAttribute("status", 1);
		} else {
			modelMap.addAttribute("status", 0);
		}
	}
}
