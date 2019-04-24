package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, String>  {

	
	/***
	 * 查询用户
	 * 
	 * @Author MRC
	 * @Date 2019年4月24日 下午9:30:28
	 * @param userName
	 * @return
	 */
	User findByUsername(String name);
}
