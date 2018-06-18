package com.thang.repository;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.thang.entity.Comment;

@Repository("commentDAO")
public class CommentDAOImpl extends GenericDAO<Comment> implements CommentDAO {

	public CommentDAOImpl() {
		super(Comment.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Comment> getByProductId(int productId) {
		Session session = getSession();
		List<Comment> commentList = session.getNamedQuery("Comment.getByProductId").setParameter("id", productId)
				.getResultList();
		return commentList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Comment> getAll(int offset, int limit) {
		Session session = getSession();
		List<Comment> commentList = session.getNamedQuery("Comment.getAll").setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		return commentList;
	}

	@Override
	public long countAll() {
		Session session = getSession();
		try {
			return (Long) session.getNamedQuery("Comment.countAll").getSingleResult();
		} catch (NoResultException e) {

		}
		return 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Comment> getByIds(List<Integer> ids) {
		Session session = getSession();
		List<Comment> commentList = session.getNamedQuery("Comment.getByIds").setParameter("ids", ids).getResultList();
		return commentList;
	}

	@Override
	public void deleteList(List<Comment> commentList) {
		Session session = getSession();
		for (Comment comment : commentList) {
			comment.setDeleted(true);
			session.update(comment);
		}
	}
}
