package com.kad.repository;

import com.kad.entity.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactFormRepository extends JpaRepository<ContactForm,Integer> {
}
