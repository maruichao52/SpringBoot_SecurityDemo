package com.example.demo.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/***
 * 用户验证
 * @author 马瑞朝
 *
 */


@Component
@Slf4j
public class UserServiceDetail implements UserDetailsService {
	
	@Autowired
	private UserRepository userRep;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		
		User u = userRep.findByUsername(name);
		
		if (u == null) {
			
			throw new AuthenticationCredentialsNotFoundException("authError");
		}
		
		List<Role> role = roleRepository.findByUsername(name);
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		role.forEach(role1 -> authorities.addAll(AuthorityUtils.commaSeparatedStringToAuthorityList(role1.getRolename())));
		
		return new org.springframework.security.core.userdetails.User(u.getUsername(),u.getPassword(),authorities);
	}
}
