package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.KhachHangDAO;
import DAO.*;
import Model.ACCOUNT;
import Model.KhachHang;


import java.util.List;


@WebServlet(name ="KhachHang",urlPatterns= { "/KhachHangServlet","/KhachHangServlet/insert" ,"/KhachHangServlet/delete" })
public class KhachHangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private KhachHangDAO khDAO;
	private accountDAO accDAO;
    
    public KhachHangServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        khDAO = new KhachHangDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String path = request.getServletPath();
		switch (path) {
		case "/KhachHangServlet":
			listKhachHang(request, response);
			break;
		case "/KhachHangServlet/insert":
			try {
				insertKhachHang(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/KhachHangServlet/delete":
			try {
				deleteKhachHang(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
    	
    	
	}
	private void listKhachHang(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<KhachHang> listKH;
		try {
			listKH = khDAO.listAllKH();
			request.setAttribute("listKH", listKH);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("loi", e.toString());
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminQLKH.jsp");
    	dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
	private void insertKhachHang(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		
		ACCOUNT acc = new ACCOUNT();
		acc.setTenDN(request.getParameter("tenDN"));
		acc.setmK(request.getParameter("mK"));
		acc.setQuyenHan("User");
	
		//acc.setQuyenHan(request.getParameter("quyenHan"));
		accDAO.insertACC(acc);
		
		KhachHang kh=new KhachHang();
		kh.setMaKH(request.getParameter("maKH"));
		kh.setTenDN(request.getParameter("tenDN"));
		kh.setHoTen(request.getParameter("hoTen"));
		kh.setDiaChi(request.getParameter("diaChi"));
		kh.setEmail(request.getParameter("email"));
		kh.setSdt(request.getParameter("sdt"));
		kh.setTichLuy(0);
		
		khDAO.insertKH(kh);
		
		System.out.println("loi");
		response.sendRedirect("/Version3/KhachHangServlet");

	}
	
	private void deleteKhachHang(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
		KhachHang kh=new KhachHang();
		kh.setMaKH(request.getParameter("maKH"));
		
//		ACCOUNT acc=new ACCOUNT();
//		acc.setTenDN(request.getParameter("tenDN"));
		
		khDAO.deleteKH(kh);
//		accDAO.deleteACC(acc);
		
    	
		
		String url = request.getContextPath() + "/KhachHangServlet";
        response.sendRedirect(url);
    }
	

}
