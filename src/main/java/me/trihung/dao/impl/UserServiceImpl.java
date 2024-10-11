package me.trihung.dao.impl;

import me.trihung.dao.IUserDAO;
import me.trihung.models.UserModel;
import me.trihung.services.IUserService;

public class UserServiceImpl implements IUserService {

	IUserDAO userDao = new UserDAOImpl();

	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.get(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public boolean register(String username, String email, String password, String fullname, String phone) {

//		long millis = System.currentTimeMillis();
//		Date date = new Date(millis);
		if (checkExistEmail(email) || checkExistUsername(username))
			return false;
		userDao.insert(new UserModel(username, email, password, fullname, phone));
		return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public UserModel get(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	public boolean isValidPassword(String password) {
		password = password.strip();
		return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
	}

}