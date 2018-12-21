package Controller;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.accountDAO;
import Model.ACCOUNT;

@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private accountDAO accDAO;

	public AccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		accDAO = new accountDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		ACCOUNT acc = new ACCOUNT();
		String ad;
		try {
			acc = accDAO.Login(request.getParameter("tenDN"), request.getParameter("mK"));

			if (acc != null && acc.getQuyenHan().equals("admin")) {
				request.setAttribute("acc", acc);
				response.sendRedirect("/FastFood/SanPhamServlet");
			}
			if (acc != null && acc.getQuyenHan().equals("user")) {
				response.sendRedirect("/FastFood/TrangChu.jsp");
			}
			if (acc == null) {
				request.setAttribute("error", "Sai ten dang nhap hoac pass");
				response.sendRedirect("/FastFood/Login.jsp");
			}
			request.setAttribute("account",acc);

		} catch (

		SQLException e) {
			e.printStackTrace();
			request.setAttribute("loi", e.toString());
		}

		/*
		 * RequestDispatcher dispatcher =
		 * getServletContext().getRequestDispatcher("/TrangChu.jsp");
		 */

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

}
