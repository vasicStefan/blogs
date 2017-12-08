package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.BlogBean;
import blog.BlogKategorijeBean;
import blog.BlogSearch;
import model.Blog;
import model.BlogKorisnik;

/**
 * Servlet implementation class BlogServlet
 */
@WebServlet("/BlogServlet")
public class BlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	BlogBean bb;
	@EJB
	BlogSearch bs;
	
	@EJB
	BlogKategorijeBean bkb;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BlogServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String text=request.getParameter("text");
		List<Blog> re=bs.Search(text);
		request.setAttribute("blogs", re);
		
		System.out.println(re.size());
		RequestDispatcher rd = request.getRequestDispatcher("pretrazi.jsp");
		rd.forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String text = request.getParameter("text");
		int likeNo = Integer.parseInt(request.getParameter("likes"));
		int BlogKatId = Integer.parseInt(request.getParameter("katId"));

		bb.SaveBlog((BlogKorisnik) request.getSession().getAttribute("user"), BlogKatId, text, likeNo);
		request.setAttribute("kategorije", bkb.GetAll());
		RequestDispatcher rd = request.getRequestDispatcher("dodajBlog.jsp");
		rd.forward(request, response);
	}

}
