package com.example.webpastebinspringboot;

import com.example.webpastebinspringboot.domain.Base;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Base, Long> {
}
