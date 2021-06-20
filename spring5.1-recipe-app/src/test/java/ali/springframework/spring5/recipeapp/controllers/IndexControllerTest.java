package ali.springframework.spring5.recipeapp.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import ali.springframework.spring5.recipeapp.domain.Recipe;
import ali.springframework.spring5.recipeapp.repositories.RecipeRepository;
import ali.springframework.spring5.recipeapp.services.RecipeServiceImpl;

class IndexControllerTest {

	@InjectMocks
	private IndexController indexController;
	
	@Mock
	private RecipeServiceImpl recipeService;
	
	@Mock
	private RecipeRepository recipeRepository;
	
	@Mock
	Model model;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		indexController=new IndexController(recipeService);
	}
	
	@Test
	public void testMockMVC() throws Exception{
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andExpect(MockMvcResultMatchers.view().name("index"));
	}
	@Test
	public void getIndexPageTest() {
		//Model model ;
		//model.addAttribute("recipes",recipeService.getRecipes());
		//given
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Set<Recipe> setRecipe=new HashSet<>();
		setRecipe.add(recipe);
		//= Model.addAttribute("recipes",recipeService.getRecipes());
		
		Mockito.when(recipeRepository.findAll()).thenReturn(setRecipe);
		Mockito.when(recipeService.getRecipes()).thenReturn(setRecipe);
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
		//when
		String s=indexController.getIndexPage(model);
		
		//then
		assertEquals("index",s);
		Mockito.verify(recipeService,Mockito.times(1)).getRecipes();
		//Mockito.verify(model,Mockito.times(1)).addAttribute(Mockito.eq("recipes"),Mockito.anySet());
		Mockito.verify(model,Mockito.times(1)).addAttribute(Mockito.eq("recipes"),argumentCaptor.capture());
				
		Set<Recipe> setIndexController = argumentCaptor.getValue();
		assertEquals(1,setIndexController.size());
	}
	
	
	
	

}
