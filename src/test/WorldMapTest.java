import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class WorldMapTest {

    @Test
    public void objectAtPlaceTest(){
        WorldMap map = new WorldMap(new Vector2D(10,10));
        Animal fox = new Animal(new Vector2D(2,2), map);
        map.place(fox);
        Assert.assertEquals(fox, map.objectAt(new Vector2D(2,2)));
        for (int i = 0; i < 10; i ++){
            for (int j = 0; j < 10; j++){
                Assert.assertTrue(map.place(new Animal(new Vector2D(i,j),map)));
            }
        }
        Assert.assertTrue(map.objectAt(new Vector2D(2,2)) instanceof List);
        Assert.assertTrue(map.objectAt(new Vector2D(3,1)) instanceof Animal);
        Assert.assertNull(map.objectAt(new Vector2D(-55, -55)));
    }

    @Test
    public void isOccupiedTest(){
        WorldMap map = new WorldMap(new Vector2D(10,10));
        for (int i = 0; i < 10; i ++){
            for (int j = 0; j < 10; j++){
                Assert.assertFalse(map.isOccupied(new Vector2D(i,j)));
            }
        }
        Animal snail = new Animal(new Vector2D(2,2),map);
        map.place(snail);
        for (int i = 0; i < 10; i ++){
            for (int j = 0; j < 10; j++){
                if (i == 2 && j == 2){
                    Assert.assertTrue(map.isOccupied(new Vector2D(i,j)));
                }else{
                    Assert.assertFalse(map.isOccupied(new Vector2D(i,j)));
                }
            }
        }
    }

    @Test
    public void betterPositionTest(){
        WorldMap map = new WorldMap(new Vector2D(6,6));
        Assert.assertEquals(new Vector2D(0, 1),map.betterPosition(new Vector2D(7,1)));
        Assert.assertEquals(new Vector2D(6,6), map.betterPosition(new Vector2D(6,6)));
        Assert.assertEquals(new Vector2D(0, 0), map.betterPosition(new Vector2D(7,7)));
        Assert.assertEquals(new Vector2D(1, 0), map.betterPosition(new Vector2D(8, 0)));
    }

    @Test
    public void betterPositionPlaceTest(){
        WorldMap map = new WorldMap(new Vector2D(6,6));
        Animal fox = new Animal(new Vector2D(7, 7),map);
        map.place(fox);
        Assert.assertEquals(fox, map.objectAt(new Vector2D(0,0)));
    }
}