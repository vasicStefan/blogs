package blog;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import model.BlogKorisnik;

public class BlogSaveInterceptor {

	@EJB
	BlogKategorijeBean bkb;
	
	@AroundInvoke
	public Object interceptSave(InvocationContext ctx) throws Exception {
		Object[] params=ctx.getParameters();
		
		BlogKorisnik bk=(BlogKorisnik) params[0];
		
		if(bk!=null){
			bkb.incPostavljenoBlogova();
			System.out.println("NOvi blog dodat!");
		}
		
		
		return ctx.proceed();
		
		
	}
	
}
