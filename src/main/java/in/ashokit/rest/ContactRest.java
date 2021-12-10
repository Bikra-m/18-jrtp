package in.ashokit.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.ContactForm;
import in.ashokit.service.ContactService;

@RestController
public class ContactRest {

	@Autowired
	private ContactService service;

	@PostMapping("/saveData")
	public String saveContactData(@RequestBody ContactForm form) {
     //System.out.println(form);
		String status = service.saveData(form);
		return status;
	}
	@GetMapping("/display")
	public List<ContactForm> displayAllTheContactRecords(){		
		List<ContactForm> viewAllContact = service.viewAllContact();		
		return viewAllContact;
	}
	@GetMapping("/edit/{contactId}")
	public ContactForm editcontact(@PathVariable Integer contactId) {
		ContactForm editContact = service.editContact(contactId);
		return editContact;
	}
	@DeleteMapping("/delete/{contactId}")
	public List<ContactForm> deletedata(@PathVariable Integer contactId){
		List<ContactForm> deleteContact = service.deleteContact(contactId);
		return deleteContact;
	}
}
