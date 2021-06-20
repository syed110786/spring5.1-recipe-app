package ali.springframework.spring5.recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import ali.springframework.spring5.recipeapp.commands.IngredientCommand;
import ali.springframework.spring5.recipeapp.domain.Ingredient;

public class IngredientCommandToIngredient implements Converter<IngredientCommand,Ingredient>{

	private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;
	
	public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
		this.uomConverter = uomConverter;
	}
	
	@Nullable
	@Override
	public Ingredient convert(IngredientCommand source) {
		if(source==null) {
			return null;
		}
		
		final Ingredient ingredient = new Ingredient();
		ingredient.setId(source.getId());
		ingredient.setAmount(source.getAmount());
		ingredient.setDescription(source.getDescription());
		ingredient.setUom(uomConverter.convert(source.getUnitOfMeasure()));
		return ingredient;
	}
}
