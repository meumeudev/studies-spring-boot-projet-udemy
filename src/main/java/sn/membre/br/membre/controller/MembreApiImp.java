package sn.membre.br.membre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sn.membre.br.membre.model.Membre;
import sn.membre.br.membre.service.MemberService;

import java.util.List;
@RequiredArgsConstructor
@RestController //cette annotation nous aide a reconnaitre que la classe est un controller
public class MembreApiImp implements MembreApi {
    private  final MemberService memberService;




    @Override
    public
    ResponseEntity<Membre> save(@RequestBody  Membre membre) {
        //si la methode s'est bien execute alors renvoi moi un Membre
        return new  ResponseEntity<>(memberService.savemembre(membre), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Membre> getMembreById(Long id) {
        return new ResponseEntity<>(memberService.getMembreById(id),HttpStatus.OK);
    }

    @Override
    public
    ResponseEntity<Membre> updateMembre(@PathVariable Long id, @RequestBody Membre membre) {
        return new ResponseEntity<>(memberService.upate(id,membre),HttpStatus.OK);
    }

    @Override
    public
    ResponseEntity<Membre> delemembre(Long id) {
        return new ResponseEntity<>(memberService.delete(id),HttpStatus.OK);
    }

    @Override
    public
    ResponseEntity<List<Membre>> getMembreAll() {
        return new ResponseEntity<>(memberService.getAllMember(),HttpStatus.OK);
    }
}
