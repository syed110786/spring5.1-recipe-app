package ali.springframework.spring5.recipeapp.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ali.springframework.spring5.recipeapp.commands.RecipeCommand;
import ali.springframework.spring5.recipeapp.converters.RecipeCommandToRecipe;
import ali.springframework.spring5.recipeapp.converters.RecipeToRecipeCommand;
import ali.springframework.spring5.recipeapp.domain.Recipe;
import ali.springframework.spring5.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService{

	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
			RecipeToRecipeCommand recipeToRecipeCommand) {
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}
	@Override
	public Set<Recipe> getRecipes() {
		//log.info("I am in Service");
	Set<Recipe> recipeSet = new HashSet<>();
	recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}
	@Override
	public Recipe findById(Long l) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(l);
		
		if(!recipeOptional.isPresent()) {
			throw new RuntimeException("Recipe Not Found");
		}
		return recipeOptional.get();
	}
	
	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand command) 
	{
		Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
		
		Recipe savedRecipe = recipeRepository.save(detachedRecipe);
		log.debug("Saved RecipeId: "+savedRecipe.getId());
		return recipeToRecipeCommand.convert(savedRecipe);
	}

	
}
