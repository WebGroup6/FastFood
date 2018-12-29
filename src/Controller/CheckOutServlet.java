package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.HoaDonDAO;
import DAO.LoaiSPDAO;
import DAO.SanPhamDAO;
import Model.Cart;

@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HoaDonDAO hdDAO;

	public CheckOutServlet() {
		super();

	}

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		hdDAO = new HoaDonDAO(jdbcURL);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session=request.getSession();

	}

}
