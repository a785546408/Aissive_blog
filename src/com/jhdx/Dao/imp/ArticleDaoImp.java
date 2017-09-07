package com.jhdx.Dao.imp;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import om.jhdx.utils.jdbcutil;

import com.jhdx.Dao.ArticleDao;
import com.jhdx.bean.Article;
import com.jhdx.bean.Category;

public class ArticleDaoImp implements ArticleDao{
	private ArticleDaoImp(){
		
	}
	
	private static ArticleDaoImp adi = new ArticleDaoImp();
	
	public static ArticleDaoImp getInstance(){
		return adi;
	}
	
	static{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("UPDATE arcitle SET content=REPLACE(content, char(13), '<br>&nbsp;&nbsp;')");
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcutil.free(ps, conn);
		}
	}
	
	@Override
	public ArrayList<Article> SelectAll(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Article> list = new ArrayList<Article>();
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("select * from arcitle,category,myuser where arcitle.category_id = category.category_id and myuser.user_id = arcitle.user_id and arcitle.user_id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Article at = new Article();
				at.setArticleId(rs.getInt(1));
				at.setUserId(rs.getInt(2));
				at.setCategoryId(rs.getInt(3));
				at.setTitle(rs.getString(4));
				at.setContent(rs.getString(5));
				at.setStatus(rs.getInt(6));
				at.setCreateTime(rs.getString(7));
				at.setUpdateTime(rs.getString(8));
				at.setViewCount(rs.getInt(9));
				at.setUpCount(rs.getInt(10));
				at.setCommentCount(rs.getInt(11));
				at.setTypename(rs.getString(13));
				at.setNickname(rs.getString(17));
				list.add(at);
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
	public Article SelectOne(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Article at = new Article();
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("select * from arcitle,category,myuser where arcitle.category_id = category.category_id and myuser.user_id = arcitle.user_id and arcitle_id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				at.setArticleId(rs.getInt(1));
				at.setUserId(rs.getInt(2));
				at.setCategoryId(rs.getInt(3));
				at.setTitle(rs.getString(4));
				at.setContent(rs.getString(5));
				at.setStatus(rs.getInt(6));
				at.setCreateTime(rs.getString(7));
				at.setUpdateTime(rs.getString(8));
				at.setViewCount(rs.getInt(9));
				at.setUpCount(rs.getInt(10));
				at.setCommentCount(rs.getInt(11));
				at.setTypename(rs.getString(13));
				at.setNickname(rs.getString(17));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcutil.free(rs, ps, conn);
		}
		return at;
	}

	@Override
	public ArrayList<Article> SelectTypeArticle(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Article> list = new ArrayList<>();
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("select * from arcitle where category_id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			ArrayList<Article> list_art = new ArrayList<>();
			while (rs.next()) {
				Article at = new Article();
				at.setArticleId(rs.getInt(1));
				at.setUserId(rs.getInt(2));
				at.setCategoryId(rs.getInt(3));
				at.setTitle(rs.getString(4));
				at.setContent(rs.getString(5));
				at.setStatus(rs.getInt(6));
				at.setCreateTime(rs.getString(7));
				at.setUpdateTime(rs.getString(8));
				at.setViewCount(rs.getInt(9));
				at.setUpCount(rs.getInt(10));
				at.setCommentCount(rs.getInt(11));
				list.add(at);
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
	public ArrayList<Article> SelectTop9Time() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Article> list = new ArrayList<>();
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("select * from arcitle order by createdTime desc LIMIT 0,9");
			rs = ps.executeQuery();
			ArrayList<Article> list_art = new ArrayList<>();
			while (rs.next()) {
				Article at = new Article();
				at.setArticleId(rs.getInt(1));
				at.setUserId(rs.getInt(2));
				at.setCategoryId(rs.getInt(3));
				at.setTitle(rs.getString(4));
				at.setContent(rs.getString(5));
				at.setStatus(rs.getInt(6));
				at.setCreateTime(rs.getString(7));
				at.setUpdateTime(rs.getString(8));
				at.setViewCount(rs.getInt(9));
				at.setUpCount(rs.getInt(10));
				at.setCommentCount(rs.getInt(11));
				list.add(at);
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
	public ArrayList<Article> SelectHot() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Article> list = new ArrayList<>();
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("select * from arcitle,category where arcitle.category_id = category.category_id ORDER BY arcitle.ViewCount desc LIMIT 0,9");
			rs = ps.executeQuery();
			ArrayList<Article> list_art = new ArrayList<>();
			while (rs.next()) {
				Article at = new Article();
				at.setArticleId(rs.getInt(1));
				at.setUserId(rs.getInt(2));
				at.setCategoryId(rs.getInt(3));
				at.setTitle(rs.getString(4));
				at.setContent(rs.getString(5));
				at.setStatus(rs.getInt(6));
				at.setCreateTime(rs.getString(7));
				at.setUpdateTime(rs.getString(8));
				at.setViewCount(rs.getInt(9));
				at.setUpCount(rs.getInt(10));
				at.setCommentCount(rs.getInt(11));
				at.setTypename(rs.getString(13));
				list.add(at);
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
	public void InsertArt(Article art) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("insert into arcitle(user_id,category_id,title,content,status,createdtime,updatedtime) values(?,?,?,?,?,?,?)");
		    ps.setInt(1, art.getUserId());
		    ps.setInt(2, art.getCategoryId());
		    ps.setString(3, art.getTitle());
		    ps.setString(4, art.getContent());
		    ps.setInt(5, art.getStatus());
		    ps.setString(6, art.getCreateTime());
		    ps.setString(7, art.getUpdateTime());
		    ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcutil.free(ps, conn);
		}
	}

	@Override
	public ArrayList<Article> SelectFYType(String keywords,int pRows,int Rows) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Article> list = new ArrayList<>();
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("select * from arcitle,category,myuser where arcitle.category_id = category.category_id and myuser.user_id = arcitle.user_id and title like ? limit ?,?");
			ps.setString(1, "%"+keywords+"%");
			ps.setInt(2, (pRows-1)*5);
			ps.setInt(3, Rows);
			rs = ps.executeQuery();
			ArrayList<Article> list_art = new ArrayList<>();
			while (rs.next()) {
				Article at = new Article();
				at.setArticleId(rs.getInt(1));
				at.setUserId(rs.getInt(2));
				at.setCategoryId(rs.getInt(3));
				at.setTitle(rs.getString(4));
				at.setContent(rs.getString(5));
				at.setStatus(rs.getInt(6));
				at.setCreateTime(rs.getString(7));
				at.setUpdateTime(rs.getString(8));
				at.setViewCount(rs.getInt(9));
				at.setUpCount(rs.getInt(10));
				at.setCommentCount(rs.getInt(11));
				at.setTypename(rs.getString(13));
				at.setNickname(rs.getString(17));
				list.add(at);
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
	public int NUMLIKE(String KeyWords) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("select * from arcitle,category,myuser where arcitle.category_id = category.category_id and myuser.user_id = arcitle.user_id and title like ? ");
			ps.setString(1, "%"+KeyWords+"%");
			rs = ps.executeQuery();
			while (rs.next()) {
				count++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcutil.free(rs, ps, conn);
		}
		return count;
	}

	@Override
	public void UpdateViewCount(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("update arcitle set ViewCount=ViewCount+1 where arcitle_id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcutil.free(ps, conn);
		}
		
	}

	@Override
	public void SCang(int userid, int articleid, String Time) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("insert into collection(user_id,Article_Id,createdTime) values(?,?,?)");
			ps.setInt(1, userid);
			ps.setInt(2, articleid);
			ps.setString(3, Time);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcutil.free(ps, conn);
		}
	}

	@Override
	public ArrayList<Article> collectionList(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Article> list = new ArrayList<>();
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("select * from collection,arcitle,category where collection.Article_Id = arcitle.arcitle_id and arcitle.category_id = category.category_id and collection.user_id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			ArrayList<Article> list_art = new ArrayList<>();
			while (rs.next()) {
				Article at = new Article();
				at.setArticleId(rs.getInt(5));
				at.setUserId(rs.getInt(6));
				at.setCategoryId(rs.getInt(7));
				at.setTitle(rs.getString(8));
				at.setContent(rs.getString(9));
				at.setStatus(rs.getInt(10));
				at.setCreateTime(rs.getString(11));
				at.setUpdateTime(rs.getString(12));
				at.setViewCount(rs.getInt(13));
				at.setUpCount(rs.getInt(14));
				at.setCommentCount(rs.getInt(15));
				at.setTypename(rs.getString(17));
				list.add(at);
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
	public void delArt(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = jdbcutil.getConnection();
			ps = conn.prepareStatement("delete from arcitle where arcitle_id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcutil.free(ps, conn);
		}
	}

}
