package com.pratz.util;

/**
 * This class is used to identify linked tags present in the html which is later used in constructing the stored location of the 
 * file/directory in the file system <br>
 * 
 * 
 * <br>
 * <b>ID</b> is used when the extracted value of source starts with # <br>
 * <b>FILE</b> is used when the extracted value of source contains a . <br>
 * <b>DIRECTORY</b> is used when the extracted value of source ends with / <br>
 * <b>OTHER</b> is used when the extracted value does not match any of the other values
 *
 */
public class SourceIdentifier {
	
	public enum SourceType{
		ID, FILE, DIRECTORY, OTHER
	}
	
	public static SourceType identifySource(String string){
		if(string.startsWith("#")){
			return SourceType.ID;
		}else if(string.endsWith("/")){
			return SourceType.DIRECTORY;
		}else if(string.contains(".")){
			return SourceType.FILE;
		}else{
			return SourceType.OTHER;
		}
	}

}
