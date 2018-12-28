package Controller;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DAO.accountDAO;
import Model.ACCOUNT;
import javafx.scene.control.Alert;

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
		HttpSession session = request.getSession();
		
		try {
			acc = accDAO.Login(request.getParameter("tenDN"), request.getParameter("mK"));

			if (acc != null && acc.getQuyenHan().equals("Admin")) {

				
				session.setAttribute("acc", acc);
				
				/*session.setAttribute("tenDN", tenDN);
				session.setAttribute("mK", mK);*/
				RequestDispatcher dispatcher = request.getRequestDispatcher("/SanPhamServlet");
				dispatcher.forward(request, response);
			}
			if (acc != null && acc.getQuyenHan().equals("User")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/TrangChu.jsp");
				dispatcher.forward(request, response);

			}
			if (acc == null) {
				response.sendRedirect(request.getContextPath()+"/Login.jsp?error=0");
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
