package com.fenohasinaKantoMinosoa.transport;

import java.sql.Connection;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = ConnexionBD.getConnection()) {

            System.out.println("Connexion réussie !");

        } catch (Exception e) {

            System.out.println("Erreur de connexion");
            e.printStackTrace();

        }

    }
}
