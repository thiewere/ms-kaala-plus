package com.thifuge.kaala_plus.containers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContainerRepository extends JpaRepository<Container, Integer> {

    Optional<Container> findByReference(String reference);
}
