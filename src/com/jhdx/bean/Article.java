package com.jhdx.bean;

public class Article {
	private int articleId;
	private int userId;
	private int categoryId;    //类别id
	private String title;      
	private String content;    //文章内容
	private int status;        //文章状态
	private String createTime;  
	private String updateTime;
	private int viewCount;    //浏览总数
	private int upCount;      //点赞总数
	private int commentCount;  //评论总数
	private String typename; //类别名称
	private String nickname;
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getUpCount() {
		return upCount;
	}
	public void setUpCount(int upCount) {
		this.upCount = upCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public Article() {
		super();
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", userId=" + userId
				+ ", categoryId=" + categoryId + ", title=" + title
				+ ", content=" + content + ", status=" + status
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", viewCount=" + viewCount + ", upCount=" + upCount
				+ ", commentCount=" + commentCount + ", typename=" + typename
				+ ", nickname=" + nickname + "]";
	}
}
