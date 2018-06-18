package com.thang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thang.config.GeneralPageConfig;
import com.thang.config.HomePageConfig;
import com.thang.entity.SaleProduct;
import com.thang.repository.SaleProductDAO;

@Service("saleProductService")
public class SaleProductServiceImpl implements SaleProductService {

	@Autowired
	private SaleProductDAO saleProductDAO;
	
	@Autowired
	private PaginationService paginationService;

	/* (non-Javadoc)
	 * @see com.thang.service.SaleProductService#create(com.thang.entity.SaleProduct)
	 */
	@Override
	@Transactional
	public void create(SaleProduct saleProduct) {
		saleProductDAO.create(saleProduct);
	}
	
	/* (non-Javadoc)
	 * @see com.thang.service.SaleProductService#edit(com.thang.entity.SaleProduct)
	 */
	@Override
	@Transactional
	public void edit(SaleProduct saleProduct) {
		saleProductDAO.update(saleProduct);
	}
	
	/* (non-Javadoc)
	 * @see com.thang.service.SaleProductService#getMaxPageIndex()
	 */
	@Override
	@Transactional(readOnly = true)
	public int getMaxPageIndex() { 
		return (int) saleProductDAO.countAll() / GeneralPageConfig.NO_OF_SALE_PRODUCT_RECORDS_PER_PAGE + 1;
	}

	/* (non-Javadoc)
	 * @see com.thang.service.SaleProductService#getPageIndexes(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Integer> getPageIndexes(int currentPageIndex) {
		return paginationService.calculatePageIndexes(currentPageIndex, getMaxPageIndex());
	}

	/* (non-Javadoc)
	 * @see com.thang.service.SaleProductService#getSaleProductsForPagination(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SaleProduct> getSaleProductsForPagination(int currentPageIndex) {
		List<SaleProduct> saleProductList = saleProductDAO.getAll(
				(currentPageIndex - 1) * GeneralPageConfig.NO_OF_SALE_PRODUCT_RECORDS_PER_PAGE,
				GeneralPageConfig.NO_OF_SALE_PRODUCT_RECORDS_PER_PAGE);
		return saleProductList;
	}

	/* (non-Javadoc)
	 * @see com.thang.service.SaleProductService#getByIds(java.util.List)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SaleProduct> getByIds(List<Integer> ids) {
		return saleProductDAO.getByIds(ids);
	}

	/* (non-Javadoc)
	 * @see com.thang.service.SaleProductService#editList(java.util.List)
	 */
	@Override
	@Transactional
	public void editList(List<SaleProduct> saleProductList) {
		saleProductDAO.updateList(saleProductList);
	}

	/* (non-Javadoc)
	 * @see com.thang.service.SaleProductService#deleteList(java.util.List)
	 */
	@Override
	@Transactional
	public void deleteList(List<SaleProduct> saleProductList) {
		saleProductDAO.deleteList(saleProductList);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SaleProduct> getSaleProducts() {
		return saleProductDAO.getSaleProducts();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SaleProduct> getLatestSaleProducts() {
		return saleProductDAO.getLatestSaleProducts(HomePageConfig.NO_OF_DISPLAY_SALE_PRODUCT);
	}
}
