package io.vteial.watchyoursales.repository;

import io.vteial.watchyoursales.model.AutoNumber;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoNumberRepository extends JpaRepository<AutoNumber, String> {

}
