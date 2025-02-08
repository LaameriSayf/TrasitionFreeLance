package tn.esprit.examen.nomPrenomClasseExamen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.nomPrenomClasseExamen.entities.CarteBancaire;
import tn.esprit.examen.nomPrenomClasseExamen.services.CarteBancaireService;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@RestController
@RequestMapping("/api/auth/cartes")
@RequiredArgsConstructor
public class CarteBancaireController {
    private final CarteBancaireService carteBancaireService;

    @GetMapping
    public List<CarteBancaire> getAllCartes() {
        return carteBancaireService.getAllCartes();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<CarteBancaire> getCarteById(@PathVariable int id) {
        Optional<CarteBancaire> carte = carteBancaireService.getCarteById(id);
        return carte.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public CarteBancaire addCarte(@RequestBody CarteBancaire carte) {
        return carteBancaireService.addCarte(carte);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateCarte(@PathVariable int id, @RequestBody CarteBancaire carte) {
        carteBancaireService.updateCarte(id, carte.getTypeCarte(), carte.getDateExpiration(), carte.getStatut());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCarte(@PathVariable int id) {
        carteBancaireService.deleteCarte(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/active")
    public List<CarteBancaire> getActiveCartes() {
        return carteBancaireService.getActiveCartes();
    }
}
