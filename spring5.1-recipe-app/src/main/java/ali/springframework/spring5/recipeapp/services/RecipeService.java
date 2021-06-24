package ali.springframework.spring5.recipeapp.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import ali.springframework.spring5.recipeapp.commands.RecipeCommand;
import ali.springframework.spring5.recipeapp.domain.Recipe;

@Service
public interface RecipeService {
	Set<Recipe> getRecipes();
	Recipe findById(Long l);
	RecipeCommand saveRecipeCommand(RecipeCommand command);
	
	public RecipeCommand findCommandById(Long l);
	
    public void deleteById(Long idToDelete);
     
}
