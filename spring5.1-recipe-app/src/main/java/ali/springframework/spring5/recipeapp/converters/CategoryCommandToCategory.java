package ali.springframework.spring5.recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import ali.springframework.spring5.recipeapp.commands.CategoryCommand;
import ali.springframework.spring5.recipeapp.domain.Category;
import lombok.Synchronized;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand,Category>{

	@Synchronized
	@Nullable
	@Override
	public Category convert(CategoryCommand source) {
		if(source==null) {
			return null;
		}
		
		final Category category = new Category();
		category.setId(source.getId());
		category.setDescription(source.getDescription());
		return category;
	}
}
