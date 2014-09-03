package com.memtrip.xcode.appengine;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.memtrip.xcode.appengine.bash.ExecProcess;
import com.memtrip.xcode.appengine.bash.ServiceGeneratorProcessBuilder;
import com.memtrip.xcode.appengine.utils.StringUtils;

/**
 * @goal generate
 * @requiresProject false
 */
public class EntryPoint extends AbstractMojo {
	/**
	 * The dir where the command will be executed
	 * @parameter
	 */
	private String executionDirParam;
	
	/**
	 * The path where the ServiceGenerator executable is located
	 * @parameter
	 */
	private String serviceGeneratorExecPathParam;
	
	/**
	 * The path where target discovery file is located
	 * @parameter
	 */
	private String discoveryFilePathParam;
	
	/**
	 * The output path where the generated files will be created
	 * @parameter
	 */
	private String outputPathParam;
	
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		if (executionDirParam == null)
			throw new MojoExecutionException("The executionDirParam is a required argument");
		
		if (serviceGeneratorExecPathParam == null)
			throw new MojoExecutionException("The serviceGeneratorExecPathParam is a required argument");
		
		if (discoveryFilePathParam == null)
			throw new MojoExecutionException("The discoveryFilePathParam is a required argument");
		
		if (outputPathParam == null)
			throw new MojoExecutionException("The outputPathParam is a required argument");
		
		ServiceGeneratorProcessBuilder serviceGeneratorProcessBuilder = 
				new ServiceGeneratorProcessBuilder(serviceGeneratorExecPathParam);
		serviceGeneratorProcessBuilder.setDirectory(executionDirParam);
		serviceGeneratorProcessBuilder.setDiscovery(discoveryFilePathParam);
		serviceGeneratorProcessBuilder.setOutput(outputPathParam);
		
		ExecProcess execProcess = new ExecProcess(serviceGeneratorProcessBuilder.getProcessBuilder());
		int result = execProcess.start();
		
		if (result == ExecProcess.SUCCESS) {
			System.out.println(StringUtils.arrayListOut(execProcess.getOutput()));
		} else {
			throw new MojoExecutionException("service generator FAILED");
		}
	}
}