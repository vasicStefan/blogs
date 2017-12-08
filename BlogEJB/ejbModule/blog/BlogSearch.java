package blog;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Blog;
import model.BlogKorisnik;

/**
 * Session Bean implementation class BlogSearch
 */
@Stateless
@LocalBean
public class BlogSearch implements BlogSearchLocal {

	 @PersistenceContext
	  EntityManager em;
    /**
     * Default constructor. 
     */
    public BlogSearch() {
        // TODO Auto-generated constructor stub
    }

	@Override
	@Interceptors(PretragaInterceptor.class)
	public List<Blog> Search(String text) {
		try {
			Query q=em.createQuery("select b from Blog b where b.text like :likeText");
			q.setParameter("likeText","%"+text+"%");
			return q.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

}
