package pharmacy.management.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pharmacy.management.entity.TAppRole;
import pharmacy.management.entity.TEmployee;
import pharmacy.management.respository.AppRoleRepository;
import pharmacy.management.respository.EmployeeRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private EmployeeRepository employee;
	@Autowired
	private AppRoleRepository appRole;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<TEmployee> listEmployee = employee.findUserAccount(username);
		List<TAppRole> listRole = appRole.getNameRole(String.valueOf(listEmployee.get(0).getId()));
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		for (TAppRole role : listRole) {
			// ROLE_USER, ROLE_ADMIN,..
			GrantedAuthority authority = new SimpleGrantedAuthority(role.getRole_name());
			grantList.add(authority);
		}
		UserDetails userDetails = (UserDetails) new User(listEmployee.get(0).getName(), //
				listEmployee.get(0).getPassword(), grantList);
		return userDetails;
	}

}
