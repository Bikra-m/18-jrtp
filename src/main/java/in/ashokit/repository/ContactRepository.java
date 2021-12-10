package in.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entitis.Contact;

public interface ContactRepository extends JpaRepository<Contact, Serializable> {

}
