package com.jhdx.Dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import om.jhdx.utils.jdbcutil;

import com.jhdx.Dao.CommentDao;
import com.jhdx.bean.Comment;

public class CommentDaoImp implements CommentDao{
	private CommentDaoImp(){}
	
	private static CommentDaoImp cdi = new CommentDaoImp();
	
	public static CommentDaoImp getInstance(){
		return cdi;
	}

	@Override
	public ArrayList<Comment> SelectCom(int artid) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Comment> list = new ArrayList<Comment>();
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("select `comment`.*,myuser.nickname,arcitle.title from `comment`,myuser,arcitle where `comment`.user_id = myuser.user_id and `comment`.Article_Id = arcitle.arcitle_id and `comment`.Article_Id=?");
			ps.setInt(1, artid);
			rs = ps.executeQuery();
			while (rs.next()) {
				Comment com = new Comment();
				com.setCommentid(rs.getInt(1));
				com.setFarcommentid(rs.getInt(2));
				com.setUserid(rs.getInt(3));
				com.setArticleid(rs.getInt(4));
				com.setComment(rs.getString(5));
				com.setCommenttime(rs.getString(6));
				com.setNickname(rs.getString(7));
				com.setTitle(rs.getString(8));
				list.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcutil.free(rs, ps, conn);
		}
		return list;
	}

	@Override
	public void InsertMajorComment(Comment com) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("insert into `comment`(user_id,Article_id,Content,ContentTime,FarCommentId) values(?,?,?,?,?)");
			ps.setInt(1, com.getUserid());
			ps.setInt(2, com.getArticleid());
			ps.setString(3, com.getComment());
			ps.setString(4, com.getCommenttime());
			ps.setInt(5, com.getFarcommentid());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcutil.free(ps, conn);
		}
	}
}
