package edu.umb.cs681.hw012;


import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class Directory extends FSElement{
	
	private LinkedList<FSElement> children;

	public Directory(Directory parent, String name) {
		super(parent, name, 0, LocalDateTime.now());
		children = new LinkedList<FSElement>();
		isDirectory = true;
		isFile = false;
		rl = new ReentrantLock();
	}

	public LinkedList<FSElement> getChildren() {
		
		return this.children;
		
	}
	
	public Directory getDirectory(String directory) {
		rl.lock();
		try {
			String foundDirectory = null;
			Directory dir = null;
			
			for (int x = 0; x < children.size(); x++){
				
				foundDirectory = children.get(x).getName();
				
				if (foundDirectory == directory){
					
					dir = (Directory) children.get(x);
				}
				
			}
			return dir;
		}finally {
			rl.unlock();
		}
		
		
		
	}
	
	public void appendChild (FSElement element) {
		rl.lock();
		try {
			children.add(element);
		} finally {
			rl.unlock();
		}
		
		
	}
	
	
	public int getTotalSize () {
		rl.lock();
		try {
			int totalSize = 0;
			
			for (FSElement element: children){
			
					totalSize+= element.getSize();
				
			}
			
			return totalSize;
		} finally {
			rl.unlock();
		}
		
	}
	
	public int getSize() {
		return this.size;
	}
	
    @Override 
	public String toString() {
		return getName();
	}


	@Override
	public boolean isDirectory() {
		return this.isDirectory;
	}

}
