package ali.springframework.spring5.recipeapp.controllers;

import java.util.HashSet;

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

import ali.springframework.spring5.recipeapp.commands.IngredientCommand;
import ali.springframework.spring5.recipeapp.commands.RecipeCommand;
import ali.springframework.spring5.recipeapp.services.IngredientService;
import ali.springframework.spring5.recipeapp.services.RecipeService;
import ali.springframework.spring5.recipeapp.services.UnitOfMeasureService;

public class IngredientControllerTest {


    @Mock
    RecipeService recipeService;
    
    @Mock
    IngredientService ingredientService;
    
    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController controller;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
      MockitoAnnotations.initMocks(this);

        controller = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testListIngredients() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        Mockito.when(recipeService.findCommandById(Mockito.anyLong())).thenReturn(recipeCommand);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredient/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));

        //then
        Mockito.verify(recipeService, Mockito.times(1)).findCommandById(Mockito.anyLong());
    }
    
    @Test
    public void testShowIngredient() throws Exception{
    	
    	//given
    	IngredientCommand ingredientCommand = new IngredientCommand();
    	
    	//when
    	Mockito.when(ingredientService.findByRecipeIdAndIngredientId(Mockito.anyLong(),Mockito.anyLong())).thenReturn(ingredientCommand);
    	
    	//then
    	mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/show/2"))
    	       .andExpect(MockMvcResultMatchers.status().isOk())
    	       .andExpect(MockMvcResultMatchers.view().name("/recipe/ingredient/show"))
    	       .andExpect(MockMvcResultMatchers.model().attributeExists("ingredient"));
    }
    
    @Test
    public void testNewIngredientForm() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        //when
        Mockito.when(recipeService.findCommandById(Mockito.anyLong())).thenReturn(recipeCommand);
        Mockito.when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredient/ingredientform"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ingredient"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("uomList"));

        Mockito.verify(recipeService, Mockito.times(1)).findCommandById(Mockito.anyLong());

    }

    @Test
    public void testUpdateIngredientForm() throws Exception {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();

        //when
        Mockito.when(ingredientService.findByRecipeIdAndIngredientId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(ingredientCommand);
        Mockito.when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/2/update"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredient/ingredientform"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ingredient"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("uomList"));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        //when
        Mockito.when(ingredientService.saveIngredientCommand(Mockito.any())).thenReturn(command);

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/recipe/2/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
        )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/recipe/2/ingredient/3/show"));

    }
    
    @Test
    public void testDeleteIngredient() throws Exception {

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/2/ingredient/3/delete")
        )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/recipe/2/ingredients"));

        Mockito.verify(ingredientService, Mockito.times(1)).deleteById(Mockito.anyLong(), Mockito.anyLong());

    }

}