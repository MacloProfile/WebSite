package com.example.webpastebinspringboot;

import com.example.webpastebinspringboot.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
