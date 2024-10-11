package me.trihung.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import me.trihung.config.DBConnectSQLServer;
import me.trihung.dao.IUserDAO;
import me.trihung.models.UserModel;

public class UserDAOImpl extends DBConnectSQLServer implements IUserDAO {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<UserModel> findAll() {
		String sql = "select * FROM tableUser";
		List<UserModel> list = new ArrayList<>();
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String phone = rs.getString("phonenumber");
				list.add(new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
						rs.getString("password"), rs.getString("image"), rs.getString("fullname"),
						findRoleByID(rs.getInt("id")), phone));
			}
			if (list.size()!=0)
				return list;
		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public UserModel findByEmail(String email) {
		String sql = "SELECT * FROM tableUser WHERE email = ?";
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
				oneUser.setPhone(rs.getString("phonenumber"));
				oneUser.setRole(findRoleByID(rs.getInt("id")));
				return oneUser;
			}
			
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
				oneUser.setPhone(rs.getString("phonenumber"));
				oneUser.setRole(findRoleByID(rs.getInt("id")));
				return oneUser;
			}
			
		} catch (Exception e) {
			
		} finally {

		}
		return null;
	}

	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO tableUser (username, email, password, fullname, phonenumber) VALUES (?, ?, ?, ?, ?)";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getFullname());
			ps.setString(5, user.getPhone());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserModel um = findByUserName(user.getUsername());
		insert(um, user.getRole());
	}

	@Override
	public void insert(UserModel user, String role) {
		String sql = "INSERT INTO tableRole (id, role) VALUES (?, ?)";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.setString(2, role);
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
		IUserDAO userDAO = new UserDAOImpl();
		UserModel user = userDAO.findByEmail("trihung@gmail.com");
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		System.out.println(user.getImages());
		System.out.println(user.getFullname());
		System.out.println(user.getRole());
		System.out.println(user.getPhone());

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
				oneUser.setPhone(rs.getString("phonenumber"));
				oneUser.setRole(findRoleByID(oneUser.getId()));
				return oneUser;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return null;
	}

	@Override
	public String findRoleByID(int id) {
		String sql = "SELECT * FROM tableRole WHERE id = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("role");
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}

	@Override
	public boolean changePasswordByMail(String mail, String password) {
		String sql = "UPDATE tableUser SET password=? WHERE email = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, mail);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return false;
	}

	@Override
	public boolean updateInfo(UserModel user) {
		if (!checkExistUsername(user.getUsername()))
			return false;
		String sql = "UPDATE tableUser SET email=?, password=?, image=?, fullname=?, phonenumber=?  WHERE username = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getImages());
			ps.setString(4, user.getFullname());
			ps.setString(5, user.getPhone());
			ps.setString(6, user.getUsername());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return false;
	}

	@Override
	public boolean changePasswordByOldPass(String username, String oldpassword, String newpassword) {

		String sql = "UPDATE tableUser SET password=? WHERE username=? AND password = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, newpassword);
			ps.setString(2, username);
			ps.setString(3, oldpassword);
			int i = ps.executeUpdate();
			return i>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return false;
	}

}