package com.pratz.util;

public class SourceIdentifier {
	
	public enum SourceType{
		ID, FILE, DIRECTORY
	}
	
	public static SourceType identifySource(String string){
		if(string.startsWith("#")){
			return SourceType.ID;
		}else if(string.endsWith("/")){
			return SourceType.DIRECTORY;
		}else {
			return SourceType.FILE;
		}
	}

}
