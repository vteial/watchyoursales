package io.vteial.watchyoursales.repository;

import io.vteial.watchyoursales.model.BlobData;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlobDataRepository extends
		PagingAndSortingRepository<BlobData, Long> {

}
