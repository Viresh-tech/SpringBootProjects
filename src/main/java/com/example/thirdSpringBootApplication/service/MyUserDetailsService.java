package com.example.thirdSpringBootApplication.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.thirdSpringBootApplication.controller.StudentController;

@Service
public class MyUserDetailsService implements UserDetailsService {

	Map<String, User> userList = new HashMap<String, User>();

	private void loadUsers() {
		userList.put("viresh", new User("viresh", "dev", getGrantedAuthority("ADMIN")));
		userList.put("abc", new User("abc", "xyz", getGrantedAuthority("USER")));
		userList.put("John", new User("John", "Mccane", getGrantedAuthority("USER")));
	}

	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {

		loadUsers();

		User u1 = userList.get(user);
		Set<GrantedAuthority> set = (Set<GrantedAuthority>) u1.getAuthorities();
		Iterator<GrantedAuthority> it = set.iterator();

		while (it.hasNext()) {

			SimpleGrantedAuthority a = (SimpleGrantedAuthority) it.next();
			StudentController.currentRole = a.toString();

		}

		return u1;

	}

	public List<GrantedAuthority> getGrantedAuthority(String authority) {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(new SimpleGrantedAuthority(authority));

		return list;

	}

}
