package io.ahaitech.harmoney.repository;

import io.ahaitech.harmoney.model.AutoNumber;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoNumberRepository extends JpaRepository<AutoNumber, String> {

}
