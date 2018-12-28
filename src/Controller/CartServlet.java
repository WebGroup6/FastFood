package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Cart;
import Model.Item;
import Model.SanPham;
import DAO.SanPhamDAO;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SanPhamDAO spDAO;
   
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String command=request.getParameter("command");
		String maSP=request.getParameter("MaSP");
		Cart cart=(Cart) session.getAttribute("cart");
		
		try {
			SanPham sp= spDAO.getSP(maSP);
			switch (command) {
			case "plus":
				if(cart.getCartItems().containsKey(cart))
				{
					cart.plustoCart(maSP, new Item(sp, cart.getCartItems().get(maSP).getQuantity()));
				}
				else {
					cart.plustoCart(maSP, new Item(sp,1));
				}
				break;

		
			}
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		session.setAttribute("cart", cart);
		response.sendRedirect("/FastFood/ThucDon");
		
	}

}
