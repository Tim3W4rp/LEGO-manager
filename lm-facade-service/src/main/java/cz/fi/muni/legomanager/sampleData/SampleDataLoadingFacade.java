package cz.fi.muni.legomanager.sampleData;

import cz.fi.muni.legomanager.entity.Brick;
import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.entity.SetOfKits;
import cz.fi.muni.legomanager.entity.Shape;
import cz.fi.muni.legomanager.services.BrickService;
import cz.fi.muni.legomanager.services.CategoryService;
import cz.fi.muni.legomanager.services.KitBrickService;
import cz.fi.muni.legomanager.services.KitService;
import cz.fi.muni.legomanager.services.SetOfKitsService;
import cz.fi.muni.legomanager.services.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
@Transactional
public class SampleDataLoadingFacade {

    @Autowired
    CategoryService categoryService;

    @Autowired
    SetOfKitsService setService;

    @Autowired
    KitService kitService;

    @Autowired
    BrickService brickService;

    @Autowired
    ShapeService shapeService;

    @Autowired
    KitBrickService kitBrickService;

    public void loadData() {
        // Categories
        Category batman = category("Batman", "Build something Batman!");
        Category marvel = category("Marvel", "Marvelous sets!");
        Category starWars = category("StarWars", "New Adventures!");
        Category minecraft = category("Minecraft", "Minecraft sets!");
        Category mindstorms = category("Mindstorms", "Exciting ideas!");

        // Sets
        SetOfKits darkKnight = setOfKits("Dark Knight", BigDecimal.valueOf(249));
        SetOfKits darkKnightRises = setOfKits("Dark Knight Rises", BigDecimal.valueOf(399));

        SetOfKits thor = setOfKits("Thor", BigDecimal.valueOf(499));
        SetOfKits hulk = setOfKits("Hulk", BigDecimal.valueOf(199));

        SetOfKits sw7 = setOfKits("Star Wars: Episode VII - The Force Awakens", BigDecimal.valueOf(299));
        SetOfKits sw8 = setOfKits("Star Wars: Episode VIII - The Last Jedi", BigDecimal.valueOf(399));
        SetOfKits swClassic = setOfKits("Classic Star Wars sets", BigDecimal.valueOf(799));

        SetOfKits mcDesert = setOfKits("Minecraft desert set", BigDecimal.valueOf(299));
        SetOfKits mcOcean = setOfKits("Minecraft ocean set", BigDecimal.valueOf(399));

        SetOfKits EV3 = setOfKits("Ultimate lego programmable robot", BigDecimal.valueOf(299));

        // Kits
        Kit jokerWehicle = kit("The Joker Notourious Lowrider", 99, 12, batman, darkKnight);
        Kit batCave = kit("Batcave Break-in", 199, 12, batman, darkKnight);

        Kit ultimateBatMobile = kit("The Ultimate Batmobile", 199, 10, batman, darkKnightRises);
        Kit riddler = kit("The Riddler™ Riddle Racer", 249, 12, batman, darkKnightRises);

        Kit battleOfAtlantis = kit("Battle of atlantis", 199, 7, marvel, thor);
        Kit battleOfAsgarad = kit("The Ultimate Battle for Asgard", 249, 7, marvel, thor);

        Kit arenaClash = kit("Arena clash", 199, 10, marvel, hulk);

        Kit starDestroyer = kit("First Order Star Destroyer", 159, 7, starWars, sw7);
        Kit scoutWalker = kit("First Order Heavy Scout Walker", 49, 7, starWars, sw7);
        Kit transportPod = kit("Resistance Transport Pod", 29, 7, starWars, sw7);

        Kit tieFighter = kit("Kylo Ren’s TIE Fighter", 79, 8, starWars, sw8);
        Kit assaultWalker = kit("First Order Heavy Assault Walker", 149, 9, starWars, sw8);
        Kit bb8 = kit("BB-8", 99, 10, starWars, sw8);
        Kit bomber = kit("Resistance Bomber", 109, 9, starWars, sw8);

        Kit deathStar = kit("Death Star™", 499, 14, starWars, swClassic);
        Kit snowSpeeder = kit("Snow Speeder", 199, 14, starWars, swClassic);
        Kit slave1 = kit("Slave I™", 199, 14, starWars, swClassic);

        Kit mountainCave = kit("The Mountain Cave", 249, 12, minecraft, mcDesert);
        Kit craftingBox = kit("The Crafting Box 2.0", 59, 8, minecraft, mcDesert);

        Kit oceanMonument = kit("The Ocean Monument", 119, 8, minecraft, mcOcean);

        Kit ultimateRobotFull = kit("EV3 Full pack", 349, 10, mindstorms, EV3);
        Kit engine = kit("EV3 engine", 99, 10, mindstorms, EV3);
        Kit sensors = kit("EV3 sensors", 79, 10, mindstorms, EV3);

        // Shapes
        Shape person = shape("Figure");
        Shape brick2x2 = shape("Brick 2x2");
        Shape brick2x4 = shape("Brick 2x4");
        Shape wheel = shape("Wheel");

        // Bricks
        Brick joker = brick(150, 0, 0, person);
        Brick batmanPerson = brick(0, 0, 0, person);
        Brick lukeSkywalker = brick(209, 157, 108, person);
        Brick thorPerson = brick(251, 255, 33, person);
        Brick hulkPerson = brick(0, 155, 25, person);

        Brick redBrick = brick(255, 0, 0, brick2x2);
        Brick greenBrick = brick(0, 255, 0, brick2x2);
        Brick bluBrick = brick(0, 0, 255, brick2x2);

        Brick gray2x2 = brick(100, 100, 100, brick2x2);
        Brick black2x2 = brick(0, 0, 0, brick2x2);

        Brick gray2x4 = brick(100, 100, 100, brick2x4);
        Brick black2x4 = brick(0, 0, 0, brick2x4);

        Brick blackWheel = brick(0, 0, 0, wheel);

        // add bricks to kits
        brickToKit(jokerWehicle, blackWheel, 10);
        brickToKit(jokerWehicle, joker, 5);
        brickToKit(jokerWehicle, black2x2, 20);
        brickToKit(jokerWehicle, gray2x2, 30);
        brickToKit(jokerWehicle, black2x4, 50);
        brickToKit(jokerWehicle, gray2x4, 100);

        brickToKit(batCave, black2x2, 12);
        brickToKit(batCave, joker, 1);
        brickToKit(batCave, batmanPerson, 1);
        brickToKit(batCave, redBrick, 12);

        brickToKit(ultimateBatMobile, blackWheel, 4);
        brickToKit(ultimateBatMobile, black2x4, 3);
        brickToKit(ultimateBatMobile, black2x4, 3);
        brickToKit(ultimateBatMobile, bluBrick, 1);
        brickToKit(ultimateBatMobile, gray2x2, 10);

        brickToKit(riddler, black2x2, 4);
        brickToKit(riddler, blackWheel, 1);
        brickToKit(riddler, black2x4, 5);
        brickToKit(riddler, gray2x2, 6);
        brickToKit(riddler, gray2x4, 4);

        brickToKit(battleOfAtlantis, thorPerson, 1);
        brickToKit(battleOfAtlantis, black2x2, 4);
        brickToKit(battleOfAtlantis, gray2x4, 10);

        brickToKit(battleOfAsgarad, thorPerson, 1);
        brickToKit(battleOfAtlantis, gray2x2, 4);
        brickToKit(battleOfAtlantis, gray2x4, 10);

        brickToKit(arenaClash, hulkPerson, 1);
        brickToKit(arenaClash, greenBrick, 2);
        brickToKit(arenaClash, black2x4, 10);
        brickToKit(arenaClash, bluBrick, 5);

        brickToKit(starDestroyer, lukeSkywalker, 1);
        brickToKit(starDestroyer, greenBrick, 2);
        brickToKit(starDestroyer, black2x4, 10);
        brickToKit(starDestroyer, bluBrick, 5);

        brickToKit(scoutWalker, lukeSkywalker, 1);
        brickToKit(scoutWalker, greenBrick, 2);
        brickToKit(scoutWalker, black2x4, 10);
        brickToKit(scoutWalker, bluBrick, 5);

        brickToKit(transportPod, lukeSkywalker, 1);
        brickToKit(transportPod, greenBrick, 2);
        brickToKit(transportPod, black2x4, 10);
        brickToKit(transportPod, bluBrick, 5);

        brickToKit(tieFighter, lukeSkywalker, 1);
        brickToKit(tieFighter, greenBrick, 2);
        brickToKit(tieFighter, black2x4, 10);
        brickToKit(tieFighter, bluBrick, 5);

        brickToKit(assaultWalker, lukeSkywalker, 1);
        brickToKit(assaultWalker, greenBrick, 2);
        brickToKit(assaultWalker, black2x4, 10);
        brickToKit(assaultWalker, bluBrick, 5);

        brickToKit(bb8, lukeSkywalker, 1);
        brickToKit(bb8, greenBrick, 2);
        brickToKit(bb8, black2x4, 10);
        brickToKit(bb8, bluBrick, 5);

        brickToKit(bomber, lukeSkywalker, 1);
        brickToKit(bomber, greenBrick, 2);
        brickToKit(bomber, black2x4, 10);
        brickToKit(bomber, bluBrick, 5);

        brickToKit(deathStar, lukeSkywalker, 1);
        brickToKit(deathStar, greenBrick, 2);
        brickToKit(deathStar, black2x4, 10);
        brickToKit(deathStar, bluBrick, 5);

        brickToKit(snowSpeeder, lukeSkywalker, 1);
        brickToKit(snowSpeeder, greenBrick, 2);
        brickToKit(snowSpeeder, black2x4, 10);
        brickToKit(snowSpeeder, bluBrick, 5);

        brickToKit(slave1, lukeSkywalker, 1);
        brickToKit(slave1, greenBrick, 2);
        brickToKit(slave1, black2x4, 10);
        brickToKit(slave1, bluBrick, 5);

        brickToKit(mountainCave, black2x2, 1000);
        brickToKit(mountainCave, gray2x2, 1000);

        brickToKit(craftingBox, black2x2, 1000);
        brickToKit(craftingBox, gray2x2, 1000);
        brickToKit(craftingBox, bluBrick, 1000);
        brickToKit(craftingBox, redBrick, 1000);

        brickToKit(oceanMonument, bluBrick, 1000);

        brickToKit(ultimateRobotFull, black2x2, 10);
        brickToKit(ultimateRobotFull, gray2x2, 10);
        brickToKit(ultimateRobotFull, bluBrick, 10);
        brickToKit(ultimateRobotFull, redBrick, 10);

        brickToKit(engine, gray2x4, 1);

        brickToKit(sensors, bluBrick, 4);

    }

