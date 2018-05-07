package com.app.service;

import java.util.List;

import com.app.model.Partner;

public interface PartnerService {
	public List<Partner> findAll();

	public List<Partner> findByNombre(String nombre);

	public Partner save(Partner partner);

	public void delete(Partner partner);

	public Partner findById(Long id);
	
	public void deleteById(Long id);
}
