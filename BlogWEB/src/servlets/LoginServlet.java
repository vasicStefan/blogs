package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.BlogBean;
import blog.BlogKategorijeBean;
import model.BlogKorisnik;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@EJB
	BlogBean bb;
	
	@EJB
	BlogKategorijeBean bkb;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		System.out.println("STIGAOOO");
		
		BlogKorisnik bk= bb.Login(request.getParameter("username").toString(), request.getParameter("password").toString());
		
		if(bk!=null){
			request.getSession().setAttribute("user", bk);
			request.setAttribute("kategorije", bkb.GetAll());
			RequestDispatcher rd=request.getRequestDispatcher("/dodajBlog.jsp");
			rd.forward(request, response);
		}else{
			RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
		
		
	}

}
