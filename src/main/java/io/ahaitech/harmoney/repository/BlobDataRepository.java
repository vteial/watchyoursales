package io.ahaitech.harmoney.repository;

import io.ahaitech.harmoney.model.BlobData;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlobDataRepository extends
		PagingAndSortingRepository<BlobData, Long> {

}
