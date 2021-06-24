package ali.springframework.spring5.recipeapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ali.springframework.spring5.recipeapp.commands.IngredientCommand;
import ali.springframework.spring5.recipeapp.converters.IngredientCommandToIngredient;
import ali.springframework.spring5.recipeapp.converters.IngredientToIngredientCommand;
import ali.springframework.spring5.recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import ali.springframework.spring5.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import ali.springframework.spring5.recipeapp.domain.Ingredient;
import ali.springframework.spring5.recipeapp.domain.Recipe;
import ali.springframework.spring5.recipeapp.repositories.RecipeRepository;
import ali.springframework.spring5.recipeapp.repositories.UnitOfMeasureRepository;

public class IngredientServiceImplTest {

	private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

	@Mock
	RecipeRepository recipeRepository;
	
	@Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
	
	IngredientService ingredientService;
	
	public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }
	
	@BeforeEach
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient,
                recipeRepository, unitOfMeasureRepository);
	}
	
	@Test
	public void findByRecipeIdAndId() throws Exception{
		
	}
	
	@Test
	public void findByRecipeIdAndRecipeIdHappyPath() throws Exception{
		
		//given
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1L);
		
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId(2L);
		
		Ingredient ingredient3 = new Ingredient();
		ingredient3.setId(3L);
		
		recipe.addIngredient(ingredient3);
		recipe.addIngredient(ingredient2);
		recipe.addIngredient(ingredient1);
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		Mockito.when(recipeRepository.findById(Mockito.anyLong())).thenReturn(recipeOptional);
		
		//then
		IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L,3L);
		
		//when
		assertEquals(Long.valueOf(3L),ingredientCommand.getId());
		assertEquals(Long.valueOf(1L),ingredientCommand.getRecipeId());
		Mockito.verify(recipeRepository,Mockito.times(1)).findById(Mockito.anyLong());
	}
}
