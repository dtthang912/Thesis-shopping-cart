package com.thang.repository;

import java.util.List;

import com.thang.entity.Comment;

public interface CommentDAO {
	void create(Comment entity);

	void delete(Comment entity);

	void update(Comment entity);

	Comment find(Integer id);

	void updateList(List<Comment> entityList);

	void deleteList(List<Comment> entityList);
	
	List<Comment> getByProductId(int productId);

	List<Comment> getAll(int offset, int limit);
	
	long countAll();
	
	List<Comment> getByIds(List<Integer> ids);
}