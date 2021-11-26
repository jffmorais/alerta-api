package br.alfa.alertaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.alfa.alertaapi.models.AlertEmission;

public interface AlertEmissionRepositoty extends JpaRepository<AlertEmission, Integer> {

}
