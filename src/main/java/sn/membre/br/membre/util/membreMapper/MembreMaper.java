package sn.membre.br.membre.util.membreMapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sn.membre.br.membre.entity.MembreEntity;
import sn.membre.br.membre.model.Membre;

@Mapper
public interface MembreMaper {
    MembreMaper INSTANCE= Mappers.getMapper(MembreMaper.class);
    //transformer un membre en MembreEntity
    MembreEntity mapModeltoEntity(Membre membre);

    //Transformer un membreentity en membre

    Membre mapEntitytoModel(MembreEntity membreEntity);
}
