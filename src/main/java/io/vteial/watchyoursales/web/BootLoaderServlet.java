package io.vteial.watchyoursales.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import com.google.common.base.Throwables;
import com.google.common.net.MediaType;

@Slf4j
public class BootLoaderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try {

			log.info("---------------------------------------------------------");
			log.info("Boostrap started..");

			log.info("Boostrap finished..");
			log.info("---------------------------------------------------------");

		} catch (Throwable t) {
			t.printStackTrace();
			Throwables.propagate(t);
		}
	}

	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType(MediaType.PLAIN_TEXT_UTF_8.toString());

		PrintWriter out = res.getWriter();
		out.println("Welcome to Harmoney!");
		out.close();
	}
}
