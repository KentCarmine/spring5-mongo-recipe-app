package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {
    private static final String DESCRIPTION = "Test Desc";

    @Autowired
    private UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    private UnitOfMeasure uom;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureReactiveRepository.deleteAll().block();

        uom = new UnitOfMeasure();
        uom.setDescription(DESCRIPTION);
    }

    @Test
    public void testSave() throws Exception {
        unitOfMeasureReactiveRepository.save(uom).block();

        assertEquals(Long.valueOf(1L), unitOfMeasureReactiveRepository.count().block());
    }

    @Test
    public void testFindByDescription() throws Exception {
        unitOfMeasureReactiveRepository.save(uom).block();

        UnitOfMeasure found = unitOfMeasureReactiveRepository.findByDescription(DESCRIPTION).block();
        assertNotNull(found.getId());
        assertEquals(DESCRIPTION, found.getDescription());
    }

}