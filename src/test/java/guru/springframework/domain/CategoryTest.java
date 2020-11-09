package guru.springframework.domain;

import lombok.Builder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    private Category category;
    @Before
    public void setup(){
        category = new Category();
    }

    @Test
    public void getId() {
        Long longId = 4L;

        category.setId(4L);
        assertEquals(longId,category.getId());


    }

    @Test
    public void getCategoryName() {
    }

    @Test
    public void getRecipes() {
    }
}