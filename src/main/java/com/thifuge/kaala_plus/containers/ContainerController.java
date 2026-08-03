package com.thifuge.kaala_plus.containers;

import com.thifuge.kaala_plus.shared.constants.ApiEndpoints;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ApiEndpoints.CONTAINERS)
public class ContainerController {

    private final ContainerService containerService;

    @PostMapping
    public void create(@RequestBody Container container) {
        this.containerService.createContainer(container);
    }

    @GetMapping
    public List<Container> findAllContainers() {
        return this.containerService.findAllContainers();
    }

    @GetMapping(path = "{id}")
    public Container findContainer(@PathVariable Integer id) {
        return this.containerService.findContainer(id);
    }

    @GetMapping(path = "{reference}")
    public Container findContainer(@PathVariable String reference) {
        return this.containerService.findContainer(reference);
    }

    @PutMapping(path = "{id}")
    public Container updateContainer(@PathVariable Integer id, @RequestBody Container container) {
        return this.containerService.updateContainer(id, container);
    }

    @PutMapping(path = "{reference}")
    public Container updateContainer(@PathVariable String reference, @RequestBody Container container) {
        return this.containerService.updateContainer(reference, container);
    }

    @DeleteMapping(path = "{id}")
    public void deleteContainer(@PathVariable Integer id) {
        this.containerService.deleteContainer(id);
    }

    @DeleteMapping(path = "{reference}")
    public void deleteContainer(@PathVariable String reference) {
        this.containerService.deleteContainer(reference);
    }
}
