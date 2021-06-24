package ali.springframework.spring5.recipeapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ali.springframework.spring5.recipeapp.converters.RecipeCommandToRecipe;
import ali.springframework.spring5.recipeapp.converters.RecipeToRecipeCommand;
import ali.springframework.spring5.recipeapp.domain.Recipe;
import ali.springframework.spring5.recipeapp.repositories.RecipeRepository;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        Mockito.when(recipeRepository.findById(Mockito.anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        Assertions.assertNotNull(recipeReturned);
        Mockito.verify(recipeRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(recipeRepository, Mockito.never()).findAll();
    }

    @Test
    public void getRecipesTest() throws Exception {

        Recipe recipe = new Recipe();
        HashSet receipesData = new HashSet();
        receipesData.add(recipe);

        Mockito.when(recipeService.getRecipes()).thenReturn(receipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
        Mockito.verify(recipeRepository, Mockito.never()).findById(Mockito.anyLong());
    }
    
    @Test
    public void testDeleteById() throws Exception{
    	Long idToDelete = Long.valueOf(2L);
    	recipeService.deleteById(idToDelete);
    	//no when, since method has void return type
    	Mockito.verify(recipeRepository,Mockito.times(1)).deleteById(Mockito.anyLong());
    }

}



