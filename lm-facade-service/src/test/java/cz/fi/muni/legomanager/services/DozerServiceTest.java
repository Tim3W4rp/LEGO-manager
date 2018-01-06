package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.config.ServiceConfigurationContext;
import cz.fi.muni.legomanager.dto.BrickDTO;
import cz.fi.muni.legomanager.dto.CategoryDTO;
import cz.fi.muni.legomanager.dto.KitDTO;
import cz.fi.muni.legomanager.dto.SetOfKitsDTO;
import cz.fi.muni.legomanager.dto.ShapeDTO;
import cz.fi.muni.legomanager.entity.Brick;
import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.entity.SetOfKits;
import cz.fi.muni.legomanager.entity.Shape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

/**
 * Test class for {@link DozerService}.
 *
 * @author Lukáš Dvořák
 */
@ContextConfiguration(classes = ServiceConfigurationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class DozerServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DozerService dozerService;

    private Category cars;
    private CategoryDTO carsDTO;
    private SetOfKits sportCars;
    private SetOfKitsDTO sportCarsDTO;
    private Kit ferrari;
    private KitDTO ferrariDTO;
    private Shape wheel;
    private ShapeDTO wheelDTO;
    private Brick leftFrontWheel;
    private BrickDTO leftFrontWheelDTO;

    @BeforeMethod
    public void setup() {

        cars = new Category();
        cars.setName("Cars");
        cars.setDescription("Great cars.");

        carsDTO = new CategoryDTO();
        carsDTO.setId(1L);
        carsDTO.setName("Cars");
        carsDTO.setDescription("Great cars.");

        ferrari = new Kit();
        ferrari.setDescription("Ferrari");
        ferrari.setPrice(100);
        ferrari.setAgeLimit(10);

        ferrariDTO = new KitDTO();
        ferrariDTO.setId(1L);
        ferrari.setDescription("Ferrari");
        ferrari.setPrice(100);
        ferrari.setAgeLimit(10);

        sportCars = new SetOfKits();
        sportCars.setDescription("Sport cars");
        sportCars.setPrice(BigDecimal.valueOf(1000));

        sportCarsDTO = new SetOfKitsDTO();
        sportCarsDTO.setId(1L);
        sportCars.setDescription("Sport cars");
        sportCars.setPrice(BigDecimal.valueOf(1000));

        wheel = new Shape();
        wheel.setName("Wheel");

        wheelDTO = new ShapeDTO();
        wheelDTO.setId(1L);
        wheelDTO.setName("Wheel");

        leftFrontWheel = new Brick();
        leftFrontWheel.setRed(0);
        leftFrontWheel.setGreen(0);
        leftFrontWheel.setBlue(0);

        leftFrontWheelDTO = new BrickDTO();
        leftFrontWheelDTO.setId(1L);
        leftFrontWheel.setRed(0);
        leftFrontWheel.setGreen(0);
        leftFrontWheel.setBlue(0);
    }

    @Test
    public void testMapToCategory() throws Exception {
        Category actual = dozerService.mapTo(carsDTO, Category.class);

        assertEquals(actual.getName(), carsDTO.getName());
        assertEquals(actual.getDescription(), carsDTO.getDescription());
    }

    @Test
    public void testMapToCategoryDTO() throws Exception {
        CategoryDTO actual = dozerService.mapTo(cars, CategoryDTO.class);

        assertEquals(actual.getName(), cars.getName());
        assertEquals(actual.getDescription(), cars.getDescription());
    }


    @Test
    public void testMapToModel() throws Exception {
        Kit actual = dozerService.mapTo(ferrariDTO, Kit.class);

        assertEquals(actual.getDescription(), ferrariDTO.getDescription());
        assertEquals(actual.getAgeLimit(), ferrariDTO.getAgeLimit());
        assertEquals(actual.getPrice(), ferrariDTO.getPrice());
    }

    @Test
    public void testMapToModelDTO() throws Exception {
        KitDTO actual = dozerService.mapTo(ferrari, KitDTO.class);

        assertEquals(actual.getDescription(), ferrari.getDescription());
        assertEquals(actual.getAgeLimit(), ferrari.getAgeLimit());
        assertEquals(actual.getPrice(), ferrari.getPrice());
    }

    @Test
    public void testMapToLegoSet() throws Exception {
        SetOfKits actual = dozerService.mapTo(sportCarsDTO, SetOfKits.class);

        assertEquals(actual.getDescription(), sportCarsDTO.getDescription());
        assertEquals(actual.getPrice(), sportCarsDTO.getPrice());
    }

    @Test
    public void testMapToLegoSetDTO() throws Exception {
        SetOfKitsDTO actual = dozerService.mapTo(sportCars, SetOfKitsDTO.class);

        assertEquals(actual.getDescription(), sportCars.getDescription());
        assertEquals(actual.getPrice(), sportCars.getPrice());
    }

    @Test
    public void testMapToPieceType() throws Exception {
        Shape actual = dozerService.mapTo(wheelDTO, Shape.class);

        assertEquals(actual.getName(), wheelDTO.getName());
    }

    @Test
    public void testMapToPieceTypeDTO() throws Exception {
        ShapeDTO actual = dozerService.mapTo(wheel, ShapeDTO.class);

        assertEquals(actual.getName(), wheel.getName());
    }

    @Test
    public void testMapToPiece() throws Exception {
        Brick actual = dozerService.mapTo(leftFrontWheelDTO, Brick.class);

        assertEquals(actual.getRed(), leftFrontWheel.getRed());
        assertEquals(actual.getGreen(), leftFrontWheel.getGreen());
        assertEquals(actual.getBlue(), leftFrontWheel.getRed());
    }

    @Test
    public void testMapToPieceDTO() throws Exception {
        BrickDTO actual = dozerService.mapTo(leftFrontWheel, BrickDTO.class);

        assertEquals(actual.getRed(), leftFrontWheelDTO.getRed());
        assertEquals(actual.getGreen(), leftFrontWheelDTO.getGreen());
        assertEquals(actual.getBlue(), leftFrontWheelDTO.getRed());
    }

}
