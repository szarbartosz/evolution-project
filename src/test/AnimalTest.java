import org.junit.Assert;
import org.junit.Test;

public class AnimalTest {
    WorldMap map = new WorldMap(new Vector2D(100,100));
    Integer[] parrotGenotype = new Integer[]{0,0,0,0,0,0,0,0,0,0,1,1,2,2,2,3,3,3,4,4,4,4,4,5,5,6,6,6,6,7,7,7};
    private Animal duck = new Animal(map);
    private Animal ant = new Animal(map);
    private Animal snail = new Animal(new Vector2D(10,10), map);
    private Animal parrot = new Animal(new Vector2D(25,25), parrotGenotype, map);

    @Test
    public void constructorTest(){
        Assert.assertEquals(duck.getPosition(),new Vector2D(2,2));
        Assert.assertEquals(ant.getPosition(),new Vector2D(2,2));
        Assert.assertEquals(snail.getPosition(),new Vector2D(10,10));
        Assert.assertEquals(parrot.getPosition(),new Vector2D(25,25));
    }

    @Test
    public void turnTest(){
        duck.turn(2);
        ant.turn(3);
        snail.turn(5);
        parrot.turn(6);
        Assert.assertEquals(duck.getOrientation(), Direction.E);
        Assert.assertEquals(ant.getOrientation(), Direction.SE);
        Assert.assertEquals(snail.getOrientation(), Direction.SW);
        Assert.assertEquals(parrot.getOrientation(), Direction.W);
    }

    @Test
    public void moveTest(){
        duck.turn(2);
        ant.turn(3);
        snail.turn(5);
        parrot.turn(6);
        duck.move();
        ant.move();
        snail.move();
        parrot.move();
        Assert.assertEquals(duck.getPosition(),new Vector2D(3,2));
        Assert.assertEquals(ant.getPosition(),new Vector2D(3,1));
        Assert.assertEquals(snail.getPosition(),new Vector2D(9,9));
        Assert.assertEquals(parrot.getPosition(),new Vector2D(24,25));
    }
}