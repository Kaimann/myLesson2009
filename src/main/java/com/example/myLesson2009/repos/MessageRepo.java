package com.example.myLesson2009.repos;

import com.example.myLesson2009.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    List<Message> findByAddress(String address);
}
