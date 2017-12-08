package blog;

import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import model.Blog;

public class PretragaInterceptor {
	
	@EJB
	BlogKategorijeBean bkb;
	
	@AroundInvoke
	public Object interceptPretraga(InvocationContext ctx) throws Exception{
		
		List<Blog> rez=(List<Blog>)ctx.proceed();
		for (Blog item : rez) {
			bkb.incBlogPregledi(item.getIdBlog());
			System.out.println("dodao za blog "+ item.getIdBlog());
		}
		
		return rez;
		
	}

}
