package blog;

import java.util.List;

import javax.ejb.Local;

import model.Blog;

@Local
public interface BlogSearchLocal {
	
	List<Blog> Search(String text);

}
