package com.github.dockerjava.jaxrs;

import static javax.ws.rs.client.Entity.entity;

import java.io.InputStream;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.command.AttachContainerCmd;

public class AttachContainerCmdExec extends AbstrDockerCmdExec<AttachContainerCmd, InputStream> implements AttachContainerCmd.Exec {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AttachContainerCmdExec.class);
	
	public AttachContainerCmdExec(WebTarget baseResource) {
		super(baseResource);
	}

	@Override
	public InputStream exec(AttachContainerCmd command) {
		WebTarget webResource = getBaseResource().path("/containers/{id}/attach")
                .resolveTemplate("{id}", command.getContainerId())
                .queryParam("logs", command.hasLogsEnabled() ? "1" : "0")
                .queryParam("timestamps",command.hasTimestampsEnabled() ? "1" : "0")
                .queryParam("stdout", command.hasStdoutEnabled() ? "1" : "0")
                .queryParam("stderr", command.hasStderrEnabled() ? "1" : "0")
                .queryParam("follow", command.hasFollowStreamEnabled() ? "1" : "0");

		LOGGER.trace("POST: {}", webResource);
		
		return webResource.request().accept(MediaType.APPLICATION_OCTET_STREAM_TYPE)
				.post(entity(null, MediaType.APPLICATION_JSON), Response.class).readEntity(InputStream.class);
	}

}
