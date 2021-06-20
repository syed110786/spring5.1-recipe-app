package ali.springframework.spring5.recipeapp.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;

public class CategoryTest {

	Category category=new Category();
	
//	@Before(value = "Setting the value")
//	public void setUp() {
//		category = new Category();
//	}
	
	@Test
	public void getId() {
		Long idValue=4L;
		category.setId(idValue);
		assertEquals(idValue,category.getId());
	}
}
