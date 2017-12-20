package org.pathvisio.ora.plugin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;

public class Methods 
{
	
	public File GeneSetBridge;
	
	public Methods() {}
	
	public  void reader(File f) throws FileNotFoundException
	{
		Scanner x1 = new Scanner(f);
		
		while(x1.hasNext())
		{
			String a =x1.next();
			System.out.printf("%s\n",a);
		}
		
		x1.close();
	}
	
	public static void chooser(String[] args)
	{

	    JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new File("."));

	    chooser.setFileFilter(new javax.swing.filechooser.FileFilter()
	    {
	        public boolean accept(File f) 
	        {
	            return f.getName().toLowerCase().endsWith(".txt")
	            || f.isDirectory();
	        }

	        public String getDescription() 
	        {
	            return "Text Documents (.txt)";
	        }
	    });

	    int r = chooser.showOpenDialog(new JFrame());
	    if (r == JFileChooser.APPROVE_OPTION) 
	    {
	        String name = chooser.getSelectedFile().getName();
	        String pathToFIle = chooser.getSelectedFile().getPath();
	        System.out.println(name);
	    
	        try
	        {
	            BufferedReader reader = new BufferedReader( new FileReader( pathToFIle ) ); //Setup the reader

	            while (reader.ready()) 
	            { 
	            	//While there are content left to read
	                String line = reader.readLine(); //Read the next line from the file
	                String[] tokens = line.split( "url = " ); //Split the string at every @ character. Place the results in an array.

	                for (String token : tokens)
	                { //Iterate through all of the found results

	                        //System.out.println(token);
	                        System.out.println(token);
	                }

	            }

	            reader.close(); //Stop using the resource
	        }
	    
	        
	        catch (Exception e)
	        {//Catch exception if any
	            System.err.println("Error: " + e.getMessage());
	        }
	    }
	}
	
	
	public void reader2 (File f)
	{
		String PathToFile = f.getPath();
	
		try
        {
            BufferedReader reader = new BufferedReader( new FileReader( PathToFile ) ); //Setup the reader

            while (reader.ready()) 
            { 
            	//While there are content left to read
                String line = reader.readLine(); //Read the next line from the file
                String[] tokens = line.split( "url = " ); //Split the string at every @ character. Place the results in an array.
                //List<String> NamesTemp = new ArrayList<String>();
                
                for (String token : tokens)
                { //Iterate through all of the found results

                        //System.out.println(token);
                        System.out.println(token);
                        //NamesTemp.add(token);
                }

            }

            reader.close(); //Stop using the resource
        }
		catch (Exception e)
		{//Catch exception if any
            System.err.println("Error: " + e.getMessage());
		}
	}
	
	public void store (File f, List<String> l) 
	{
		String PathToFile = f.getPath();
		
		try
        {
            BufferedReader reader = new BufferedReader( new FileReader( PathToFile ) ); //Setup the reader

            while (reader.ready()) 
            { 
            	//While there are content left to read
                String line = reader.readLine(); //Read the next line from the file
                String[] tokens = line.split( "url = " ); //Split the string at every @ character. Place the results in an array.
                
                
                for (String token : tokens)
                { //Iterate through all of the found results

                        //System.out.println(token);
                        //System.out.println(token);
                        l.add(token);
                }

            }

            reader.close(); //Stop using the resource
        }
		catch (Exception e)
		{//Catch exception if any
            System.err.println("Error: " + e.getMessage());
		}
	}
	public void loader (File f,JList<String> l)
	{
		String PathToFile = f.getPath();
		ArrayList<String> GeneNames = new ArrayList<String>();
	//	JList GeneNameList;
		
		try
        {
            BufferedReader reader = new BufferedReader( new FileReader( PathToFile ) ); //Setup the reader

            while (reader.ready()) 
            { 
            	//While there are content left to read
                String line = reader.readLine(); //Read the next line from the file
                String[] tokens = line.split( "url = " ); //Split the string at every @ character. Place the results in an array.
                
                
                for (String token : tokens) 
                {
                    GeneNames.add(token);
                }
                
            }
            reader.close(); //Stop using the resource
            
        }
		catch (Exception e)
		{//Catch exception if any
            System.err.println("Error: " + e.getMessage());
		}
		
		
		l = new JList(GeneNames.toArray());
	}
	
	public File StoreGeneSet(File f)
	{
		GeneSetBridge = f;
		reader2(GeneSetBridge);
		return GeneSetBridge;
	}
	


}



