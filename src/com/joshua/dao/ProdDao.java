package com.joshua.dao;

import java.sql.SQLException;
import java.util.List;

import com.joshua.domain.Prod;

public interface ProdDao extends Dao {

	/**
	 * 向数据库中添加一条prod
	 * 
	 * @param prod
	 *            封装了prod信息
	 * @throws SQLException
	 */
	void addProd(Prod prod) throws SQLException;

	/**
	 * 从数据库中获取所有的prod信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<Prod> findAllProds() throws SQLException;

	/**
	 * 根据ID取prod数据
	 * 
	 * @param iD
	 * @return
	 * @throws SQLException
	 */
	Prod findProdByID(String ID) throws SQLException;

	/**
	 * 根据ID将数据库中商品的pnum更改
	 * 
	 * @param prod_id
	 * @param buynum
	 * @throws SQLException
	 */
	void delPnum(String prod_id, int buynum) throws SQLException;

	/**
	 * 根据产品id未产品增加数量
	 * @param product_id
	 * @param buynum
	 * @throws SQLException 
	 */
	void addPnum(String product_id, int buynum) throws SQLException;

}
