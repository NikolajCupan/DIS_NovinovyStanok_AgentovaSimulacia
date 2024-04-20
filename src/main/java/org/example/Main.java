package org.example;

import org.example.simulation.MySimulation;

public class Main
{
    public static void main(String[] args)
    {
        MySimulation s = new MySimulation();
        s.onSimulationWillStart(e -> System.out.println("Simulating..."));
        s.simulate(1);
    }
}