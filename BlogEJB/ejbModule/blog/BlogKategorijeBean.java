package blog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.BlogKateg;


/**
 * Session Bean implementation class BlogKategorijeBean
 */
@Singleton
@LocalBean
public class BlogKategorijeBean implements BlogKategorijeBeanLocal {

	@PersistenceContext
	EntityManager em;
	
	@Resource
	TimerService ts;

	private HashMap<Integer, Integer> blogPregledi = new HashMap<>();
	private HashMap<Integer, Integer> blogPostavljaanje = new HashMap<>();
	private int postavljenoBlogova=0;

	public int getPostavljenoBlogova() {
		return postavljenoBlogova;
	}

	public void incPostavljenoBlogova() {
		this.postavljenoBlogova++;
	}

	public HashMap<Integer, Integer> getBlogPregledi() {
		return blogPregledi;
	}

	public void incBlogPregledi(int idBlog) {

		if (blogPregledi.containsKey(idBlog)) {

			blogPregledi.put(idBlog, blogPregledi.get(idBlog) + 1);
		} else {
			blogPregledi.put(idBlog, blogPregledi.get(idBlog));
		}
	}

	public HashMap<Integer, Integer> getBlogPostavljaanje() {
		return blogPostavljaanje;
	}

	public void incBlogPostavljaanje(int userId) {

		if (blogPregledi.containsKey(userId)) {

			blogPostavljaanje.put(userId, blogPregledi.get(userId) + 1);
		} else {
			blogPostavljaanje.put(userId, blogPregledi.get(userId));
		}
	}

	/**
	 * Default constructor.
	 */
	public BlogKategorijeBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<BlogKateg> GetAll() {
		// TODO Auto-generated method stub
		try {
			Query q = em.createQuery("select bk from BlogKateg bk ");
			return (List<BlogKateg>) q.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Schedule(second = "59", minute = "59", hour = "23",dayOfWeek="*", persistent = false)
	public void ispisPregledaNaKrajuDana() {
		System.out.println("broj dodatih blogova danas je: "+ postavljenoBlogova);
	}
	
	@Timeout
	public void updateFields(Timer t) {
		
		postavljenoBlogova=0;
	}

}
