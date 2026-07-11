package com.thifuge.kaala_plus.containers;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class ContainerService {

    private final ContainerRepository containerRepository;

    public void createContainer(Container container) {
        log.info("Creating a new container {}", container);
        this.containerRepository.save(container);
    }

    public List<Container> findAllContainers() {
        log.info("Finding all containers");
        return this.containerRepository.findAll();
    }

    public Container findContainer(Integer id) {
        log.info("Finding container by id {}", id);
        Optional<Container> container = this.containerRepository.findById(id);
        return container.orElseThrow(() -> new EntityNotFoundException(
                "Container with id " + id + " not found"
        ));
    }

    public Container findContainer(String reference) {
        log.info("Finding container by reference {}", reference);
        Optional<Container> container = this.containerRepository.findByReference(reference);
        return container.orElseThrow(() -> new EntityNotFoundException(
                "Container with reference " + reference + " not found"
        ));
    }

    public Container updateContainer(Integer id, Container container) {
        Container containerInDB = this.findContainer(id);

        log.info("Updating container by id {}", id);
        containerInDB.setReference(container.getReference());
        containerInDB.setUpdatedAt(container.getUpdatedAt());
        containerInDB = this.containerRepository.save(containerInDB);
        return containerInDB;
    }

    public Container updateContainer(String reference, Container container) {
        Container containerInDB = this.findContainer(reference);

        log.info("Updating container by reference {}", reference);
        containerInDB.setReference(container.getReference());
        containerInDB.setUpdatedAt(container.getUpdatedAt());
        containerInDB = this.containerRepository.save(containerInDB);
        return containerInDB;
    }

    public void deleteContainer(Integer id) {
        Container containerInDB = this.findContainer(id);
        log.info("Deleting container by id {}", id);
        this.containerRepository.delete(containerInDB);
    }

    public void deleteContainer(String reference) {
        Container containerInDB = this.findContainer(reference);
        log.info("Deleting container by reference {}", reference);
        this.containerRepository.delete(containerInDB);
    }
}
