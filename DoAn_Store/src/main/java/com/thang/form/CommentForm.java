package com.thang.form;

import java.util.List;

import com.thang.entity.Comment;

public class CommentForm {
	private int cPage = 1;
	
	private int productId;
	private String content = "";
	private String productNameUrl = "";

	private List<String> selectedIds;
	
	// List of events for editing
	private List<Comment> commentList;

	// Method for processing
	private String method;
	
	public CommentForm() {
		
	}

	public CommentForm(int productId, String content, String productNameUrl) {
		this.productId = productId;
		this.content = content;
		this.productNameUrl = productNameUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductNameUrl() {
		return productNameUrl;
	}

	public void setProductNameUrl(String productNameUrl) {
		this.productNameUrl = productNameUrl;
	}

	public List<String> getSelectedIds() {
		return selectedIds;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public String getMethod() {
		return method;
	}

	public void setSelectedIds(List<String> selectedIds) {
		this.selectedIds = selectedIds;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getcPage() {
		return cPage;
	}

	public void setcPage(int cPage) {
		this.cPage = cPage;
	}
	
}
