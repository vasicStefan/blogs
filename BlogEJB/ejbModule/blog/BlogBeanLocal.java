package blog;

import javax.ejb.Local;
import javax.ejb.Remote;

import model.Blog;
import model.BlogKorisnik;

@Local
public interface BlogBeanLocal {
	
	BlogKorisnik Login(String username, String password);
	
	Blog SaveBlog(BlogKorisnik k,int BlogKatId, String text,int likeNo);

}
