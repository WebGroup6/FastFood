package Controller;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import Model.*;
import DAO.*;

@WebServlet(name = "LoaiSP", urlPatterns = { "/LoaiSPServlet", "/LoaiSPServlet/insert", "/LoaiSPServlet/delete",
		"/LoaiSPServlet/edit" })

public class LoaiSPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoaiSPDAO loaiSPDAO;

	public LoaiSPServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");

		loaiSPDAO = new LoaiSPDAO(jdbcURL);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletPath();
		switch (path) {
		case "/LoaiSPServlet":
			listLoaiSP(request, response);
			break;
		case "/LoaiSPServlet/insert":
			try {
				insertLoaiSP(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/LoaiSPServlet/delete":
			try {
				deleteLoaiSP(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/LoaiSPServlet/edit":
			try {
				editLoaiSP(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void listLoaiSP(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<LoaiSP> listLoaiSP;
		try {
			listLoaiSP = loaiSPDAO.listAllLoaiSP();
			request.setAttribute("listLoaiSP", listLoaiSP);
		} catch (SQLException e) {
			e.printStackTrace();

		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminLoaiSP.jsp");
		dispatcher.forward(request, response);
	}

	private void insertLoaiSP(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		LoaiSP loaisp = new LoaiSP();
		loaisp.setMaLoai(request.getParameter("maLoai"));
		loaisp.setTenLoaiSP(request.getParameter("tenLoaiSP"));

		loaiSPDAO.insertLoaiSP(loaisp);

		response.sendRedirect("/Version3/LoaiSPServlet");

	}

	private void editLoaiSP(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

//		String maLoai=request.getParameter("maLoai");
//		String tenLoai=request.getParameter("tenLoaiSP");
		LoaiSP loaisp = new LoaiSP();
		loaisp.setMaLoai(request.getParameter("maLoai"));
		loaisp.setTenLoaiSP(request.getParameter("tenLoaiSP"));

		try {
			loaiSPDAO.updateLoaiSP(loaisp);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		request.setAttribute("sua", loaisp);

		String url = request.getContextPath() + "/LoaiSPServlet";
		response.sendRedirect(url);
	}

	private void deleteLoaiSP(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String maLoai = request.getParameter("maLoai");
		LoaiSP loaisp = new LoaiSP(maLoai);
		try {
			loaiSPDAO.deleteLoaiSP(loaisp);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		String url = request.getContextPath() + "/LoaiSPServlet";
		response.sendRedirect(url);
	}

}
