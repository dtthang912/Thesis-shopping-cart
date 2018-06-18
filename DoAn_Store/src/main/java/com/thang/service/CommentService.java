package com.thang.service;

import java.util.List;

import com.thang.entity.Comment;
import com.thang.entity.Customer;

public interface CommentService {

	int getMaxPageIndex();

	List<Integer> getPageIndexes(int currentPageIndex);

	List<Comment> getCommentsForPagination(int currentPageIndex);

	List<Comment> getByIds(List<Integer> ids);

	void editList(List<Comment> commentList);

	void deleteList(List<Comment> commentList);

	List<Comment> getCommentsByProductId(int productId);

	void postComment(Customer customer, int productId, String content);

}