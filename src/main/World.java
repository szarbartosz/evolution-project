public class World {
    public static void main(String[] args) {

        WorldMap map = new WorldMap(new Vector2D(40,40));
        Animal fox = new Animal(new Vector2D(2,2), new Integer[]{0,0,0,0,0,0,0,0,1,1,2,2,2,2,2,2,3,3,4,4,4,4,4,4,5,5,6,6,7,7,7,7}, map);
        Animal sheep = new Animal(new Vector2D(2,2), new Integer[]{0,0,0,0,3,3,3,0,1,1,4,4,4,4,4,2,3,3,4,4,4,4,4,4,5,5,6,6,0,0,1,1}, map);
        map.place(fox);
        map.place(sheep);
        map.place(new Animal(map));
        map.place(new Animal(map));
        map.place(new Animal(map));
        map.place(new Animal(map));

        map.generateGrass();
        Simulation sim = new Simulation(map);
        sim.startSimulation(1000);






    }

}
