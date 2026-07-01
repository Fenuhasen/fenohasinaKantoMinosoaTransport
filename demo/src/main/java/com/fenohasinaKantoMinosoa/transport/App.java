package com.fenohasinaKantoMinosoa.transport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choix = 0;

        while (choix != 3) {

            System.out.println("\n===== MENU =====");
            System.out.println("1 - Lister les voyages");
            System.out.println("2 - Lister les taxi-brousses");
            System.out.println("3 - Quitter");
            System.out.print("Votre choix : ");

            choix = scanner.nextInt();

            switch (choix) {

                case 1:
                    afficherVoyages();
                    break;

                case 2:
                    afficherTaxiBrousses();
                    break;

                case 3:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide.");
            }
        }

        scanner.close();
    }

    private static void afficherTaxiBrousses() {

        String sql = "SELECT * FROM taxibrousse";

        try (
                Connection conn = ConnexionBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            System.out.println("\n===== LISTE DES TAXI-BROUSSES =====");

            while (rs.next()) {

                System.out.println("--------------------------------");

                System.out.println("ID : " + rs.getInt("id"));
                System.out.println("Matricule : " + rs.getString("matricule"));
                System.out.println("Places max : " + rs.getInt("place_max"));
                System.out.println("Dépense/km : " + rs.getInt("depense_kilometre"));
                System.out.println("Vitesse moyenne : " + rs.getInt("vitesse_moyenne"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void afficherVoyages() {

        String sql = "SELECT * FROM voyage";

        try (
                Connection conn = ConnexionBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            System.out.println("\n===== LISTE DES VOYAGES =====");

            while (rs.next()) {

                System.out.println("--------------------------------");

                System.out.println("ID : " + rs.getInt("id"));
                System.out.println("Centre départ : " + rs.getString("centre_depart"));
                System.out.println("Centre arrivée : " + rs.getString("centre_arrivee"));
                System.out.println("Distance : " + rs.getInt("distance"));
                System.out.println("Date départ : " + rs.getDate("date_depart"));
                System.out.println("Classe : " + rs.getString("classe"));
                System.out.println("Prix billet : " + rs.getInt("prix_billet"));
                System.out.println("Taxi : " + rs.getInt("taxibrousse_id"));
                System.out.println("Chauffeur : " + rs.getInt("chauffeur_id"));
                System.out.println("Status : " + rs.getString("status"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}