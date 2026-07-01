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

        var agence = new Agence();
        var admin = new Admin(0, "Test", "Admin", agence);
        var tana = new Centre(1, "Antananarivo");
        var fianara = new Centre(2, "Fianarantsoa");
        var bob = new Chauffeur(1, "RAKOTO", "Bob", 500000);
        var taxiBrousse = new TaxiBrousse(1, "1234 TAN", 32, 5000, 45);
        var charlie = new Client(1, "TestClient", "Charlie", "0340000000", "Antananarivo", admin);
        var tanaFianara = new Voyage(1, tana, fianara, 500, LocalDate.of(2025,07,06), Classe.STANDARD, 30000, taxiBrousse, bob);

        charlie.reserver(tanaFianara, 2, null);
        charlie.reserver(tanaFianara, 4, null);
        charlie.payerReservation(1);
        System.out.println(charlie.listerReservation().get(0).getStatut()); //Paye
        System.out.println(tanaFianara.nombrePlaceLibre()); //26

         
    }
}
