package com.fenohasinaKantoMinosoa.transport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        
        var agence = new Agence();
        var admin = new Admin(0, "Test", "Admin", agence);
        var tana = new Centre(1, "Antananarivo", admin);
        var fianara = new Centre(2, "Fianarantsoa", admin);
        var bob = new Chauffeur(1, "RAKOTO", "Bob", 500000 , admin);
        var taxiBrousse = new TaxiBrousse(1, "1234 TAN", 32, 5000, 45, admin);
        var charlie = new Client(1, "TestClient", "Charlie", "0340000000", "Antananarivo", admin);
        var tanaFianara = new Voyage(1, tana, fianara, 500, LocalDate.of(2025,07,06), Classe.STANDARD, 30000, taxiBrousse, bob, admin);

        charlie.reserver(tanaFianara, 2, null);
        charlie.reserver(tanaFianara, 4, null);
        charlie.payerReservation(1);
        System.out.println(charlie.listerReservation().get(0).getStatut()); //Paye
        System.out.println(tanaFianara.nombrePlaceLibre()); //26

         

        Scanner scanner = new Scanner(System.in);
        int choix = 0;

        while (choix != 4) {

            System.out.println("\n===== MENU =====");
            System.out.println("1 - Lister les voyages");
            System.out.println("2 - Lister les taxi-brousses");
            System.out.println("3 - Créer un voyage");
            System.out.println("4 - Quitter");
            System.out.print("Votre choix : ");
            System.out.println(admin.getAgence().getVoyages());

            choix = scanner.nextInt();

            switch (choix) {

                case 1:
                    afficherVoyages();
                    break;

                case 2:
                    afficherTaxiBrousses();
                    break;

                case 3:
                    creerVoyage(admin);
                    break;

                case 4:
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
                System.out.println("Centre départ : " + rs.getInt("centre_depart_id"));
                System.out.println("Centre arrivée : " + rs.getInt("centre_arrivee_id"));
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

    private static void creerVoyage(Admin admin){

        Scanner scanner = new Scanner(System.in);
        int id = admin.getAgence().getVoyages().size()+1;
        System.out.println("Id centre depart :");
        int centreDepartId = scanner.nextInt();
        System.out.println("Id centre arrivee :");
        int centreArriveeId = scanner.nextInt();
        System.out.println("Distance :");
        int distance = scanner.nextInt();
        LocalDate date = LocalDate.now();
        Classe classe = Classe.STANDARD;
        System.out.println("Prix billet :");
        int prixBillet = scanner.nextInt();
        System.out.println("Id taxi brousse :");
        int taxibrousseId = scanner.nextInt();
        System.out.println("Id chauffeur :");
        int chauffeurId = scanner.nextInt();

        System.out.println(admin.getAgence().getTaxiBrousses());
        System.out.println(admin.getAgence().getChauffeurs());
        System.out.println(admin.getAgence().getCentres());
        var voyage = new Voyage(id, admin.getAgence().getCentres().get(centreDepartId - 1), admin.getAgence().getCentres().get(centreArriveeId - 1), distance, date, classe, prixBillet, admin.getAgence().getTaxiBrousses().get(taxibrousseId - 1), admin.getAgence().getChauffeurs().get(chauffeurId - 1), admin);

        scanner.close();
        ;
    }
}