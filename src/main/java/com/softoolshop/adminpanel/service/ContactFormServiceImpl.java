package com.softoolshop.adminpanel.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softoolshop.adminpanel.dto.ContactFormDTO;
import com.softoolshop.adminpanel.entity.ContactForm;
import com.softoolshop.adminpanel.repository.ContactFormRepository;

@Service
public class ContactFormServiceImpl implements ContactFormService {
	
	@Autowired
	private ContactFormRepository cntctRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ContactFormDTO saveMessage(ContactFormDTO contactForm) {
	    ContactForm entity = modelMapper.map(contactForm, ContactForm.class);
	    entity.setCrtDate(LocalDateTime.now());
	    ContactForm savedEntity = cntctRepo.save(entity);

	    if (savedEntity != null && savedEntity.getEnqId() != null && savedEntity.getEnqId() > 0) {
	        ContactFormDTO responseDto = modelMapper.map(savedEntity, ContactFormDTO.class);
	        return responseDto;
	    }

	    return null;
	}

	@Override
	public List<ContactFormDTO> getAllMessages() {
		List<ContactForm> entities = cntctRepo.findAll();
		return Arrays.asList(modelMapper.map(entities, ContactFormDTO[].class));
	}

}
