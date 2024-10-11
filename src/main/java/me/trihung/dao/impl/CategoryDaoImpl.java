package me.trihung.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import me.trihung.config.DBConnectSQLServer;
import me.trihung.dao.ICategoryDao;
import me.trihung.models.CategoryModel;

public class CategoryDaoImpl implements ICategoryDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<CategoryModel> findAll() {
		String sql = "SELECT * FROM categories";
		try {
			conn = DBConnectSQLServer.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List<CategoryModel> list = new ArrayList<>();
			while(rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				list.add(category);
			}
			
			conn.close();
			ps.close();
			rs.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return null;
	}

	@Override
	public CategoryModel findById(int id) {
		String sql = "SELECT * FROM categories WHERE categoryid = ?";
		try {
			conn = DBConnectSQLServer.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				return category;
			}
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return null;
	}

	@Override
	public void insert(CategoryModel categoryModel) {
		String sql = "INSERT INTO categories(categoryname, images, status) VALUES(?, ?, ?)";
		try {
			conn = DBConnectSQLServer.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, categoryModel.getCategoryname());
			ps.setString(2, categoryModel.getImages());
			ps.setInt(3, categoryModel.getStatus());
			ps.executeQuery();
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}

	@Override
	public boolean update(CategoryModel categoryModel) {
		String sql = "UPDATE categories SET categoryname=?, images=?, status=? WHERE categoryid=?";
		try {
			conn = DBConnectSQLServer.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, categoryModel.getCategoryname());
			ps.setString(2, categoryModel.getImages());
			ps.setInt(3, categoryModel.getStatus());
			ps.setInt(4, categoryModel.getCategoryid());
			int index = ps.executeUpdate();
			if (index>0)
				return true;
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM categories WHERE categoryid=?";
		try {
			conn = DBConnectSQLServer.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int index = ps.executeUpdate();
			if (index>0)
				return true;
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return false;
	}

	@Override
	public List<CategoryModel> findName(String keyword) {
		String sql = "SELECT * FROM categories WHERE categoryname like ?";
		try {
			conn = DBConnectSQLServer.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			rs = ps.executeQuery();
			List<CategoryModel> categories = new ArrayList<>();
			while(rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				categories.add(category);
			}
			conn.close();
			ps.close();
			rs.close();
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return null;
	}

	@Override
	public boolean softDelete(CategoryModel categoryModel) {
		String sql = "UPDATE categories SET status=? WHERE categoryid=?";
		try {
			conn = DBConnectSQLServer.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, categoryModel.getStatus());
			ps.setInt(2, categoryModel.getCategoryid());
			int index = ps.executeUpdate();
			if (index>0)
				return true;
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return false;
	}

}
