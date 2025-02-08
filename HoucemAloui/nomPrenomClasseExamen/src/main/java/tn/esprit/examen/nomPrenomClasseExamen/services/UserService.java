package tn.esprit.examen.nomPrenomClasseExamen.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.examen.nomPrenomClasseExamen.entities.User;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
@Service
public class UserService{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository ur;

    public List<User> getall() {
        List<User> lu=ur.findAll();
        return lu;
    }


    public void add(User user) {
        ur.save(user);
    }


    public void delete(Long id) {
        ur.deleteById(id);
    }


    public void update(User user) {
        ur.save(user);
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public Long countUser(){
        return ur.count();
    }


}
