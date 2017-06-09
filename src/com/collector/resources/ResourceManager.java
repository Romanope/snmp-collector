package com.collector.resources;

public final class ResourceManager {

	
	private static IResource resourceOnline;
	
	private static IResource resourceOffiline;
	
	private ResourceManager() {
		
	}
	
	public synchronized static IResource getInstance(boolean online) {
		 
		if (online) {
			if (resourceOnline == null) {
				resourceOnline = new ResourceOnline();
			}
			
			return resourceOnline;
		} else {
			if (resourceOffiline == null) {
				resourceOffiline = new ResourceOffline();
			}
			return resourceOffiline;
		}
	}
}