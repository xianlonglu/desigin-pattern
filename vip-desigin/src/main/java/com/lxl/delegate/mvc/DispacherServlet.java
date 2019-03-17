package com.lxl.delegate.mvc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Administrator
 *
 */
public class DispacherServlet extends HttpServlet {

	// private void doDispatch(HttpServletRequest req, HttpServletResponse resp)
	// {
	// String uri = req.getRequestURI();
	// String mid = req.getParameter("mid");
	// if ("getMemberById".equals(uri)) {
	// new MemberController().getMemberById(mid);
	// } else if("getOrderById".equals(uri)) {
	// new OrderController().getOrderById(mid);
	// } else if("logout".equals(uri)) {
	// new SystemController().logout();
	// }
	// }

	private List<Hander> handerList = new ArrayList<Hander>();

	@Override
	public void init() throws ServletException {
		try {
			Class<?> memberControllerClass = MemberController.class;
			handerList.add(new Hander().setController(memberControllerClass.newInstance())
					.setMethod(memberControllerClass.getMethod("getMemberById",new Class[] { String.class }))
					.setUrl("/web/getMemberById.json"));
			Class<?> orderControllerClass = OrderController.class;
			handerList.add(new Hander().setController(orderControllerClass.newInstance())
					.setMethod(orderControllerClass.getMethod("getOrderById", new Class[] {String.class}))
					.setUrl("/web/getOrderById.json"));
			Class<?> systemControllerClass = SystemController.class;
			handerList.add(new Hander().setController(systemControllerClass.newInstance())
					.setMethod(systemControllerClass.getMethod("logout", new Class[] {}))
					.setUrl("/web/logout.json"));
		} catch (Exception e) {
		}
	}

	private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {

		// 1、获取用户请求的url
		// 如果按照J2EE的标准、每个url对对应一个Serlvet，url由浏览器输入
		String uri = req.getRequestURI();
		String mid = req.getParameter("mid");
		
		// 2、Servlet拿到url以后，要做权衡（要做判断，要做选择）
		// 根据用户请求的URL，去找到这个url对应的某一个java类的方法
		// 3、通过拿到的URL去handlerMapping（我们把它认为是策略常量）
		Hander hander = null;
		for (Hander h : handerList) {
			if(uri.equals(h.getUrl())){
				hander = h;
				break;
			}
		}
        //4、将具体的任务分发给Method（通过反射去调用其对应的方法）
		try {
			Object obj = hander.getMethod().invoke(hander.getController(), mid);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 5、获取到Method执行的结果，通过Response返回出去
		// response.getWriter().write();
	}

	class Hander {
		private Object controller;
		private Method method;
		private String url;

		public Object getController() {
			return controller;
		}

		public Hander setController(Object controller) {
			this.controller = controller;
			return this;
		}

		public Method getMethod() {
			return method;
		}

		public Hander setMethod(Method method) {
			this.method = method;
			return this;
		}

		public String getUrl() {
			return url;
		}

		public Hander setUrl(String url) {
			this.url = url;
			return this;
		}
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			doDispatch(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
