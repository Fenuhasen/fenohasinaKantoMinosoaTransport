package com.fenohasinaKantoMinosoa.transport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter

public class Voyage {
    private int id;
    private Centre centreDepart;
    private Centre centreArrivee;
    private int distance;
    private LocalDate dateDepart;
    private Classe classe;
    private int prixBillet;
    private TaxiBrousse taxiBrousse;
    private Chauffeur chauffeur;
    private List<Reservation> listeReservation;
    private Status status;
    private Admin admin;

    public String confirmerArrivee() {
        this.status = Status.ARRIVEE;
        return "Arrivée confirmée";
    }

    public int nombrePassagers() {
        int total = 0;
        for (Reservation reservation : listeReservation) {
            total += reservation.getNombrePassager();
        }
        return total;
    }

    public int nombrePlaceLibre() {
        return this.getTaxiBrousse().getPlacesMax() - nombrePassagers();
    }

    public int dureeEstimee() {
        return this.taxiBrousse.getVitesseMoyenne() / this.distance;
    }

    public Voyage(int id, Centre centreDepart, Centre centreArrivee, int distance, LocalDate dateDepart, Classe classe,
            int prixBillet,
            TaxiBrousse taxiBrousse, Chauffeur chauffeur, Admin admin) {
        this.id = id;
        this.centreDepart = centreDepart;
        this.centreArrivee = centreArrivee;
        this.distance = distance;
        this.dateDepart = dateDepart;
        this.classe = classe;
        this.prixBillet = prixBillet;
        this.taxiBrousse = taxiBrousse;
        this.chauffeur = chauffeur;
        this.status = Status.EN_PREPARATION;
        this.listeReservation = new ArrayList<>();
        this.admin = admin;
        admin.getAgence().getVoyages().add(this);


        String sql = "INSERT INTO voyage(id, centre_depart_id, centre_arrivee_id, distance, date_depart, classe, prix_billet, taxibrousse_id, chauffeur_id, status) VALUES (?, ?, ?, ?, ?, ?,?,?,?,?)";

        try (
                Connection conn = ConnexionBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            stmt.setInt(2, centreDepart.getId());
            stmt.setInt(3, centreArrivee.getId());
            stmt.setInt(4, distance);
            stmt.setDate(5, java.sql.Date.valueOf(dateDepart));
            stmt.setString(6, classe.name()); // si Classe est un enum
            stmt.setInt(7, prixBillet);
            stmt.setInt(8, taxiBrousse.getId());
            stmt.setInt(9, chauffeur.getId());
            stmt.setString(10, status.name()); // si Status est un enum
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}