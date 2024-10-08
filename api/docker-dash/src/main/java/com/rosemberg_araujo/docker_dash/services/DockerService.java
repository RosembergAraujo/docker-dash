package com.rosemberg_araujo.docker_dash.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;

@Service
public class DockerService {
    private final DockerClient dockerClient;

    public DockerService(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public List<Container> listContainers(Boolean showAll) {
        return dockerClient.listContainersCmd().withShowAll(showAll).exec();
    }

    public List<Image> listImages() {
        return dockerClient.listImagesCmd().exec();
    }

    public List<Image> listImagesFilteringByImageName(String filterName) {
        return dockerClient.listImagesCmd().withImageNameFilter(filterName).exec();
    }

    public void startContainer(String containerId) {
        dockerClient.startContainerCmd(containerId).exec();
    }

    public void stopContainer(String containerId) {
        dockerClient.stopContainerCmd(containerId).exec();
    }

    public void removeContainer(String containerId) {
        dockerClient.removeContainerCmd(containerId).exec();
    }

    public void createContainer(String imageName) {
        dockerClient.createContainerCmd(imageName).exec();
    }
}
