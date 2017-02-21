package com.joshua.service;

import java.util.List;
import java.util.UUID;

import com.joshua.dao.ProdDao;
import com.joshua.domain.Prod;
import com.joshua.factory.BasicFactory;

public class ProdServiceImpl implements ProdService {
	ProdDao prodDao = BasicFactory.getFactory().getInstance(ProdDao.class);

	@Override
	public void addProd(Prod prod) {
		prod.setId(UUID.randomUUID().toString());
		prodDao.addProd(prod);
	}

	@Override
	public List<Prod> findAllProds() {
		return prodDao.findAllProds();
	}

	@Override
	public Prod findProdByID(String ID) {
		return prodDao.findProdByID(ID);
	}

}
