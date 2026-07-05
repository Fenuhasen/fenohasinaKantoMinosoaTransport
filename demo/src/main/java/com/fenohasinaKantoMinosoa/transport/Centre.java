package com.fenohasinaKantoMinosoa.transport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class Centre {
    private int id;
    private String ville;
    private Admin admin;


    public Centre(int id, String ville, Admin admin) {
        this.id = id;
        this.ville = ville;
        this.admin = admin;

        admin.getAgence().getCentres().add(this);

        String sql = "INSERT INTO centre(id, ville) VALUES (?, ?)";

        try (
                Connection conn = ConnexionBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            stmt.setString(2, ville);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}