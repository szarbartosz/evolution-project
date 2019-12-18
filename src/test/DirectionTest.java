import org.junit.Assert;
        import org.junit.Test;
import features.Direction;
import features.Vector2D;

public class DirectionTest {
    @Test
    public void toUnitVectorTest(){
        Assert.assertEquals(new Vector2D(0,1),Direction.N.toUnitVector());
        Assert.assertEquals(new Vector2D(1,1),Direction.NE.toUnitVector());
        Assert.assertEquals(new Vector2D(1,0),Direction.E.toUnitVector());
        Assert.assertEquals(new Vector2D(1,-1),Direction.SE.toUnitVector());
        Assert.assertEquals(new Vector2D(0,-1),Direction.S.toUnitVector());
        Assert.assertEquals(new Vector2D(-1,-1),Direction.SW.toUnitVector());
        Assert.assertEquals(new Vector2D(-1,0),Direction.W.toUnitVector());
        Assert.assertEquals(new Vector2D(-1,1),Direction.NW.toUnitVector());
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertTest(){
        String[] args = new String[]{"n", "ne", "e", "se", "s", "sw", "w", "nw"};
        Direction[] expectedParse = new Direction[]{Direction.N, Direction.NE, Direction.E, Direction.SE, Direction.S, Direction.SW, Direction.W, Direction.NW};
        Assert.assertArrayEquals(expectedParse, Direction.convert(args));
        String[] illegalArgs = new String[]{"n", "w", "epstein", "se", "didnt", "s", "kill", "w", "himself"};
        Direction.convert(illegalArgs);
    }
}
