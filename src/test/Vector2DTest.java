import org.junit.Assert;
import org.junit.Test;

public class Vector2DTest {

    @Test
    public void equalsTest(){
        Vector2D v1 = new Vector2D(2,2);
        Vector2D v2 = new Vector2D(2,2);
        Vector2D v3 = new Vector2D(4,6);
        Assert.assertTrue(v1.equals(v1));
        Assert.assertTrue(v1.equals(v2));
        Assert.assertTrue(v2.equals(v1));
        Assert.assertFalse(v3.equals(v2));
        Assert.assertFalse(v2.equals(v3));
        Assert.assertFalse(v1.equals(null));
    }

    @Test
    public void toStringTest(){
        Vector2D vec = new Vector2D(4,4);
        Assert.assertEquals(vec.toString(),"(4,4)");
    }

    @Test
    public void precedesTest(){
        Vector2D v1 = new Vector2D(2,2);
        Vector2D v2 = new Vector2D(4,4);
        Vector2D v3 = new Vector2D(4,2);
        Assert.assertTrue(v1.precedes(v2));
        Assert.assertTrue(v3.precedes(v2));
        Assert.assertTrue(v2.precedes(v2));
        Assert.assertFalse(v2.precedes(v1));
    }

    @Test
    public void followsTest(){
        Vector2D v1 = new Vector2D(2,2);
        Vector2D v2 = new Vector2D(4,4);
        Vector2D v3 = new Vector2D(4,2);
        Assert.assertTrue(v2.follows(v1));
        Assert.assertTrue(v3.follows(v1));
        Assert.assertTrue(v2.follows(v2));
        Assert.assertFalse(v1.follows(v2));
    }

    @Test
    public void upperRightTest(){
        Vector2D v1 = new Vector2D(4,2);
        Vector2D v2 = new Vector2D(2,4);
        Vector2D v3 = new Vector2D(4,4);
        Assert.assertEquals(v1.upperRight(v2),v3);
    }

    @Test
    public void lowerLeftTest(){
        Vector2D v1 = new Vector2D(4,2);
        Vector2D v2 = new Vector2D(2,4);
        Vector2D v3 = new Vector2D(2,2);
        Assert.assertEquals(v1.lowerLeft(v2),v3);
    }

    @Test
    public void addTest(){
        Vector2D v1 = new Vector2D(4,2);
        Vector2D v2 = new Vector2D(2,4);
        Vector2D v3 = new Vector2D(6,6);
        Assert.assertEquals(v1.add(v2),v3);
        Assert.assertNotSame(v1.add(v2),"test");
    }

    @Test
    public void subtractTest(){
        Vector2D v1 = new Vector2D(4,2);
        Vector2D v2 = new Vector2D(2,4);
        Vector2D v3 = new Vector2D(2,-2);
        Assert.assertEquals(v1.subtract(v2),v3);
        Assert.assertNotSame(v1.add(v2),"test");
    }

    @Test
    public void oppositeTest(){
        Vector2D vec = new Vector2D(1,1);
        Vector2D opVec = new Vector2D(-1,-1);
        Assert.assertEquals(vec.opposite(),opVec);
    }

}