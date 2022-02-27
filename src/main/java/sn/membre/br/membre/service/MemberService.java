package sn.membre.br.membre.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.membre.br.membre.entity.MembreEntity;
import sn.membre.br.membre.exception.MembreNotFoundException;
import sn.membre.br.membre.exception.MembreUpdateException;
import sn.membre.br.membre.model.Membre;
import sn.membre.br.membre.repository.MembreRepositpry;
import sn.membre.br.membre.util.membreMapper.MembreMaper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final  MembreRepositpry membreRepositpry;

    public Membre savemembre(Membre membre){

        //avant de creer un nouveau membre
        //check si l'instance membre est valide
        //une fois c valid tranformer membre en entity
        MembreEntity membreEntity= MembreMaper.INSTANCE.mapModeltoEntity(membre);
        //creer le movie
        membreRepositpry.save(membreEntity);
        //si la creation est bonne retourner l'instance creer

        return membre;
    }

    public Membre getMembreById(@NonNull  Long id) {
        //check si id n'est pas null
        // sinon chercher le id dans la base
        //si trouver retourner la valeur
        //si l'on ne trouve pas on retourne execption
        Optional<MembreEntity> membreEntity=membreRepositpry.findById(id);
        if(!membreEntity.isPresent()){
            throw new MembreNotFoundException("Le membre n'existe pas");
        }
        Membre membre=MembreMaper.INSTANCE.mapEntitytoModel(membreEntity.get());
        return membre;


    }

    public Membre upate(Long id,Membre membre) {
        //check id not null and membre not null
        //check si les l'id du membre et celui qu'on veut changer son different
        //si oui exception 400
        //sinon regarde s'il existe dans la base donnee
        //si non lever une exception not found 404
        //sinon prendre ce membre et changer le avec  le membre en parametrre
        //si c fini alors enregistre dans la base
        //et retourne ce membre
        if(id!=membre.getId()){
            throw  new MembreUpdateException("Erreur parametre id");
        }
        Optional<MembreEntity> entity=membreRepositpry.findById(id);

        if(!entity.isPresent()){
             throw new MembreNotFoundException("Le membre n'existe pas dans la base");
        }
        MembreEntity membreEntity=entity.get();
        membreEntity.setAdresse(membre.getAdresse());
        membreEntity.setLieudenaissance(membre.getLieudenaissance());
        membreEntity.setPrenom(membre.getPrenom());
        membreEntity.setNom(membre.getNom());
        membreEntity.setDatedenaissance(membre.getDatedenaissance());
        System.out.println("enregistrement bien fait");
        membreRepositpry.save(membreEntity);
        System.out.println("enregiste");

        return membre;
    }

    public Membre delete(@NonNull  Long id) {
        //check si id est null
        //si non ,check s'il existe dans la base de donnee
        //Au cas que ca existe on  doit le supprimer dans la base de donnee
        //si cela existe pas leve une Exdeption
        Optional<MembreEntity> membreEntity=membreRepositpry.findById(id);
        if(!membreEntity.isPresent()){
            throw new MembreNotFoundException("le membre n'existe dans la base de donnee");
        }
        membreRepositpry.delete(membreEntity.get());
        Membre membre=MembreMaper.INSTANCE.mapEntitytoModel(membreEntity.get());
        return membre;

    }

    public List<Membre> getAllMember() {
            List<Membre> membres=new ArrayList<>();
            for(MembreEntity membreEntity:membreRepositpry.findAll()){
                membres.add(MembreMaper.INSTANCE.mapEntitytoModel(membreEntity));
            }
            return membres;
    }
}
