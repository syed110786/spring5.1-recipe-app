package ali.springframework.spring5.recipeapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ali.springframework.spring5.recipeapp.commands.RecipeCommand;
import ali.springframework.spring5.recipeapp.domain.Recipe;
import ali.springframework.spring5.recipeapp.services.RecipeService;

public class RecipeControllerTest {

	@Mock
	RecipeService recipeService;
	
	RecipeController controller;
	
	MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		controller=new RecipeController(recipeService);
		mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testGetRecipe() throws Exception{
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		Mockito.when(recipeService.findById(Mockito.anyLong())).thenReturn(recipe);
		mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("recipe/show"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
	}
	
	@Test
	public void testGetNewRecipeForm() throws Exception {
		RecipeCommand command = new RecipeCommand();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/recipe/new"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("recipe/recipeform"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
	}
	
	@Test
    public void testPostNewRecipeForm() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        Mockito.when(recipeService.saveRecipeCommand(Mockito.any())).thenReturn(command);

        mockMvc.perform(MockMvcRequestBuilders.post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
        )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/recipe/show/2"));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        Mockito.when(recipeService.findCommandById(Mockito.anyLong())).thenReturn(command);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/update"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/recipeform"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    }
    
    @Test
    public void testDeleteAction() throws Exception{
    	mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/delete"))
    		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
    		.andExpect(MockMvcResultMatchers.view().name("redirect:/"));
    	Mockito.verify(recipeService,Mockito.times(1)).deleteById(Mockito.anyLong());
    }
    
    
}
