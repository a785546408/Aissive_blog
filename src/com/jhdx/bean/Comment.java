package com.jhdx.bean;

public class Comment {
	private int commentid;
	private int farcommentid;
	private int userid;
	private int articleid;
	private String comment;
	private String commenttime;
	private String nickname;
	private String title;
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public int getFarcommentid() {
		return farcommentid;
	}
	public void setFarcommentid(int farcommentid) {
		this.farcommentid = farcommentid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getArticleid() {
		return articleid;
	}
	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCommenttime() {
		return commenttime;
	}
	public void setCommenttime(String commenttime) {
		this.commenttime = commenttime;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Comment(int commentid, int farcommentid, int userid, int articleid,
			String comment, String commenttime, String nickname, String title) {
		super();
		this.commentid = commentid;
		this.farcommentid = farcommentid;
		this.userid = userid;
		this.articleid = articleid;
		this.comment = comment;
		this.commenttime = commenttime;
		this.nickname = nickname;
		this.title = title;
	}
	public Comment() {
		super();
	}
}
