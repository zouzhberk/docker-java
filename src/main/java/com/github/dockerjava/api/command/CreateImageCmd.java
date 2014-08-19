package com.github.dockerjava.api.command;

import java.io.InputStream;

public interface CreateImageCmd extends DockerCmd<CreateImageResponse> {

	public String getRepository();

	public String getTag();
	
	public InputStream getImageStream();

	/**
	 * @param repository        the repository to import to
	 */
	public CreateImageCmd withRepository(String repository);

	/**
	 * @param imageStream       the InputStream of the tar file
	 */
	public CreateImageCmd withImageStream(InputStream imageStream);

	/**
	 * @param tag               any tag for this image
	 */
	public CreateImageCmd withTag(String tag);
	
	public static interface Exec extends DockerCmdExec<CreateImageCmd, CreateImageResponse> {
	}


}