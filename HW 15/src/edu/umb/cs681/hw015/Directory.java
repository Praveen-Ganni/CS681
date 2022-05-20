package edu.umb.cs681.hw015;


import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Directory extends FSElement{
	
	private ConcurrentLinkedQueue<FSElement> q;

	public Directory(Directory parent, String name) {
		super(parent, name, 0, LocalDateTime.now());
		q = new ConcurrentLinkedQueue<FSElement>();
		isDirectory = true;
		isFile = false;
		rl = new ReentrantLock();
	}

	public ConcurrentLinkedQueue<FSElement> getChildren() {
		
		return this.q;
		
	}
	
	public Directory getDirectory(String directory) {
		rl.lock();
		try {
			String foundDirectory = null;
			Directory dir = null;
			Iterator<FSElement> i = q.iterator();
			while (i.hasNext()) {
			    foundDirectory = i.next().getName();
				
				if (foundDirectory == directory){
					
					dir = (Directory) i.next();
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
			q.add(element);
		} finally {
			rl.unlock();
		}
		
		
	}
	
	
	public int getTotalSize () {
		rl.lock();
		try {
			int totalSize = 0;
			
			for (FSElement element: q){
			
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
