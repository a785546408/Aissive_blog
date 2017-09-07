package com.jhdx.Dao;

import java.util.ArrayList;

import com.jhdx.bean.Comment;

public interface CommentDao {
	public abstract ArrayList<Comment> SelectCom(int artid);  //获取评论
	
	public abstract void InsertMajorComment(Comment com);//添加主评论
}
