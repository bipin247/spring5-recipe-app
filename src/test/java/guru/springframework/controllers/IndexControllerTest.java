package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import guru.springframework.services.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.BiConsumer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IndexControllerTest {
    IndexController indexController;
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeService recipeService;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
    @Mock
    CategoryRepository categoryRepository;
    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(categoryRepository,unitOfMeasureRepository,recipeService);
    }

    @Test
    public void getIndexPage() {
        HashSet<Recipe> recipesData = new HashSet<>();
        Recipe recipe = new Recipe();
        recipesData.add(recipe);

        Category cat = new Category();
        cat.setCategoryName("Italian");
        cat.setId(2L);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setUom("Cup");
        uom.setId(5L);

        when(categoryRepository.findByCategoryName("Italian")).thenReturn(Optional.of(cat));
        when(unitOfMeasureRepository.findByUom("Cup")).thenReturn(Optional.of(uom));

        String index = indexController.getIndexPage(model);

        assertEquals("index",index);

        verify(recipeService,times(1)).getAllRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());

        System.out.println("recipes object on map " + model.asMap().size());

    }
}