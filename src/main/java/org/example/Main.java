package org.example;

import org.example.simulation.MySimulation;
import org.example.vlastne.GeneratorNasad;
import org.example.vlastne.Konstanty;

public class Main
{
    public static void main(String[] args)
    {
        GeneratorNasad.inicializujGeneratorNasad(420, true);

        MySimulation s = new MySimulation(Konstanty.TRVANIE_CAS_SEKUNDY);
        s.onSimulationWillStart(e -> System.out.println("Simulacia spustena"));
        s.simulate(100000, Konstanty.TRVANIE_CAS_SEKUNDY);
    }
}