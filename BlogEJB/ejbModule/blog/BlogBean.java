package blog;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Blog;
import model.BlogKateg;
import model.BlogKorisnik;

/**
 * Session Bean implementation class BlogBean
 */
@Stateful
@LocalBean
public class BlogBean implements BlogBeanLocal {

  @PersistenceContext
  EntityManager em;

	@Override
	public BlogKorisnik Login(String username, String password) {
		// TODO Auto-generated method stub
		
		
		try {
			System.out.println("USERNAME "+ username);
			Query q=em.createQuery("select bk from BlogKorisnik bk where bk.username= :username and bk.password= :password");
			q.setParameter("username",username);
			q.setParameter("password",password);
			return (BlogKorisnik)q.getResultList().get(0);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	@Interceptors(BlogSaveInterceptor.class)
	public Blog SaveBlog(BlogKorisnik k, int BlogKatId, String text, int likeNo) {
		// TODO Auto-generated method stub
		try {
			Blog b=new Blog();
			b.setBlogKorisnik(k);
			b.setText(text);
			b.setDatum(new Date());
			b.setBlogKateg(em.find(BlogKateg.class, BlogKatId));
			b.setLikeNo(likeNo);
			em.persist(b);
			return b;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	
	@PostConstruct
	public void Kreiran(){
		
		System.out.println("unisten je bean");
	}
	@PreDestroy
	public void Unisten(){
		
		System.out.println("Kreiran je bean");
	}

}
