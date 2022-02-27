package sn.membre.br.membre.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@RequiredArgsConstructor
public class Membre  {
    //identificatin
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

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
