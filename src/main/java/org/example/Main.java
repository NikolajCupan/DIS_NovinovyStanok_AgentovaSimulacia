package org.example;

import org.example.simulation.MySimulation;
import org.example.vlastne.GeneratorNasad;

public class Main
{
    public static void main(String[] args)
    {
        GeneratorNasad.inicializujGeneratorNasad(420, true);

        MySimulation s = new MySimulation();
        s.onSimulationWillStart(e -> System.out.println("Simulacia spustena"));
        s.simulate(1);
    }
}