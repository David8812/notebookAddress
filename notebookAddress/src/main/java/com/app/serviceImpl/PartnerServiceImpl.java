package com.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.data.PartnerRepository;
import com.app.model.Partner;
import com.app.service.PartnerService;

@Component
public class PartnerServiceImpl implements PartnerService {
	
	@Autowired
	PartnerRepository partnerRepository;

	@Override
	public List<Partner> findAll() {
		return (List<Partner>) partnerRepository.findAll();
	}

	@Override
	public List<Partner> findByNombre(String nombre) {
		return partnerRepository.findByNombre(nombre);
	}

	@Override
	public Partner save(Partner partner) {
		return partnerRepository.save(partner);
	}

	@Override
	public void delete(Partner partner) {
		partnerRepository.delete(partner);
	}

	@Override
	public Partner findById(Long id) {
		return partnerRepository.findById(id).get();
	}

	@Override
	public void deleteById(Long id) {
		partnerRepository.deleteById(id);
	}

}
