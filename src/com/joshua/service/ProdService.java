package com.joshua.service;

import java.util.List;

import com.joshua.domain.Prod;

public interface ProdService {

	/**
	 * 添加商品功能
	 * 
	 * @param prod
	 *            商品信息
	 */
	void addProd(Prod prod);

	/**
	 * 查找所有商品功能
	 * @return
	 */
	List<Prod> findAllProds();

}
