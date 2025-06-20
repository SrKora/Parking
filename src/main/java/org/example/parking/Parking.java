package org.example.parking;

import java.util.ArrayList;

public class Parking {
    public static ArrayList<Coche> parking = new ArrayList<>();

    public static boolean comprobarMatricula(String matricula) {
        return parking.stream().anyMatch(x -> x.getMatricula().equals(matricula));
    }

    public static Coche buscarCoche(String matricula) {
        return parking.stream().filter(x -> x.getMatricula().equals(matricula)).findFirst().get();
    }
}