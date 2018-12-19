package Controller;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import DAO.NhanVienDAO;
import DAO.accountDAO;
import Model.ACCOUNT;
import Model.LoaiSP;
import Model.NhanVien;

import java.util.List;

@WebServlet(name ="NhanVien",urlPatterns= { "/NhanVienServlet","/NhanVienServlet/insert", "/NhanVienServlet/delete" })


public class NhanVienServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
	private NhanVienDAO nvDAO;
	private accountDAO accDAO;
    
    public NhanVienServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        nvDAO = new NhanVienDAO(jdbcURL, jdbcUsername, jdbcPassword);
        accDAO=new accountDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String path = request.getServletPath();
		switch (path) {
		case "/NhanVienServlet":
			listNhanVien(request, response);
			break;
		case "/NhanVienServlet/insert":
			try {
				insertNhanVien(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/NhanVienServlet/delete":
			try {
				deleteNV(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}

	}

	private void insertNhanVien(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//insertACC(request, response);
		ACCOUNT acc = new ACCOUNT();
		acc.setTenDN(request.getParameter("tenDN"));
		acc.setmK(request.getParameter("mK"));
		//acc.setQuyenHan("Admin");
	
		acc.setQuyenHan(request.getParameter("quyenHan"));
		accDAO.insertACC(acc);
		
		
		NhanVien nv = new NhanVien();
		nv.setMaNV(request.getParameter("maNV"));
		nv.setHoTen(request.getParameter("hoTen"));
		nv.setGioiTinh(request.getParameter("gioiTinh"));
		nv.setDiaChi(request.getParameter("diaChi"));
		nv.setEmail(request.getParameter("email"));
		nv.setSdt(request.getParameter("sdt"));
		nv.setTenDN(request.getParameter("tenDN"));
		

		nvDAO.insertNV(nv);
		System.out.println("loi");
		response.sendRedirect("/FastFood/NhanVienServlet");

	}
	
	private void deleteNV(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
		NhanVien nv=new NhanVien();
		nv.setMaNV(request.getParameter("maNV"));
		
//		ACCOUNT acc=new ACCOUNT();
//		acc.setTenDN(request.getParameter("tenDN"));
		
		nvDAO.deleteNV(nv);
//		accDAO.deleteACC(acc);
		
    	
		
		String url = request.getContextPath() + "/NhanVienServlet";
        response.sendRedirect(url);
    }

	

	private void listNhanVien(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<NhanVien> listNV;

		try {
			listNV = nvDAO.listAllNV();
			request.setAttribute("listNV", listNV);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("loi", e.toString());
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminQLNV.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	
//	private void insertACC(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException {
//
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//
//		ACCOUNT acc = new ACCOUNT();
//		acc.setTenDN(request.getParameter("tenDN"));
//		acc.setmK(request.getParameter("mK"));
//		acc.setQuyenHan("Admin");
//	
//		accDAO.insertACC(acc);
//
//		System.out.println("loi");
//		response.sendRedirect("/Version3/NhanVienServlet");
//
//	}
}
