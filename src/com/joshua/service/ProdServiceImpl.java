package com.joshua.service;

import java.util.List;
import java.util.UUID;

import com.joshua.dao.ProdDao;
import com.joshua.domain.Prod;
import com.joshua.factory.BasicFactory;

public class ProdServiceImpl implements ProdService {
	ProdDao prodDao = BasicFactory.getFactory().getDao(ProdDao.class);

	@Override
	public void addProd(Prod prod) {
		try {
			prod.setId(UUID.randomUUID().toString());
			prodDao.addProd(prod);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Prod> findAllProds() {
		try {
			return prodDao.findAllProds();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Prod findProdByID(String ID) {
		try {
			return prodDao.findProdByID(ID);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
