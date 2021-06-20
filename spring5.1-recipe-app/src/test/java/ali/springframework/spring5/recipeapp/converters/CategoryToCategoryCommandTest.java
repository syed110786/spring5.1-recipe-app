package ali.springframework.spring5.recipeapp.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ali.springframework.spring5.recipeapp.commands.CategoryCommand;
import ali.springframework.spring5.recipeapp.domain.Category;

public class CategoryToCategoryCommandTest {

	public static final Long ID_VALUE = 1L;
	public static final String DESCRIPTION = "descript";
	
	CategoryToCategoryCommand converter;
	
	@BeforeEach
	public void setUp() throws Exception {
		converter = new CategoryToCategoryCommand();
	}
	
	@Test
	public void testNullObject() throws Exception{
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() throws Exception{
		assertNotNull(converter.convert(new Category()));
	}
	
	@Test
	public void convert() throws Exception{
		//given
		Category category = new Category();
		category.setId(ID_VALUE);
		category.setDescription(DESCRIPTION);
		
		//when
		CategoryCommand categoryCommand = converter.convert(category);
		
		//then
		assertEquals(ID_VALUE,categoryCommand.getId());
		assertEquals(DESCRIPTION,categoryCommand.getDescription());
	}
}
