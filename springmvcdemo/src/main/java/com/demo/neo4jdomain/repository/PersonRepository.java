package com.demo.neo4jdomain.repository;

import java.util.List;


import com.demo.neo4jdomain.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {

    List<Person> findByName(String name);

    List<Person> findByNameLike(String name);
}
