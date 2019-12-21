package projectStructure;

import features.Vector2D;
import visualization.RenderPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Simulation {
    WorldMap map;
    private int numberOfDays;
    private int refreshTime;

    int day;
    int numberOfAnimals;
    int totalNumberOfAnimals;
    int maxNumberOfAnimals;
    private int bornAnimals;
    private int deadAnimals;

    public Simulation(){
        JSON mapDetails = new JSON();
        this.numberOfDays = mapDetails.days;
        this.numberOfAnimals = mapDetails.animals;
        this.refreshTime = mapDetails.refresh;
        this.day = 0;
        this.totalNumberOfAnimals = mapDetails.animals;
        this.maxNumberOfAnimals = mapDetails.animals;

        Random random = new Random();
        this.map = new WorldMap(new Vector2D(mapDetails.width - 1, mapDetails.height - 1), mapDetails.startEnergy, mapDetails.moveEnergy, mapDetails.plantEnergy, mapDetails.jungleRatio);
        for (int i = 0; i < mapDetails.animals; i++){
            map.place(new Animal(new Vector2D(random.nextInt(map.width), random.nextInt(map.height)), map));
        }
    }

    public void nextDay(){
        int deadAnimals = map.clearDeadAnimals();
        map.turnAllAnimals();
        map.moveAllAnimals();
        map.eatAll();
        int bornAnimals = map.procreateAll();
        map.generateGrass();
        day++;
        numberOfAnimals += bornAnimals - deadAnimals;
        totalNumberOfAnimals += bornAnimals;
        this.deadAnimals += deadAnimals;
        this.bornAnimals += bornAnimals;
        if (numberOfAnimals > maxNumberOfAnimals){
            maxNumberOfAnimals = numberOfAnimals;
        }
    }


    /*public void startSimulation(int numberOfDays){
        System.out.println("Day: " + day);
        System.out.println(map.drawMap());
        for (int i = 1; i <= numberOfDays; i++){
            this.nextDay();
            System.out.println("Day: " + day);
            System.out.println(map.drawMap());
        }
    }*/

    public void startSimulation() throws InterruptedException {
        System.out.println("Day: " + day);
        System.out.println(this.map.drawMap());
        JFrame frame = new JFrame();
        frame.setSize(1600,840);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RenderPanel panel = new RenderPanel(map, frame);
        frame.add(panel);
        frame.setVisible(true);
        frame.setLayout(new GridLayout());

        JPanel infoPanel = new JPanel();
        JLabel dayCount = new JLabel("Day: " + this.day);
        JLabel pigsCount = new JLabel("Number of animals: " + this.numberOfAnimals);
        JLabel totalPigsCount = new JLabel("Total number of animals: " + this.totalNumberOfAnimals);
        JLabel maxNumberOfPigs = new JLabel("Max number of animals: " + this.maxNumberOfAnimals);
        JLabel bornAnimals = new JLabel("Born animals: 0");
        JLabel deadAnimals = new JLabel( "Dead animals: 0");

        infoPanel.setSize((int) (0.5 * frame.getWidth()),500);
        infoPanel.add(dayCount);
        infoPanel.add(pigsCount);
        infoPanel.add(totalPigsCount);
        infoPanel.add(maxNumberOfPigs);
        infoPanel.add(bornAnimals);
        infoPanel.add(deadAnimals);
        infoPanel.setLayout(new GridLayout(20, 1));
        infoPanel.setVisible(true);

        frame.add(infoPanel);

        java.util.concurrent.TimeUnit.SECONDS.sleep(1);


        for (int i = 1; i <= numberOfDays; i++){
            this.nextDay();
            System.out.println("Day: " + day);
            System.out.println(map.drawMap());
            panel.repaint();
            System.out.println(this.numberOfAnimals);
            pigsCount.setText("Number of animals: " + this.numberOfAnimals);
            dayCount.setText("Day: " + this.day);
            totalPigsCount.setText("Total number of animals: " + this.totalNumberOfAnimals);
            maxNumberOfPigs.setText("Max number of animals: " + this.maxNumberOfAnimals);
            deadAnimals.setText("Dead animals: " + this.deadAnimals);
            bornAnimals.setText("Born animals: " + this.bornAnimals);
            java.util.concurrent.TimeUnit.MILLISECONDS.sleep(this.refreshTime);
        }
    }


}
