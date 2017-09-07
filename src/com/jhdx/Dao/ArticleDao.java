package com.jhdx.Dao;

import java.util.ArrayList;

import com.jhdx.bean.Article;
import com.jhdx.servlet.articleServlet;

public interface ArticleDao {
	public abstract ArrayList<Article> SelectAll(int id);   //�鿴��������
	
	public abstract Article SelectOne(int id);  //�鿴����һƪ����
	
	public abstract ArrayList<Article> SelectTypeArticle(int id);//������ѯ��������
	
	public abstract ArrayList<Article> SelectTop9Time();//������ʱ����ȡǰ��ƪ����
	
	public abstract ArrayList<Article> SelectHot();//���������ȡǰ��ƪ����
	
	public abstract void InsertArt(Article art);//������
	
	public abstract ArrayList<Article> SelectFYType(String keywords,int pRows,int Row); //����֮����ʾ��������ҳ
	
	public abstract int NUMLIKE(String KeyWords);//ģ������
	
	public abstract void UpdateViewCount(int id); //���������������
	
	public abstract void SCang(int userid,int articleid,String Time); //�ղ�
	
	public abstract ArrayList<Article> collectionList(int id);//�ղ���ʾ
	
	public abstract void delArt(int id);
}
