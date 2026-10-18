package org.sid.compteservice.web;

import java.util.List;
import org.sid.compteservice.entities.Compte;
import org.sid.compteservice.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comptes")
public class CompteRestController {

    @Autowired
    private CompteRepository compteRepository;

    // GET tous les comptes
    @GetMapping
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    // GET un compte par id
    @GetMapping("/{id}")
    public Compte getCompteById(@PathVariable Long id) {
        return compteRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Compte non trouvé : " + id));
    }

    // POST créer un compte
    @PostMapping
    public Compte createCompte(@RequestBody Compte compte) {
        return compteRepository.save(compte);
    }

    // PUT mettre à jour un compte
    @PutMapping("/{id}")
    public Compte updateCompte(@PathVariable Long id, @RequestBody Compte compte) {
        Compte existing = compteRepository.findById(id)
                          .orElseThrow(() -> new RuntimeException("Compte non trouvé : " + id));
        existing.setSolde(compte.getSolde());
        existing.setType(compte.getType());
        existing.setDateCreation(compte.getDateCreation());
        return compteRepository.save(existing);
    }

    // DELETE supprimer un compte
    @DeleteMapping("/{id}")
    public String deleteCompte(@PathVariable Long id) {
        compteRepository.deleteById(id);
        return "Compte " + id + " supprimé avec succès";
    }
}