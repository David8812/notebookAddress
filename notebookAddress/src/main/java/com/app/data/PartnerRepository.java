package com.app.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.model.Partner;

public interface PartnerRepository extends CrudRepository<Partner, Long> {
	List<Partner> findByNombre(String nombre);
}
