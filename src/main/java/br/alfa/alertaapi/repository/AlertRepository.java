package br.alfa.alertaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.alfa.alertaapi.models.Alert;

public interface AlertRepository extends JpaRepository<Alert, Integer> {
	
	@Query("SELECT a FROM ALERT a WHERE a.userId = :id")
	List<Alert> findUserAlerts(@Param("id") int id);
	
	Alert findAlertById(int id);
}
