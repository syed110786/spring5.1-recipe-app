package ali.springframework.spring5.recipeapp.services;

import ali.springframework.spring5.recipeapp.commands.IngredientCommand;

public interface IngredientService {

	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId,Long ingredientId);
	IngredientCommand saveIngredientCommand(IngredientCommand command);
    public void deleteById(Long recipeId, Long idToDelete);
}
