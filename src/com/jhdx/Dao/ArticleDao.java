package com.jhdx.Dao;

import java.util.ArrayList;

import com.jhdx.bean.Article;
import com.jhdx.servlet.articleServlet;

public interface ArticleDao {
	public abstract ArrayList<Article> SelectAll(int id);   //查看所有文章
	
	public abstract Article SelectOne(int id);  //查看单独一篇文章
	
	public abstract ArrayList<Article> SelectTypeArticle(int id);//按类别查询所有文章
	
	public abstract ArrayList<Article> SelectTop9Time();//按更新时间拉取前九篇文章
	
	public abstract ArrayList<Article> SelectHot();//按点击量拉取前就篇文章
	
	public abstract void InsertArt(Article art);//发表博文
	
	public abstract ArrayList<Article> SelectFYType(String keywords,int pRows,int Row); //搜索之后显示文章类别分页
	
	public abstract int NUMLIKE(String KeyWords);//模糊搜素
	
	public abstract void UpdateViewCount(int id); //更新文章浏览次数
	
	public abstract void SCang(int userid,int articleid,String Time); //收藏
	
	public abstract ArrayList<Article> collectionList(int id);//收藏显示
	
	public abstract void delArt(int id);
}
