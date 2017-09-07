package com.jhdx.Dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import om.jhdx.utils.jdbcutil;

import com.jhdx.Dao.CategoryDao;
import com.jhdx.bean.Article;
import com.jhdx.bean.Category;

public class CategoryDaoImp implements CategoryDao {
	private CategoryDaoImp(){}
	
	private static CategoryDaoImp cdi = new CategoryDaoImp();
	
	public static CategoryDaoImp getInstance(){
		return cdi;
	}

	@Override
	public Category SelectOne(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Category cg = new Category();
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("select * from category where category_id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				cg.setId(rs.getInt(1));
				cg.setName(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcutil.free(rs, ps, conn);
		}
		return cg;
	}

	@Override
	public ArrayList<Category> SelectAll() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Category> list = new ArrayList<Category>();
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("select * from category");
			rs = ps.executeQuery();
			while (rs.next()) {
				Category cg = new Category();
				cg.setId(rs.getInt(1));
				cg.setName(rs.getString(2));
				list.add(cg);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcutil.free(rs, ps, conn);
		}
		return list;
	}

}
