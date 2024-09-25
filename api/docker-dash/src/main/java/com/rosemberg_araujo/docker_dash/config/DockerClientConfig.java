package com.rosemberg_araujo.docker_dash.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;

@Configuration
public class DockerClientConfig {

	@Value("${docker.socket.path}")
	private String DOCKER_SOCKET_PATH;

	@Bean
	public DockerClient buildDockerClient() {
		DefaultDockerClientConfig.Builder defaultConfigBuilder = DefaultDockerClientConfig
				.createDefaultConfigBuilder();

		if (this.DOCKER_SOCKET_PATH.startsWith("unix://")) {
			defaultConfigBuilder.withDockerHost(this.DOCKER_SOCKET_PATH)
					.withDockerTlsVerify(false);
		}

		DefaultDockerClientConfig dockerClientConfig = defaultConfigBuilder.build();

		ApacheDockerHttpClient dockerHttpClient = new ApacheDockerHttpClient.Builder()
				.dockerHost(dockerClientConfig.getDockerHost()).build();

		return DockerClientBuilder.getInstance(dockerClientConfig)
				.withDockerHttpClient(dockerHttpClient)
				.build();
	}
}
