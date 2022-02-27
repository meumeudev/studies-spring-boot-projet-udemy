package sn.membre.br.membre.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "membre")
public class MembreEntity {
    @Id
    private Long id;
    //nom
    private String nom;
    // prenom
    private String prenom;
    //adresse
    private String adresse;
    //Date de Naissance;
    private Date datedenaissance;
    //lieu de naissance
    private String lieudenaissance;

}
