package com.joshua.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.joshua.domain.Prod;
import com.joshua.util.DaoUtils;
import com.joshua.util.TranManager;

public class ProdDaoImpl implements ProdDao {

	@Override
	public void addProd(Prod prod) throws SQLException {
		String sql = "insert into products values(?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(TranManager.getSource());
		runner.update(sql, prod.getId(), prod.getName(), prod.getPrice(), prod.getCategory(), prod.getPnum(),
				prod.getImgurl(), prod.getDescription());
	}

	@Override
	public List<Prod> findAllProds() throws SQLException {
		String sql = "select * from products";
		QueryRunner runner = new QueryRunner(TranManager.getSource());
		return runner.query(sql, new BeanListHandler<Prod>(Prod.class));
	}

	@Override
	public Prod findProdByID(String ID) throws SQLException {
		String sql = "select * from products where id=?";
		QueryRunner runner = new QueryRunner(TranManager.getSource());
		return runner.query(sql, new BeanHandler<Prod>(Prod.class), ID);
	}

	@Override
	public void delPnum(String prod_id, int buynum) throws SQLException {
		String sql = "update products set pnum=pnum-? where id=? and pnum-?>=0";
		QueryRunner runner = new QueryRunner(TranManager.getSource());
		int count = runner.update(sql, buynum, prod_id, buynum);
		if (count <= 0) {
			throw new RuntimeException("商品库存不足");
		}
	}

	@Override
	public void addPnum(String product_id, int buynum) throws SQLException {
		String sql = "update products set pnum=pnum+? where id=?";
		QueryRunner runner = new QueryRunner(TranManager.getSource());
		runner.update(sql, buynum, product_id);
	}

}
