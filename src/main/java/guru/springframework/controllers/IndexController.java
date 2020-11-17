package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;
import java.util.Optional;

/**
 * Created by jt on 6/1/17.
 */
@Slf4j
@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
        log.debug("In IndexController!!");
        Optional<Category> categoryObject = categoryRepository.findByCategoryName("Italian");
        Optional<UnitOfMeasure> uomObject = unitOfMeasureRepository.findByDescription("Cup");

        System.out.println("category id :" + categoryObject.get().getId());
        System.out.println("uom id :" + uomObject.get().getId());

        Set<Recipe> recipes = this.recipeService.getAllRecipes();
        model.addAttribute("recipes",recipes);
        return "index";
    }
}
