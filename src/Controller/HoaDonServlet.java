package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.HoaDonDAO;

import Model.HoaDon;


import java.util.List;

@WebServlet(name ="HoaDon",urlPatterns= { "/HoaDonServlet" })
public class HoaDonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
	private HoaDonDAO hdDAO;
    
    public HoaDonServlet() {
        super();
    }
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        hdDAO = new HoaDonDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getServletPath();
		switch (path) {
		case "/HoaDonServlet":
			listHD(request, response);
			break;
		case "/HoaDonServlet/insert":
			try {
				insertHD(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/HoaDonServlet/delete":
			try {
				deleteHD(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/HoaDonServlet/edit":
			try {
				editHD(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
    	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void listHD(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<HoaDon> listHD;
		try {
			listHD = hdDAO.listAllHD();
			request.setAttribute("listHD", listHD);
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminQLHD.jsp");
    	dispatcher.forward(request, response);
	}
	
	
	private void insertHD(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HoaDon hd = new HoaDon();
		hd.setMaHD(request.getParameter("maHD"));
	
		

		hdDAO.insertHD(hd);

		response.sendRedirect("/Version3/HoaDonServlet");

	}
	private void editHD(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

//		String maHD =request.getParameter("maHD");
//		String tenLoai=request.getParameter("tenLoaiSP");
//		LoaiSP loaisp = new LoaiSP( maLoai,  tenLoai);
//
//		
//		try {			
//			loaiSPDAO.updateLoaiSP(loaisp);
//		
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//		
//		
//		request.setAttribute("sua",loaisp);
//		
//		String url = request.getContextPath() + "/HoaDonServlet";
//        response.sendRedirect(url);  
    }
 
    private void deleteHD(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
//    	String maHD=request.getParameter("maHD");
//		HoaDon hd = new HoaDon(maHD);
//		try {
//			hdDAO.deleteHD(hd);		
//			
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//		
//		String url = request.getContextPath() + "/HoaDonServlet";
//        response.sendRedirect(url);
    }
	

    
	
}