    private Category category(String name, String description) {
        Category c = new Category(name, description);
        categoryService.create(c);
        return c;
    }

    private SetOfKits setOfKits(String description, BigDecimal price) {
        SetOfKits s = new SetOfKits();
        s.setDescription(description);
        s.setPrice(price);
        setService.create(s);
        return s;
    }

    private Kit kit(String description, Integer price, Integer ageLimit, Category category, SetOfKits set) {
        Kit k = new Kit();
        k.setDescription(description);
        k.setPrice(price);
        k.setAgeLimit(ageLimit);
        k.setCategory(category);
        k.setSetOfKits(set);
        kitService.createKit(k);
        return k;
    }

    private Shape shape(String name) {
        Shape s = new Shape();
        s.setName(name);
        shapeService.create(s);
        return s;
    }

    private Brick brick(int red, int green, int blue, Shape shape) {
        Brick b = new Brick();
        b.setRed(red);
        b.setGreen(green);
        b.setBlue(blue);
        b.setShape(shape);
        brickService.create(b);
        return b;
    }

    private void brickToKit(Kit kit, Brick brick, int amount) {
        kitBrickService.createKitBrick(kit, brick);
        kitBrickService.addBrickToKit(kit, brick);
        kitBrickService.setBrickCount(kit, brick, amount);
    }
}
