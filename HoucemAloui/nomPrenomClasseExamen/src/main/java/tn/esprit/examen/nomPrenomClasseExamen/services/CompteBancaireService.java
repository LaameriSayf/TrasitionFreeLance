package tn.esprit.examen.nomPrenomClasseExamen.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.nomPrenomClasseExamen.entities.CompteBancaire;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.CompteBancaireRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompteBancaireService {
   @Autowired
    CompteBancaireRepository compteBancaireRepository;

    public List<CompteBancaire> getAllComptes() {
        return compteBancaireRepository.findAll();
    }

    public Optional<CompteBancaire> getCompteById(Long id) {
        return Optional.ofNullable(compteBancaireRepository.findCompteById(id));
    }

    public CompteBancaire addCompte(CompteBancaire compte) {
        return compteBancaireRepository.save(compte);
    }

    /*@Transactional
    public void updateCompte(Long id, String type, float solde) {
        compteBancaireRepository.updateCompte(id, type, solde);
    }*/

    public void deleteCompte(Long id) {
        compteBancaireRepository.deleteById(id);
    }

    public void ajouterCompteBancaire(Long userId, String typecompte, float solde) {
        compteBancaireRepository.ajouterCompteBancaire(typecompte, solde, userId);
    }
}
