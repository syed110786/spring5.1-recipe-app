package ali.springframework.spring5.recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ali.springframework.spring5.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {

	private final RecipeService recipeService;
	
	
	public IndexController(RecipeService recipeService) {
		this.recipeService = recipeService;
		log.debug("Loading Controllers");
	}

	@RequestMapping({"","/","/index"})
	public String getIndexPage(Model model) {
//		Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
//		Optional<UnitOfMeasure> unitOfMeasureOptional=unitOfMeasureRepository.findByDescription("Teaspoon");
//		
//		System.out.println("Category Id:"+categoryOptional.get().getId());
//		System.out.println("Unit Of Measure Id:"+unitOfMeasureOptional.get().getId());
		model.addAttribute("recipes",recipeService.getRecipes());
		return "index";
	}
}
