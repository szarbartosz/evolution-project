package projectStructure;

import features.Vector2D;

public class World {
    public static void main(String[] args) throws InterruptedException {

        WorldMap map = new WorldMap(new Vector2D(16,16));
        Animal fox = new Animal(new Vector2D(1,2), new Integer[]{0,0,0,0,0,0,0,0,1,1,2,2,2,2,2,2,3,3,4,4,4,4,4,4,5,5,6,6,7,7,7,7}, map);
        Animal sheep = new Animal(new Vector2D(2,2), new Integer[]{0,0,0,0,3,3,3,0,1,1,4,4,4,4,4,2,3,3,4,4,4,4,4,4,5,5,6,6,0,0,1,1}, map);
        map.place(fox);
        map.place(sheep);
        map.place(new Animal(new Vector2D(2,1),map));
        map.place(new Animal(new Vector2D(4,2),map));
        map.place(new Animal(new Vector2D(2, 0),map));
        map.place(new Animal(new Vector2D(0,5),map));
        map.place(new Animal(new Vector2D(3,1),map));
        map.place(new Animal(new Vector2D(6,3),map));
        map.place(new Animal(new Vector2D(5, 1),map));
        map.place(new Animal(new Vector2D(2,4),map));
        Simulation sim = new Simulation(map);
        sim.startSimulation(1000);
        /*System.out.println(map.drawMap());
        JFrame frame = new JFrame();
        frame.setSize(1000,1000);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RenderPanel panel = new RenderPanel(map, frame);
        frame.add(panel);
        frame.setVisible(true);
        java.util.concurrent.TimeUnit.SECONDS.sleep(1);
        map.turnAllAnimals();
        map.moveAllAnimals();
        panel.repaint();
        java.util.concurrent.TimeUnit.SECONDS.sleep(1);
        map.turnAllAnimals();
        map.moveAllAnimals();
        panel.repaint();
        System.out.println(map.drawMap());
        ((1 + 1) + 1) - 1
        System.out.println(map.drawMap());*/





    }
    /*public static void main(String[] args) {

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
    }*/

}
