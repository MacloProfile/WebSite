package com.example.webpastebinspringboot;

import com.example.webpastebinspringboot.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message>  findByTag(String tag);

}
