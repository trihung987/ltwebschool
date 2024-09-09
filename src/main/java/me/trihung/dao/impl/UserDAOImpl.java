package me.trihung.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import me.trihung.config.DBConnectSQLServer;
import me.trihung.models.UserModel;

public class UserDAOImpl extends DBConnectSQLServer implements IUserDAO {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<UserModel> findAll() {
		String sql = "select * tableUser";
		List<UserModel> list = new ArrayList<>();
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
						rs.getString("password"), rs.getString("image"), rs.getString("fullname")));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserModel findByEmail(String email) {
		String sql = "SELECT * tableUser WHERE email = ?";
		UserModel oneUser = new UserModel();

		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				oneUser.setId(rs.getInt("id"));
				oneUser.setUsername(rs.getString("username"));
				oneUser.setFullname(rs.getString("fullname"));
				oneUser.setEmail(rs.getString("email"));
				oneUser.setPassword(rs.getString("password"));
				oneUser.setImages(rs.getString("image"));
			}
			return oneUser;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}

	@Override
	public UserModel findById(int Id) {
		String sql = "SELECT * FROM tableUser WHERE id = ?";
		UserModel oneUser = new UserModel();

		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Id);
			rs = ps.executeQuery();
			while (rs.next()) {
				oneUser.setId(rs.getInt("id"));
				oneUser.setUsername(rs.getString("username"));
				oneUser.setFullname(rs.getString("fullname"));
				oneUser.setEmail(rs.getString("email"));
				oneUser.setPassword(rs.getString("password"));
				oneUser.setImages(rs.getString("image"));
			}
			return oneUser;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}

	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO tableUser (username, email, password, fullname) VALUES (?, ?, ?, ?)";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getFullname());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkExistEmail(String email) {
		UserModel user = null;
		user = this.findByEmail(email);
		if (user == null)
			return false;
		return true;
	}

	@Override
	public boolean checkExistUsername(String username) {
		UserModel user = null;
		user = this.findByUserName(username);
		if (user == null)
			return false;
		return true;
	}

	public static void main(String[] arg) {
		UserDAOImpl userDAO = new UserDAOImpl();
		UserModel user = userDAO.findByEmail("trihung@gmail.com");
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		System.out.println(user.getImages());
		System.out.println(user.getFullname());

	}

	@Override
	public UserModel findByUserName(String username) {
		String sql = "SELECT * FROM tableUser WHERE username = ?";
		UserModel oneUser = new UserModel();

		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				oneUser.setId(rs.getInt("id"));
				oneUser.setUsername(rs.getString("username").strip());
				oneUser.setFullname(rs.getString("fullname").strip());
				oneUser.setEmail(rs.getString("email").strip());
				oneUser.setPassword(rs.getString("password").strip());
				oneUser.setImages(rs.getString("image"));
			}
			return oneUser;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}

}