package me.trihung.services;

import me.trihung.models.UserModel;

public interface IUserService {
	
	public UserModel login(String username, String password);

	public UserModel get(String username);

	boolean register(String username, String email, String password, String fullname, String phone);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);
	
	boolean isValidPassword(String password);

}