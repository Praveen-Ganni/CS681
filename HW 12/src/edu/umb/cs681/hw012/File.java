package edu.umb.cs681.hw012;

import java.time.LocalDateTime;

public class File extends FSElement {
	
	public File(Directory parent, String name, int size) {
		
		super(parent, name, size, LocalDateTime.now());
		isFile = true;
		isDirectory = false;
	}
	
	public boolean isDirectory() {
		return this.isDirectory;
	}
	
	public void setSize (int size) {
		this.size = size;
	}
	
	public int getSize () {
		return this.size;
	}
	
	//override toString() method
    @Override 
    public String toString(){
    	
    	String str = "- " + getName();
        
        return str;
    }
    
   

}
