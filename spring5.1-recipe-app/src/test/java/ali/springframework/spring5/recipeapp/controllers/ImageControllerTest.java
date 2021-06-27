package ali.springframework.spring5.recipeapp.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ali.springframework.spring5.recipeapp.commands.RecipeCommand;
import ali.springframework.spring5.recipeapp.services.ImageService;
import ali.springframework.spring5.recipeapp.services.RecipeService;

public class ImageControllerTest {

	@Mock
	ImageService imageService;
	
	//@Mock
	ImageController controller;
	
	@Mock
	RecipeService recipeService;
	
	MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new ImageController(imageService,recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void getImageForm() throws Exception {
		//given
		RecipeCommand command = new RecipeCommand();
		command.setId(1L);
		
		Mockito.when(recipeService.findCommandById(Mockito.anyLong())).thenReturn(command);
		
		//when
		mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/image"))
		       .andExpect(MockMvcResultMatchers.status().isOk())
		       .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
		
		Mockito.verify(recipeService,Mockito.times(1)).findCommandById(Mockito.anyLong());
		       
		
	}
	
	
	@Test
	public void handleImagePost() throws Exception {
		MockMultipartFile multipartFile = new MockMultipartFile("imagefile","testing.txt","text/plain","Spring Framework Guru".getBytes());
		
		this.mockMvc.perform(MockMvcRequestBuilders.multipart("/recipe/1/image").file(multipartFile))
		          .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		          .andExpect(MockMvcResultMatchers.header().string("Location","/recipe/1/show"));
		
		Mockito.verify(imageService,Mockito.times(1)).saveImageFile(Mockito.anyLong(), Mockito.any());
	}
	@Test
	 public void renderImageFromDB() throws Exception {

	        //given
	        RecipeCommand command = new RecipeCommand();
	        command.setId(1L);

	        String s = "fake image text";
	        Byte[] bytesBoxed = new Byte[s.getBytes().length];

	        int i = 0;

	        for (byte primByte : s.getBytes()){
	            bytesBoxed[i++] = primByte;
	        }

	        command.setImage(bytesBoxed);

	        Mockito.when(recipeService.findCommandById(Mockito.anyLong())).thenReturn(command);

	        //when
	        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/recipeimage"))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andReturn().getResponse();

	        byte[] reponseBytes = response.getContentAsByteArray();

	        assertEquals(s.getBytes().length, reponseBytes.length);
	    }
}
