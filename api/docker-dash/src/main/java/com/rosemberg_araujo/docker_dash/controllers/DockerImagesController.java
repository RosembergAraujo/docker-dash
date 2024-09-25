package com.rosemberg_araujo.docker_dash.controllers;

import com.github.dockerjava.api.model.Image;
import com.rosemberg_araujo.docker_dash.services.DockerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class DockerImagesController {
    private final DockerService dockerService;

    public DockerImagesController(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    @GetMapping("")
    public List<Image> listImages(){
        return dockerService.listImages();
    }

    @GetMapping("/filter")
    public List<Image> listImagesFilteringByImageName(@RequestParam(required = false, defaultValue = "") String filterName){
        return dockerService.listImagesFilteringByImageName(filterName);
    }

    @PostMapping("/{id}/start")
    public void startContainer(@PathVariable String id){
        dockerService.startContainer(id);
    }

    @PostMapping("/{id}/stop")
    public void stopContainer(@PathVariable String id){
        dockerService.stopContainer(id);
    }

    @PostMapping("/{id}/remove")
    public void removeContainer(@PathVariable String id){
        dockerService.removeContainer(id);
    }

    @PostMapping("/{imageName}/create")
    public void createContainer(@PathVariable String imageName){
        dockerService.createContainer(imageName);
    }

}
