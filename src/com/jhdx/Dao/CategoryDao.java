package com.jhdx.Dao;

import java.util.ArrayList;

import com.jhdx.bean.Category;

public interface CategoryDao {
	public abstract Category SelectOne(int id);
	
	public abstract ArrayList<Category> SelectAll();
}
