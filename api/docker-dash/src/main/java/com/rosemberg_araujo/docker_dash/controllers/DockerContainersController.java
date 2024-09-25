package com.rosemberg_araujo.docker_dash.controllers;

import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.rosemberg_araujo.docker_dash.services.DockerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/containers")
public class DockerContainersController {
    private final DockerService dockerService;

    public DockerContainersController(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    @GetMapping("")
    public List<Container> listContainers(@RequestParam(required = false, defaultValue = "true") Boolean showAll){
        return dockerService.listContainers(showAll);
    }
}
