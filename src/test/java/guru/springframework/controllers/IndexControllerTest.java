package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(categoryRepository,unitOfMeasureRepository,recipeService);

        Category cat = new Category();
        cat.setCategoryName("Italian");
        cat.setId(2L);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setUom("Cup");
        uom.setId(5L);

        when(categoryRepository.findByCategoryName("Italian")).thenReturn(Optional.of(cat));
        when(unitOfMeasureRepository.findByUom("Cup")).thenReturn(Optional.of(uom));

    }

    @Test
    public void mvcTest() throws Exception {
        MockMvc mvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {
        Set<Recipe> recipesData = new HashSet<>();
        Recipe recipe1 = new Recipe();
        recipe1.setDescription("test 1");
        recipesData.add(recipe1);
        Recipe recipe2 = new Recipe();
        recipe2.setDescription("test 2");
        recipesData.add(recipe2);

        when(recipeService.getAllRecipes()).thenReturn(recipesData);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String index = indexController.getIndexPage(model);

        assertEquals("index",index);

        verify(recipeService,times(1)).getAllRecipes();

        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        Set<Recipe> setInController = argumentCaptor.getValue();
        System.out.println("recipes object on map " + setInController.size());

        assertEquals(2,setInController.size());

    }
}