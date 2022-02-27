package sn.membre.br.membre.service;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sn.membre.br.membre.entity.MembreEntity;
import sn.membre.br.membre.exception.MembreNotFoundException;
import sn.membre.br.membre.exception.MembreUpdateException;
import sn.membre.br.membre.model.Membre;
import sn.membre.br.membre.repository.MembreRepositpry;
import sn.membre.br.membre.util.membreMapper.MembreMaper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
@RunWith(SpringJUnit4ClassRunner.class)
public class MembreServicetest {
    @InjectMocks
    private MemberService memberService;
    @Mock
    private MembreRepositpry membreRepositpry;
@Test
    public void testCreateMembre(){
        // appeler la methode savemembre de MemberService
        //testons a la fin si la methode save repository a bien fonctionne
        //pour attester que le membre a ete bien cree
        Membre membre=createMembre();
        memberService.savemembre(membre);
        verify(membreRepositpry,times(1)).save(any());

    }
    @Test
    public void testGetMembrefound(){
        //definissons un membre a trouver dans la base de donner avec un id specifique
        Membre membre=createMembre();
        membre.setId(1L);
        // Enregistrer dans la base
        when(membreRepositpry.findById(2L)).thenReturn(Optional.of(MembreMaper.INSTANCE.mapModeltoEntity(membre)));
        //Appelons la methode
        Membre membreget=memberService.getMembreById(2L);
        //voyons si le membre est le meme
        MatcherAssert.assertThat(membre.getAdresse(),is(membreget.getAdresse()));

    }
    @Test
    public void testUpdateMembre(){
    Membre membre=createMembre();
    membre.setId(1L);
    when(membreRepositpry.findById(1L)).thenReturn(Optional.of(MembreMaper.INSTANCE.mapModeltoEntity(membre)));
    membre.setId(1L);
    membre.setNom("awa");
    Membre upate=memberService.upate(1L,membre);
    MatcherAssert.assertThat(membre.getNom(),is(upate.getNom()));
    }
    @Test(expected = MembreNotFoundException.class)
    public void testUpdateNOtFoundId(){
    when(membreRepositpry.findById(1L)).thenReturn(Optional.empty());
    Membre membre=createMembre();
    membre.setId(1L);
    memberService.upate(1L,membre);


    }
    @Test(expected = MembreNotFoundException.class)
    public void testDeleteNOtFoundId(){
        when(membreRepositpry.findById(1L)).thenReturn(Optional.empty());
        Membre membre=createMembre();
        membre.setId(1L);
        memberService.delete(1L);



    }
@Test
    public void testDeleteFoundId(){
    Membre membre=createMembre();
    membre.setId(1L);
        when(membreRepositpry.findById(1L)).thenReturn(Optional.of(MembreMaper.INSTANCE.mapModeltoEntity(membre)));
        memberService.delete(1L);
        verify(membreRepositpry,times(1)).delete(MembreMaper.INSTANCE.mapModeltoEntity(membre));


    }
    @Test(expected = MembreUpdateException.class)
    public  void testUpdateWrongId(){
    Membre membre=createMembre();
    membre.setId(2L);
    memberService.upate(1L,membre);


    }
    @Test(expected = MembreNotFoundException.class)
    public void testGetMembreNotfound(){
    when(memberService.getMembreById(1L)).thenReturn(null);
    memberService.getMembreById(2L);


    }
    @Test
    public void testGetAll(){
        // inserer 2 movie
        //ensuite appel de la methode
        //tester si le resultat contient 2 element
        //tester si la methode findAll a ete appele
        List<MembreEntity>   membres=new ArrayList<>();
        Membre membre=createMembre();
        membres.add(MembreMaper.INSTANCE.mapModeltoEntity(membre));
        membres.add(MembreMaper.INSTANCE.mapModeltoEntity(membre));
        //definit  l'element de retour
        when(membreRepositpry.findAll()).thenReturn(membres);
        //appeler la methode
        List<Membre> allMember=memberService.getAllMember();
        verify(membreRepositpry,times(1)).findAll();
        MatcherAssert.assertThat(allMember.size(),is(membres.size()));

    }

    private Membre createMembre(){
        Membre result=new Membre();
        result.setNom("modou");
        result.setPrenom("fatou");
        result.setLieudenaissance("barale");
        return  result;
    }

}
