package ali.springframework.spring5.recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ali.springframework.spring5.recipeapp.commands.RecipeCommand;
import ali.springframework.spring5.recipeapp.services.RecipeService;

@Controller
public class RecipeController {

	private final RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	
	@GetMapping("/recipe/show/{id}")
	public String showById(@PathVariable String id,Model model) {
		model.addAttribute("recipe",recipeService.findById(new Long(id)));
		return "recipe/show";
	}
	
	
	@GetMapping("/recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe",new RecipeCommand());
		return "recipe/recipeform";
	}

	
	@GetMapping("recipe/{id}/update")
	public String updateRecipe(@PathVariable String id,Model model) {
		model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
		return "recipe/recipeform";
	}	
	
	@PostMapping("recipe")
	public String saveOrUpdate(@ModelAttribute("recipe") RecipeCommand command) {
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/show/" +savedCommand.getId();
	}
	
	@GetMapping("recipe/{id}/delete")
	public String deleteById(@PathVariable String id) {
		//log.debug("Deleting id: "+id);
		recipeService.deleteById(Long.valueOf(id));;
		return "redirect:/";
	}
}
