package ali.springframework.spring5.recipeapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ali.springframework.spring5.recipeapp.domain.Recipe;
import ali.springframework.spring5.recipeapp.repositories.RecipeRepository;

public class RecipeServiceImplTest {
	//requires junit;
	//@InjectMocks
	private RecipeServiceImpl recipeService;
		
	@Mock
	private RecipeRepository recipeRepository;
	
	/*
	 * public Set<Recipe> getRecipes() {
		//log.info("I am in Service");
	Set<Recipe> recipeSet = new HashSet<>();
	recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}
	 * */
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository);
	}
	
	@Test
	public void getRecipesTest() {
		
		Set<Recipe> expectedSetRecipe =new HashSet<>();
		Recipe expectedRecipe = new Recipe();
//		expectedRecipe.setId(1L);
//		expectedRecipe.setCookTime(5);
		expectedSetRecipe.add(expectedRecipe);
		Mockito.when(recipeRepository.findAll()).thenReturn(expectedSetRecipe); 
//		
//		//Mockito.when(null).thenReturn(null);
//		Set<Recipe> actualRecipe=recipeServiceImpl.getRecipes();
//		assertEquals(expectedSetRecipe.size(),1);
		Set<Recipe> recipes = recipeService.getRecipes();
		assertEquals(recipes.size(),1);
		Mockito.verify(recipeRepository,Mockito.times(1)).findAll();
		
	}
	
	@Test
	public void getRecipesTestById() {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		Mockito.when(recipeRepository.findById(Mockito.anyLong())).thenReturn(recipeOptional);
		Recipe recipeReturned = recipeService.findById(1L);
		assertNotNull( recipeReturned,"Null recipe returned");
		Mockito.verify(recipeRepository,Mockito.times(1)).findById(Mockito.anyLong());
		Mockito.verify(recipeRepository,Mockito.never()).findAll();
		
	}
}
