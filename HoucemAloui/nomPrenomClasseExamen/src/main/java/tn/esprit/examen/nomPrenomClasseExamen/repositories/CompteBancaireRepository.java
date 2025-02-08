package tn.esprit.examen.nomPrenomClasseExamen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.nomPrenomClasseExamen.entities.CompteBancaire;
import org.springframework.data.jpa.repository.Modifying;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CompteBancaireRepository extends JpaRepository<CompteBancaire, Long> {

    @Query("SELECT c FROM CompteBancaire c")
    List<CompteBancaire> findAllComptes();


    @Query("SELECT c FROM CompteBancaire c WHERE c.idCompte = :id")
    CompteBancaire findCompteById(@Param("id") Long id);




    @Modifying
    @Transactional
    @Query(" INSERT INTO CompteBancaire (typecompte, solde, user) " +
            "VALUES (:typecompte, :solde, (SELECT u FROM User u WHERE u.id = :userId))")
    void ajouterCompteBancaire(@Param("typecompte") String typecompte,
                               @Param("solde") float solde,
                               @Param("userId") Long userId);

}
