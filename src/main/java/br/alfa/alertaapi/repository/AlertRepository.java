package br.alfa.alertaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.alfa.alertaapi.models.Alert;

public interface AlertRepository extends JpaRepository<Alert, Integer> {

}
