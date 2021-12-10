package in.ashokit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.bindings.ContactForm;
import in.ashokit.entitis.Contact;
import in.ashokit.repository.ContactRepository;
@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	public String saveData(ContactForm form) {

		// here repository save(entity) required entity class object
		// but here returns binding class object
		// copy from binding class object to entity class

		Contact entity = new Contact();
		BeanUtils.copyProperties(form, entity);
		entity.setActiveSw("Y");
		contactRepository.save(entity);
		
		if (entity.getContactId() != null) {
			return "SUCCESS";
		}
		return "FAILURE";
	}

	public List<ContactForm> viewAllContact() {

		List<ContactForm> dataList = new ArrayList<>();
		List<Contact> findAll = contactRepository.findAll();

		for (Contact entity : findAll) {
			ContactForm form = new ContactForm();
			BeanUtils.copyProperties(entity, form);
			dataList.add(form);
		}

		return dataList;
	}

	public ContactForm editContact(Integer contactId) {

		Optional<Contact> findById = contactRepository.findById(contactId);
		if (findById.isPresent()) {
			Contact entity = findById.get();
			ContactForm form = new ContactForm();
			BeanUtils.copyProperties(entity, form);
			return form;
		}

		return null;
	}

	public List<ContactForm> deleteContact(Integer contactId) {

		contactRepository.deleteById(contactId);
		
		List<ContactForm> dataList = new ArrayList<>();
		List<Contact> findAll = contactRepository.findAll();

		for (Contact entity : findAll) {
			ContactForm form = new ContactForm();
			BeanUtils.copyProperties(entity, form);
			dataList.add(form);
		}
		return dataList;	
	}

}
