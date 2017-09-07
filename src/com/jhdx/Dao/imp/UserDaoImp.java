package com.jhdx.Dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import om.jhdx.utils.jdbcutil;

import com.jhdx.Dao.UserDao;
import com.jhdx.bean.User;

public class UserDaoImp implements UserDao {
	private UserDaoImp(){}
	
	private static UserDaoImp udi = new UserDaoImp();
	
	public static UserDaoImp getInstance(){
		return udi;
	}
	@Override
	public boolean Login(User user) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("select * from myuser where username = ? and password = ?");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcutil.free(rs, ps, conn);
		}
		return flag;
	}
	@Override
	public User GetUser(User user) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user1 = null;
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("select * from myuser where username = ? and password = ?");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			if (rs.next()) {
				user1 = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcutil.free(rs, ps, conn);
		}
		return user1;
	}
	@Override
	public void InsertUser(User user) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("insert into myuser(username,password,nickname,userimg) values(?,?,?,?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickname());
			ps.setString(4, user.getImgurl());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcutil.free(ps, conn);
		}
	}
	@Override
	public User getuser(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user1 = null;
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("select * from myuser where user_id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				user1 = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcutil.free(rs, ps, conn);
		}
		return user1;
	}

}
