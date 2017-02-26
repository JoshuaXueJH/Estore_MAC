package com.joshua.listener;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.joshua.domain.Prod;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		arg0.getSession().setAttribute("cartMap", new LinkedHashMap<Prod, Integer>());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
