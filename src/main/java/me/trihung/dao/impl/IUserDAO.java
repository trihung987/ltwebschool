package me.trihung.dao.impl;

import java.util.List;

import me.trihung.models.UserModel;

public interface IUserDAO {
	List<UserModel> findAll();

	UserModel findById(int id);

	void insert(UserModel user);
	
	UserModel findByUserName(String username);
	
	UserModel findByEmail(String email);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
	String findRoleByID(int id);
	
	void insert(UserModel user, String role);
	
	boolean changePasswordByMail(String mail, String password);
	
}