package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.DBConnectMySQL;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.models.UserModel;

public class UserDaoImpl extends DBConnectMySQL implements IUserDao {

	@Override
	public List<UserModel> findAll() {
		String sql ="select * from users";
		
		List<UserModel> list = new ArrayList<>();
		
		try {
			Connection conn = super.getDatabaseConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("fullname"), rs.getString("images")));
				
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<UserModel> findById (int id) 
	{
		String sql="SELECT * FROM user WHERE id=?";
		List<UserModel> list = new ArrayList<UserModel>();
		try {
			Connection conn = super.getDatabaseConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				UserModel model1=new UserModel();
				list.add(new UserModel(model1.getId(), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("fullname"), rs.getString("image")));
			}
			return list;

		}catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO users(id, username, email, password, fullname, images) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			Connection conn = super.getDatabaseConnection();
			
			Object ps = conn.prepareStatement(sql);
			
			((PreparedStatement) ps).setInt(1, user.getId());
			((PreparedStatement) ps).setString(2, user.getUsername());
			((PreparedStatement) ps).setString(3, user.getEmail());
			((PreparedStatement) ps).setString(4, user.getPassword());
			((PreparedStatement) ps).setString(5, user.getFullname());
			((PreparedStatement) ps).setString(6, user.getImages());
			
			((PreparedStatement) ps).executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		UserDaoImpl userDao = new UserDaoImpl();
		
		//userDao.insert(new UserModel(4, "bcd", "bcd@gmail.com", "567", "bcdefg",""));
		
		List<UserModel> list1=userDao.findById(1);
		
		//List<UserModel> list = userDao.findAll();
		
		for (UserModel user : list1) {
			System.out.println(user);
		}
	}
}
