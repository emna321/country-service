package org.sid.compteservice;

import java.util.Date;
import org.sid.compteservice.entities.Compte;
import org.sid.compteservice.enums.TypeCompte;
import org.sid.compteservice.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CompteService1Application {

    public static void main(String[] args) {
        SpringApplication.run(CompteService1Application.class, args);
    }

    @Bean
    CommandLineRunner start(CompteRepository compteRepository) {
        return args -> {

            // Insérer 3 comptes dans la base H2
            compteRepository.save(new Compte(null, 98000, new Date(), TypeCompte.COURANT));
            compteRepository.save(new Compte(null, 12000, new Date(), TypeCompte.EPARGNE));
            compteRepository.save(new Compte(null, 2100,  new Date(), TypeCompte.COURANT));

            // Afficher tous les comptes dans la console
            compteRepository.findAll().forEach(cp -> {
                System.out.println("Type  : " + cp.getType());
                System.out.println("Solde : " + cp.getSolde());
                System.out.println("---");
            });
        };
    }
}