package com.jhdx.Dao;

import com.jhdx.bean.User;

public interface UserDao {
	public abstract boolean Login(User user);
	
	public abstract User GetUser(User user);
	
	public abstract void InsertUser(User user);
	
	public abstract User getuser(int id);
}
