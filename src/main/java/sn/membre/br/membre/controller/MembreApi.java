package sn.membre.br.membre.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sn.membre.br.membre.model.Membre;

import java.util.List;

@RequestMapping("membre_br")
public interface MembreApi {


    @ApiResponses(
            value = {@ApiResponse(code = 201, message = "La creation du membre a bien reussi"),
                    @ApiResponse(code = 400, message = "echec de la creation"),
                    @ApiResponse(code = 500, message = "Echec du serveur"),
                    @ApiResponse(code = 401, message = "echech Authenfication")
                    })
    @ApiOperation(
            value = "creer une nouvelle membre",
            notes = "Cela va rcuperer un nouveau membre et retourner"
    )

    @PostMapping("/membre")
    public ResponseEntity<Membre> save(@Validated @RequestBody Membre membre);
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "la recherche d'un membre en passant son identifiant"),
                    @ApiResponse(code = 404, message = "le membre nexiste pa"),
                    @ApiResponse(code = 500, message = "Echec du serveur"),
                    @ApiResponse(code = 401, message = "echech Authenfication")
            })
    @ApiOperation(
            value = "mettre a jour le  membre",
            notes = "Cela va rcuperer le  membre mise a jour et le retourner"
    )
    @GetMapping("/membre/{id}")
    public ResponseEntity<Membre> getMembreById(@PathVariable Long id);
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "La mise a jour du membre a bien reussi"),
                    @ApiResponse(code = 400, message = "echec de la mise a jour"),
                    @ApiResponse(code = 500, message = "Echec du serveur"),
                    @ApiResponse(code = 401, message = "echech Authenfication")
            })
    @ApiOperation(
            value = "mettre a jour le  membre",
            notes = "Cela va rcuperer le  membre mise a jour et le retourner"
    )
    @PutMapping("/membre/{id}")
    public ResponseEntity<Membre> updateMembre(@PathVariable Long id,@NonNull @RequestBody Membre membre);
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Supprime le membre"),
                    @ApiResponse(code = 400, message = "echec de la creation"),
                    @ApiResponse(code = 500, message = "Echec du serveur"),
                    @ApiResponse(code = 401, message = "echec Authenfication")
            })
    @ApiOperation(
            value = "supprimer  le  membre",
            notes = "Cela va retourner le membre supprimer"
    )
    @DeleteMapping("membre/{id}")
    public ResponseEntity<Membre> delemembre(@PathVariable Long id);
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Envois la liste des membres de la base"),
                    @ApiResponse(code = 404, message = "La liste est vide"),
                    @ApiResponse(code = 500, message = "Echec du serveur"),
                    @ApiResponse(code = 401, message = "echech Authenfication")
            })
    @ApiOperation(
            value = "Recupere la liste des membres de la base",
            notes = "Cela va rcuperer la liste et renvoyer la liste au client"
    )
    @GetMapping("/membre/all")
    public ResponseEntity<List<Membre>> getMembreAll();

}
