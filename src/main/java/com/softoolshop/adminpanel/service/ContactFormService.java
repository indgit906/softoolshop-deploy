package com.softoolshop.adminpanel.service;

import java.util.List;

import com.softoolshop.adminpanel.dto.ContactFormDTO;

public interface ContactFormService {

	ContactFormDTO saveMessage(ContactFormDTO contactForm);

	List<ContactFormDTO> getAllMessages();

}
