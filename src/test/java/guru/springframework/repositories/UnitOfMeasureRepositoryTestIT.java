package guru.springframework.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryTestIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    //@DirtiesContext
    public void findByUom() {

        assertEquals("Cup",
              unitOfMeasureRepository.findByDescription("Cup").get().getDescription());
    }

    @Test
    public void findByUomTeaspoon() {

        assertEquals("Teaspoon",
                unitOfMeasureRepository.findByDescription("Teaspoon").get().getDescription());
    }
}