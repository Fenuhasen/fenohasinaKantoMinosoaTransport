package com.fenohasinaKantoMinosoa.transport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class TaxiBrousse {
    private int id;
    private String matricule;
    private int placesMax;
    private int depenseParKilometre;
    private int vitesseMoyenne;
    private Admin admin;
    //methodes coming soon


    public TaxiBrousse(int id, String matricule, int placesMax, int depenseParKilometre, int vitesseMoyenne, Admin admin) {
        this.id = id;
        this.matricule = matricule;
        this.placesMax = placesMax;
        this.depenseParKilometre = depenseParKilometre;
        this.vitesseMoyenne = vitesseMoyenne;
        this.admin = admin;

        admin.getAgence().getTaxiBrousses().add(this);


        String sql = "INSERT INTO Taxibrousse(id, matricule, place_max, depense_kilometre, vitesse_moyenne) VALUES (?, ?, ?, ?, ?)";

        try (
            Connection conn = ConnexionBD.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            stmt.setInt(1, id);
            stmt.setString(2, matricule);
            stmt.setInt(3, placesMax);
            stmt.setInt(4, depenseParKilometre);
            stmt.setInt(5, vitesseMoyenne);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}