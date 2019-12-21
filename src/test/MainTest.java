import features.Direction;
import features.Vector2D;
import org.junit.Assert;
import org.junit.Test;
import projectStructure.Animal;
import projectStructure.Grass;
import projectStructure.WorldMap;

public class MainTest {
    @Test
    public void oneAnimalTest(){
        WorldMap map = new WorldMap(new Vector2D(10, 10));
        Animal fox = new Animal(new Vector2D(2,2), map);
        map.place(fox);
        Vector2D oldPosition = new Vector2D(2, 2);
        for (int i = 0; i < 5000; i++){
            fox.autoTurn();
            fox.move();
            boolean flag = false;
            for (Direction direction : Direction.values()){
                if ((map.objectAt(map.betterPosition(oldPosition.add(direction.toUnitVector())))) != null){
                    flag = true;
                }
            }
            Assert.assertTrue(flag);
            oldPosition = fox.getPosition();
        }
        System.out.println(fox.getEnergy());
    }

    @Test
    public void grassEaterTest(){
        WorldMap map = new WorldMap(new Vector2D(2, 2));
        map.putGrass(new Vector2D(1,1));
        Animal fox = new Animal(new Vector2D(1, 0),map);
        Animal sheep = new Animal(new Vector2D(0,1),map);
        sheep.turn(2);
        Animal goat  = new Animal(new Vector2D(1, 2), map);
        goat.turn(4);
        Animal dog = new Animal(new Vector2D(2,1),map);
        dog.turn(6);
        Assert.assertTrue(map.objectAt(new Vector2D(1,1)) instanceof Grass);
        map.place(fox);
        map.place(dog);
        map.place(sheep);
        map.place(goat);
        map.moveAllAnimals();

        map.eatAll();

        Assert.assertEquals(33.0, dog.getEnergy(), 0.01);
        Assert.assertEquals(33.0, sheep.getEnergy(), 0.01);
        Assert.assertEquals(33.0, goat.getEnergy(), 0.01);
        Assert.assertEquals(33.0, fox.getEnergy(), 0.01);
        map.moveAllAnimals();
        Assert.assertFalse(map.objectAt(new Vector2D(1,1)) instanceof Grass);

    }

    @Test
    public void grassEatingTest(){
        WorldMap map = new WorldMap(new Vector2D(2,2));
        map.place(new Animal(new Vector2D(1, 0), map));
        map.putGrass(new Vector2D(1,1));
        map.moveAllAnimals();
        map.eatAll();
        map.moveAllAnimals();
        Assert.assertNull(map.objectAt(new Vector2D(1,1)));
        Assert.assertEquals(0, map.grassSet.size());

    }
}
