package com.thang.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thang.config.GeneralPageConfig;
import com.thang.entity.Comment;
import com.thang.entity.Customer;
import com.thang.entity.Product;
import com.thang.repository.CommentDAO;
import com.thang.repository.ProductDAO;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private PaginationService paginationService;
	
	/* (non-Javadoc)
	 * @see com.thang.service.CommentService#getMaxPageIndex()
	 */
	@Override
	@Transactional(readOnly = true)
	public int getMaxPageIndex() { 
		return (int) commentDAO.countAll() / GeneralPageConfig.NO_OF_COMMENT_RECORDS_PER_PAGE + 1;
	}

	/* (non-Javadoc)
	 * @see com.thang.service.CommentService#getPageIndexes(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Integer> getPageIndexes(int currentPageIndex) {
		return paginationService.calculatePageIndexes(currentPageIndex, getMaxPageIndex());
	}

	/* (non-Javadoc)
	 * @see com.thang.service.CommentService#getCommentsForPagination(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Comment> getCommentsForPagination(int currentPageIndex) {
		List<Comment> commentList = commentDAO.getAll(
				(currentPageIndex - 1) * GeneralPageConfig.NO_OF_COMMENT_RECORDS_PER_PAGE,
				GeneralPageConfig.NO_OF_COMMENT_RECORDS_PER_PAGE);
		return commentList;
	}

	/* (non-Javadoc)
	 * @see com.thang.service.CommentService#getByIds(java.util.List)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Comment> getByIds(List<Integer> ids) {
		return commentDAO.getByIds(ids);
	}

	/* (non-Javadoc)
	 * @see com.thang.service.CommentService#editList(java.util.List)
	 */
	@Override
	@Transactional
	public void editList(List<Comment> commentList) {
		commentDAO.updateList(commentList);
	}

	/* (non-Javadoc)
	 * @see com.thang.service.CommentService#deleteList(java.util.List)
	 */
	@Override
	@Transactional
	public void deleteList(List<Comment> commentList) {
		commentDAO.deleteList(commentList);	
	}
	
	/* (non-Javadoc)
	 * @see com.thang.service.CommentService#getCommentByProductId(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Comment> getCommentsByProductId(int productId) {
		return commentDAO.getByProductId(productId);
	}

	/* (non-Javadoc)
	 * @see com.thang.service.CommentService#postComment(com.thang.entity.Customer, int, java.lang.String)
	 */
	@Override
	@Transactional
	public void postComment(Customer customer, int productId, String content) {
		Product product = productDAO.find(productId);
		Comment comment = new Comment(customer, product, content, new Date());
		commentDAO.create(comment);
	}
}
