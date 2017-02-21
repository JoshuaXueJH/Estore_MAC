package com.joshua.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.joshua.domain.Prod;
import com.joshua.util.DaoUtils;

public class ProdDaoImpl implements ProdDao {

	@Override
	public void addProd(Prod prod) {
		String sql = "insert into products values(?,?,?,?,?,?,?)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, prod.getId(), prod.getName(), prod.getPrice(), prod.getCategory(), prod.getPnum(),
					prod.getImgurl(), prod.getDescription());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Prod> findAllProds() {
		String sql = "select * from products";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Prod>(Prod.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
