package com.memtrip.xcode.appengine.bash;

import java.io.File;
import java.util.ArrayList;

/**
 * @author memtrip
 */
public class ServiceGeneratorProcessBuilder {
	private ArrayList<String> commandList;
	private String directory;
	
	public void setDiscovery(String discovery) {
		if (discovery != null)
			commandList.add(discovery);
	}
	
	public void setOutput(String output) {
		if (output != null) {
			commandList.add("--outputDir");
			commandList.add(output);
		}
	}
	
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	public ServiceGeneratorProcessBuilder(String serviceGeneratorExec) {
		commandList = new ArrayList<String>();
		commandList.add(serviceGeneratorExec);
	}
	
	public ProcessBuilder getProcessBuilder() {
		String[] commandArray = new String[commandList.size()];
		commandList.toArray(commandArray);
		ProcessBuilder processBuilder = new ProcessBuilder(commandArray);
		
		if (directory != null)
			processBuilder.directory(new File(directory));
		
		processBuilder.redirectErrorStream(true);
		
		return processBuilder;
	}
}
