package edu.umb.cs681.hw012;


public class FileSystem implements Runnable{
	
	private static FileSystem fs;
	public Directory root = null;
	private int tab;

	private FileSystem() {}
	
	public static FileSystem getFileSystem(){
		
		clearSystem();
		
		if (fs == null){
			fs = new FileSystem();
			
		}
		return fs;
	}
	
	private static void clearSystem(){
		fs = null;
	}
	
	public Directory getRootDirectory(){
		if (this.root == null){
				this.root = new Directory(null, "root");
			}
		
		return this.root;
	}

	
	public String getTab(){
		
		String tabs = "";
		String tab = "\t";
		
		for(int i=0; i < this.tab; i++){
			
			tabs = tabs + tab;
		}
		
		return tabs;
		
	}

	
	@Override
	public void run() {
		
		FileSystem fileSystem = FileSystem.getFileSystem();
		
		File rand = new File(fileSystem.getRootDirectory(), "random file", 5);
		
		Directory system = new Directory(fileSystem.root, "system");
		File a = new File(system, "a.jpg", 10);
		File b = new File(system, "b.jpg", 45);
		File c = new File(system, "c.jpg", 6);
		
		
		Directory home = new Directory(fileSystem.root, "home");
		File d = new File(home, "d.jpg", 12);
		
		Directory pictures = new Directory(home, "pictures");
		File e = new File(pictures, "e.jpg", 75);
		File f = new File(pictures, "f.jpg", 33);
		
		Directory newPics = new Directory(pictures, "new pics");
		File g = new File(pictures, "g.jpg", 16 );
		File h = new File(pictures, "h.jpg", 15);
		
		fileSystem.root.appendChild(system);
		fileSystem.root.appendChild(home);
		fileSystem.root.appendChild(rand);
		
		system.appendChild(a);
		system.appendChild(b);
		system.appendChild(c);
		
		pictures.appendChild(e);
		pictures.appendChild(f);
		
		newPics.appendChild(g);
		newPics.appendChild(h);
		
		home.appendChild(d);
		home.appendChild(pictures);
		
		pictures.appendChild(newPics);
		System.out.print("Children of pictures directory : " + pictures.getChildren() + "\n");
		System.out.println("home is a directory ? : " + home.isDirectory());
		System.out.println("\"f.jpg\" is a file ? : " + f.isFile());
		System.out.println("Total size : " + home.getTotalSize());
		System.out.println("Size of \"f.jpg\" : " + f.getSize() + "\n");
	}
	
	public static void main (String args[]) throws InterruptedException{

		for(int n=0;n<2;n++){
			Thread t = new Thread(new FileSystem());
			System.out.println("Thread " + (n+1) + " : ");
		    t.start();
		    Thread.sleep(1000);
		}
		
		
	}

	

}
