package com.rosemberg_araujo.docker_dash.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.dockerjava.api.model.Container;
import com.rosemberg_araujo.docker_dash.services.DockerService;

@RestController
@RequestMapping("/api/containers")
public class DockerContainersController {
    private final DockerService dockerService;

    public DockerContainersController(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    @GetMapping("")
    public List<Container> listContainers(@RequestParam(required = false, defaultValue = "true") Boolean showAll) {
        return dockerService.listContainers(showAll);
    }

    @PostMapping("/start/{id}")
    public void startContainer(@PathVariable String id) {
        dockerService.startContainer(id);
    }

    @PostMapping("/stop/{id}")
    public void stopContainer(@PathVariable String id) {
        dockerService.stopContainer(id);
    }

    @DeleteMapping("/{id}")
    public void removeContainer(@PathVariable String id) {
        dockerService.removeContainer(id);
    }

    @PostMapping("/create/{imageName}")
    public void createContainer(@PathVariable String imageName) {
        dockerService.createContainer(imageName);
    }
}
