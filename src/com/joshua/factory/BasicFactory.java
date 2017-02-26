package com.joshua.factory;

import java.io.FileReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

import com.joshua.annotation.Tran;
import com.joshua.dao.Dao;
import com.joshua.service.Service;
import com.joshua.util.TranManager;

public class BasicFactory {
	private BasicFactory() {
	}

	private static BasicFactory factory = new BasicFactory();
	private static Properties prop = null;

	static {
		try {
			prop = new Properties();
			prop.load(new FileReader(BasicFactory.class.getClassLoader().getResource("config.properties").getPath()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static BasicFactory getFactory() {
		return factory;
	}

	public <T extends Service> T getService(Class<T> clazz) {
		try {
			String infName = clazz.getSimpleName();
			String implName = prop.getProperty(infName);
			T service = (T) Class.forName(implName).newInstance();
			@SuppressWarnings("unchecked")
			T proxyService = (T) Proxy.newProxyInstance(service.getClass().getClassLoader(),
					service.getClass().getInterfaces(), new InvocationHandler() {

						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							if (method.isAnnotationPresent(Tran.class)) {
								try {
									TranManager.startTran();
									Object obj = method.invoke(service, args);
									TranManager.commit();
									return obj;

								} catch (InvocationTargetException e) {
									TranManager.rollback();
									throw new RuntimeException(e.getTargetException());
								} catch (Exception e) {
									TranManager.rollback();
									throw new RuntimeException(e);
								} finally {
									TranManager.release();
								}
							} else {
								return method.invoke(service, args);
							}
						}
					});
			return proxyService;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public <T extends Dao> T getDao(Class<T> clazz) {
		try {
			String infName = clazz.getSimpleName();
			String implName = prop.getProperty(infName);
			return (T) Class.forName(implName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}
}
