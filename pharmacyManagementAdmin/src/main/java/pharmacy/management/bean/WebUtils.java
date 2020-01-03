package pharmacy.management.bean;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class WebUtils {

	public static String toString(User user) {
		StringBuilder sb = new StringBuilder();
		sb.append(user.getUsername());
		return sb.toString();
	}

	public static String getRole(User user) {
		StringBuilder sb = new StringBuilder();
		Collection<GrantedAuthority> authorities = user.getAuthorities();
		if (authorities != null && !authorities.isEmpty()) {
			boolean first = true;
			for (GrantedAuthority a : authorities) {
				if (first) {
					sb.append(a.getAuthority());
					first = false;
				}
			}
		}
		return sb.toString();
	}
}