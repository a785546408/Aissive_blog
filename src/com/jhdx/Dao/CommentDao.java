package com.jhdx.Dao;

import java.util.ArrayList;

import com.jhdx.bean.Comment;

public interface CommentDao {
	public abstract ArrayList<Comment> SelectCom(int artid);  //��ȡ����
	
	public abstract void InsertMajorComment(Comment com);//���������
}
