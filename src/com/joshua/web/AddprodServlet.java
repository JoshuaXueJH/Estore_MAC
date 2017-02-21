package com.joshua.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.joshua.domain.Prod;
import com.joshua.factory.BasicFactory;
import com.joshua.service.ProdService;
import com.joshua.util.IOUtils;

/**
 * Servlet implementation class AddprodServlet
 */
@WebServlet("/AddprodServlet")
public class AddprodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddprodServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdService prodService = BasicFactory.getFactory().getInstance(ProdService.class);
		try {
			String encode = this.getServletContext().getInitParameter("encode");
			Map<String, String> paraMap = new HashMap<String, String>();
			// 上传图片
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024 * 100);
			factory.setRepository(new File(this.getServletContext().getRealPath("WEB-INF/temp")));

			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			fileUpload.setHeaderEncoding(encode);
			// fileUpload.setFileSizeMax(1024*1024*1);
			// fileUpload.setSizeMax(1024*1024*10);

			if (!fileUpload.isMultipartContent(request)) {
				throw new RuntimeException("请使用正确的表单进行上传！");
			}
			List<FileItem> list = fileUpload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					// 普通字段
					String name = item.getFieldName();
					String value = item.getString(encode);
					paraMap.put(name, value);
				} else {
					// 文件项上传
					String realname = item.getName();
					String uuidname = UUID.randomUUID().toString() + "_" + realname;
					String hash = Integer.toHexString(uuidname.hashCode());
					String upload = this.getServletContext().getRealPath("WEB-INF/upload");
					String imgurl = "WEB-INF/upload";
					for (char c : hash.toCharArray()) {
						upload += "/" + c;
						imgurl += "/" + c;
					}
					imgurl += "/" + uuidname;
					paraMap.put("imgurl", imgurl);

					// 建立文件输出的文件夹
					File uploadFile = new File(upload);
					if (!uploadFile.exists()) {
						uploadFile.mkdirs();
					}

					InputStream in = item.getInputStream();
					OutputStream out = new FileOutputStream(new File(upload, uuidname));

					IOUtils.In2Out(in, out);
					IOUtils.close(in, out);
					item.delete();

				}
			}
			// 调用service中方法添加一条商品记录
			Prod prod = new Prod();
			BeanUtils.populate(prod, paraMap);
			prodService.addProd(prod);

			// 提示成功，返回主页
			response.getWriter().write("商品录入成功，三秒后返回主页");
			response.setHeader("Refresh", "3;url=index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
