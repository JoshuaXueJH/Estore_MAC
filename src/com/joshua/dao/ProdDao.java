package com.joshua.dao;

import java.util.List;

import com.joshua.domain.Prod;

public interface ProdDao {

	/**
	 * 向数据库中添加一条prod
	 * 
	 * @param prod
	 *            封装了prod信息
	 */
	void addProd(Prod prod);

	/**
	 * 从数据库中获取所有的prod信息
	 * @return
	 */
	List<Prod> findAllProds();

	/**
	 * 根据ID取prod数据
	 * @param iD
	 * @return
	 */
	Prod findProdByID(String ID);

}
