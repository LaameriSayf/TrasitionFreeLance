package tn.esprit.examen.nomPrenomClasseExamen.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.nomPrenomClasseExamen.entities.CarteBancaire;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.CarteBancaireRepository;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class CarteBancaireService {
    @Autowired
    CarteBancaireRepository carteBancaireRepository;

    public List<CarteBancaire> getAllCartes() {
        return carteBancaireRepository.findAllCartes();
    }
    public Optional<CarteBancaire> getCarteById(int id) {
        return Optional.ofNullable(carteBancaireRepository.findCarteById(id));
    }

    public CarteBancaire addCarte(CarteBancaire carte) {
        return carteBancaireRepository.save(carte);
    }

    @Transactional
    public void updateCarte(int id, String type, Date date, String statut) {
        carteBancaireRepository.updateCarte(id, type, date, statut);
    }

    @Transactional
    public void deleteCarte(int id) {
        carteBancaireRepository.deleteCarte(id);
    }

    public List<CarteBancaire> getActiveCartes() {
        return carteBancaireRepository.findActiveCartes();
    }
}
