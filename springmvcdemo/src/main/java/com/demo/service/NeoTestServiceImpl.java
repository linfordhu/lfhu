package com.demo.service;

import com.demo.NeoTestService;
import com.demo.neo4jdomain.Person;
import com.demo.neo4jdomain.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * neo4j test service implements
 * @author hlf
 * @since 2019/07/01 17:00
 */
@Service("neoTestService")
public class NeoTestServiceImpl implements NeoTestService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void doWork() {

        Iterable<Person> a = personRepository.findAll();
        a.forEach(System.out::println);
    }
}
