//GSEA plugin for PathVisio,

//Gene set enrichment plugin 

// Copyright 2017 BiGCaT Bioinformatics

//

// Licensed under the Apache License, Version 2.0 (the "License");

// you may not use this file except in compliance with the License.

// You may obtain a copy of the License at

//
// http://www.apache.org/licenses/LICENSE-2.0
//

// Unless required by applicable law or agreed to in writing, software

// distributed under the License is distributed on an "AS IS" BASIS,

// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.

// See the License for the specific language governing permissions and

// limitations under the License.

package org.pathvisio.ora.plugin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.bridgedb.DataSource;
import org.bridgedb.IDMapper;
import org.bridgedb.IDMapperException;
import org.bridgedb.Xref;
import org.pathvisio.core.data.XrefWithSymbol;
import org.pathvisio.core.util.PathwayParser;
import org.pathvisio.core.util.PathwayParser.ParseException;
import org.pathvisio.desktop.PvDesktop;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

//import jxl.Cell;
//import jxl.CellType;
//import jxl.Sheet;
//import jxl.Workbook;
//import jxl.read.biff.BiffException;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;


/**
 * @ Jesse
 * 
 *   This represents the Load data page where a genesetfile, a dataset file or a preranked list,
 *   and a phenotype file can be uploaded. It is also possible to select the phenotypes manually instead of
 *   using the phenotype file.
 * 
 */

public class LoadDataPage extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField title;
	private JTextField txtSelectGS;
	private JTextField txtGSPath;
	private JButton browseGS;
	private JTextField txtSelectPW;
	private JTextField txtPWPath;
	private JButton browsePW;
	private JTextField txtDSPath;
	private File geneSetsFile;
	private File dataSetFile;
	private File phenotypeFile;
	private File preRankedList;
	private JTextField txtPLPath;
	private JTextField txtPFPath;
	private JTextField txtLoadedFiles;
	
	public static List<String> namesTemp = new ArrayList<String>();
	public static List<String> geneNames;
	public static String geneSetsFileName;
	public static String dataSetFileName;
	public static String phenotypeFileName;
	public static String preRankedFileName;
	public static Boolean preRanked = false;
	public static JButton doneLoad = new JButton("Done");
	public static JList<String> listLoadedFileNames = new JList<String>(new DefaultListModel<String>());
	public static ArrayList<HashMap<String, double[]>> mylist = new ArrayList<HashMap<String, double[]>>();
    public static HashMap<String, double[]> map = new HashMap<String, double[]>();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	
	private final PvDesktop desktop;
	
	
	

	/**
	 * Launch the application.
	 */
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadDataPage frame = new LoadDataPage(desktop);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoadDataPage(PvDesktop desktop) {
		
		this.desktop = desktop;
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 787, 719);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Create title
		title = new JTextField();
		title.setBounds(321, 0, 116, 22);
		title.setText("Load Data Page");
		contentPane.add(title);
		title.setColumns(10);
		title.setEditable(false);
		
		/*//Create "Select gene sets" text
		txtSelectGS = new JTextField();
		txtSelectGS.setBounds(12, 35, 199, 22);
		txtSelectGS.setBackground(Color.GRAY);
		txtSelectGS.setText("Select Gene Set  ");
		contentPane.add(txtSelectGS);
		txtSelectGS.setColumns(10);
		
		//Create "Select gene sets" text
		txtSelectPW = new JTextField();
		txtSelectPW.setBounds(426, 35, 199, 22);
		txtSelectPW.setBackground(Color.GRAY);
		txtSelectPW.setText("Select pathway file  ");
		contentPane.add(txtSelectPW);
		txtSelectPW.setColumns(10);*/
		
		//Create "Loaded files" text
		txtLoadedFiles = new JTextField();
		txtLoadedFiles.setText("Loaded Files");
		txtLoadedFiles.setBounds(0, 416, 116, 22);
		contentPane.add(txtLoadedFiles);
		txtLoadedFiles.setColumns(10);
		
		//Create text fields to show paths of loaded files
		txtGSPath = new JTextField();
		txtGSPath.setBounds(12, 63, 223, 22);
		contentPane.add(txtGSPath);
		txtGSPath.setColumns(10);
		txtGSPath.setEditable(false);
		
		txtPWPath = new JTextField();
		txtPWPath.setBounds(426, 63, 223, 22);
		contentPane.add(txtPWPath);
		txtPWPath.setColumns(10);
		txtPWPath.setEditable(false);
		
	/*	txtDSPath = new JTextField();
		txtDSPath.setBounds(12, 173, 223, 22);
		contentPane.add(txtDSPath);
		txtDSPath.setColumns(10);
		txtDSPath.setEnabled(false);
		txtDSPath.setEditable(false);*/
		
		txtPLPath = new JTextField();
		txtPLPath.setColumns(10);
		txtPLPath.setBounds(426, 173, 223, 22);
		contentPane.add(txtPLPath);
		txtPLPath.setEnabled(false);
		txtPLPath.setEditable(false);
		
		txtPFPath = new JTextField();
		txtPFPath.setColumns(10);
		txtPFPath.setBounds(12, 299, 223, 22);
		contentPane.add(txtPFPath);
		txtPFPath.setEditable(false);
		txtPFPath.setEnabled(true);
		
		// Create horizontal lines for design
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(0, 238, 769, 14);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBounds(0, 121, 769, 25);
		contentPane.add(separator_1);
		
		
		//create browse buttons
		browseGS = new JButton("Browse");
		browseGS.setBounds(244, 62, 97, 25);	
		contentPane.add(browseGS);
		browseGS.setEnabled(false);
		
		browsePW = new JButton("Browse");
		browsePW.setBounds(660, 62, 97, 25);	
		contentPane.add(browsePW);
		browsePW.setEnabled(false);
		
		/*JButton browseDS = new JButton("Browse");
		browseDS.setBounds(244, 172, 97, 25);
		contentPane.add(browseDS);
		browseDS.setEnabled(false);*/
		
		JButton browsePL = new JButton("Browse");
		browsePL.setBounds(660, 172, 97, 25);
		contentPane.add(browsePL);
		browsePL.setEnabled(false);
		
		JButton browsePF = new JButton("Browse");
		browsePF.setBounds(244, 295, 97, 25);
		contentPane.add(browsePF);
		browsePF.setEnabled(false);
		
		
		
		//Create load buttons
		JButton loadGS = new JButton("Load");
		loadGS.setEnabled(false);
		loadGS.setBounds(12, 83, 97, 25);
		contentPane.add(loadGS);
		
		JButton loadPW = new JButton("Load");
		loadPW.setEnabled(false);
		loadPW.setBounds(426, 83, 97, 25);
		contentPane.add(loadPW);
		
		/*JButton loadDS = new JButton("Load");
		loadDS.setBounds(12, 195, 97, 25);
		contentPane.add(loadDS);
		loadDS.setEnabled(false);*/
		
		JButton loadPL = new JButton("Load");
		loadPL.setBounds(426, 195, 97, 25);
		contentPane.add(loadPL);
		loadPL.setEnabled(false);
		
		JButton loadPF = new JButton("Load");
		loadPF.setBounds(12, 322, 97, 25);
		contentPane.add(loadPF);
		loadPF.setEnabled(false);
		
		
		//create manual selection button for phenotypes
		JButton manualSelection = new JButton("SetPhenotypes");
		manualSelection.setBounds(493, 298, 132, 25);
		contentPane.add(manualSelection);
		manualSelection.setEnabled(false);
		
		//create done button
		//JButton doneLoad = new JButton("Done");
		doneLoad.setBounds(340, 621, 97, 25);
		contentPane.add(doneLoad);
		doneLoad.setEnabled(false);
		
	    
		//create radiobuttons
		
		JRadioButton rdbtnUseGeneset = new JRadioButton("Use Geneset");
		rdbtnUseGeneset.setEnabled(true);
	    buttonGroup_2.add(rdbtnUseGeneset);
	    rdbtnUseGeneset.setBounds(12, 29, 267, 25);
		contentPane.add(rdbtnUseGeneset);
		

		JRadioButton rdbtnUsePathwayFile = new JRadioButton("Use Pathway file");
		rdbtnUsePathwayFile.setEnabled(true);
	    buttonGroup_2.add(rdbtnUsePathwayFile);
	    rdbtnUsePathwayFile.setBounds(426, 29, 215, 25);
		contentPane.add(rdbtnUsePathwayFile);
		
	    JRadioButton rdbtnUseExpressionDataset = new JRadioButton("Use loaded Expression Dataset");
	    rdbtnUseExpressionDataset.setEnabled(false);
	    buttonGroup.add(rdbtnUseExpressionDataset);
		rdbtnUseExpressionDataset.setBounds(12, 139, 267, 25);
		contentPane.add(rdbtnUseExpressionDataset);
		

		JRadioButton rdbtnUsePrerankedList = new JRadioButton("Use Preranked List");
	    rdbtnUsePrerankedList.setEnabled(false);
	    buttonGroup.add(rdbtnUsePrerankedList);
		rdbtnUsePrerankedList.setBounds(426, 139, 215, 25);
		contentPane.add(rdbtnUsePrerankedList);
	    
	    
	    JRadioButton rdbtnSelectPhentypeFile = new JRadioButton("Select Phenotype File");
		buttonGroup_1.add(rdbtnSelectPhentypeFile);
		rdbtnSelectPhentypeFile.setBounds(12, 261, 267, 25);
		contentPane.add(rdbtnSelectPhentypeFile);
		rdbtnSelectPhentypeFile.setEnabled(false);
		
		JRadioButton rdbtnSelectPhenotypeManually = new JRadioButton("Select Phenotypes Manually");
		buttonGroup_1.add(rdbtnSelectPhenotypeManually);
		rdbtnSelectPhenotypeManually.setBounds(426, 261, 267, 25);
		contentPane.add(rdbtnSelectPhenotypeManually);
		rdbtnSelectPhenotypeManually.setEnabled(false);
		
		
		
		//create JList for displaying the files that have been loaded
		
		listLoadedFileNames.setBounds(0, 439, 769, 128);
		contentPane.add(listLoadedFileNames);
		
		
	    // From here on are actionlisteners for the respective components
		
		//Filechooser action for the gene set browse button
	    
		browseGS.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				geneSetsFile = chooser.getSelectedFile();
				String pathOfFile =geneSetsFile.getAbsolutePath();
				txtGSPath.setText(pathOfFile);
			}
		});
		
		
		
		//Actionlistener  to ensure that loadGS is only set active when text is present in the "txtGSPath"
		
		 final ActionListener actionListener = new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	                if (e.getActionCommand().equalsIgnoreCase("Enable"))
	                {
	                	loadGS.setEnabled(true);
	                }
	                else if (e.getActionCommand().equalsIgnoreCase("Disable"))
	                {
	                	loadGS.setEnabled(false);
	                }
	            }
	        };
	        
	        
	      //Works together with "actionlistener" created before  to ensure that load is only set active when text is present in the "txtGSPath"
	        
	        final Runnable target = new Runnable()
	        {

	            public void run()
	            {
	                while (true)
	                {
	                    final ActionListener[] listeners = txtGSPath.getActionListeners();
	                    for (ActionListener listener : listeners)
	                    {
	                        if (txtGSPath.getText().trim().length() > 0)
	                        {
	                            final ActionEvent event = new ActionEvent(txtGSPath, 1, "Enable");
	                            listener.actionPerformed(event);
	                        }
	                        else
	                        {
	                            final ActionEvent event = new ActionEvent(txtGSPath, 1, "Disable");
	                            listener.actionPerformed(event);
	                        }
	                    }
	                }
	            }
	        };

	        new Thread(target).start();
			txtGSPath.addActionListener(actionListener);

		
		
		// Load button action for geneset --> adds the name of the file to the created JList for display
		// and tests wheter you can display geneset on console
		// Also it stores the names of the geneset in a temporary list ("namesTemp") so that it can be used in the manual selection page. 
		loadGS.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				
				//m.reader2(geneSetsFile);
				geneSetsFileName = geneSetsFile.getName();
				((DefaultListModel)listLoadedFileNames.getModel()).addElement(geneSetsFileName);
				rdbtnUsePrerankedList.setEnabled(true);
				rdbtnUseExpressionDataset.setEnabled(true);
				
				
				
				
			}
		});
		

		// radiobutton action for the expressiondatasets --> enables "txtDSPath and browseDS, disables txtPLPath, loadPL and browsePL
		rdbtnUseExpressionDataset.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				txtDSPath.setEnabled(true);
				//browseDS.setEnabled(true);
				txtPLPath.setEnabled(false);
				browsePL.setEnabled(false);
				loadPL.setEnabled(false);
				
			}
		});
		
		//Filechooser action for the data set browse button
	/*	browseDS.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				dataSetFile = chooser.getSelectedFile();
				String pathOfFile =dataSetFile.getAbsolutePath();
				txtDSPath.setText(pathOfFile);
				dataSetFileName = dataSetFile.getName();
			}
		});*/
		
		
		//Actionlistener  to ensure that load is only set active when text is present in the "txtDSPath"
	/*	final ActionListener actionListener2 = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (e.getActionCommand().equalsIgnoreCase("Enable"))
                {
                	loadDS.setEnabled(true);
                }
                else if (e.getActionCommand().equalsIgnoreCase("Disable"))
                {
                	loadDS.setEnabled(false);
                }
            }
        };
        
        //Works together with "actionlistener2" created before  to ensure that load is only set active when text is present in the "txtDSPath"

        final Runnable target2 = new Runnable()
        {

            public void run()
            {
                while (true)
                {
                    final ActionListener[] listeners = txtDSPath.getActionListeners();
                    for (ActionListener listener : listeners)
                    {
                        if (txtDSPath.getText().trim().length() > 0)
                        {
                            final ActionEvent event = new ActionEvent(txtDSPath, 1, "Enable");
                            listener.actionPerformed(event);
                        }
                        else
                        {
                            final ActionEvent event = new ActionEvent(txtDSPath, 1, "Disable");
                            listener.actionPerformed(event);
                        }
                    }
                }
            }
        };

        new Thread(target2).start();
        txtDSPath.addActionListener(actionListener2);*/
		
		
		
        // Load button action for expression dataset --> adds the name of the file to the created JList for display
		/*loadDS.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Methods m = new Methods();
				
				
				System.out.println(dataSetFileName);
				((DefaultListModel)listLoadedFileNames.getModel()).addElement(dataSetFileName);
				//Stores gene names in list namesTemp for later use
				
				if(dataSetFile.exists())
				System.out.println("true1");
				else 
				System.out.println("false1");	
					
				if(dataSetFile.isFile())
				System.out.println("true2");
				else
				System.out.println("false2");
				if(dataSetFile.canRead())
			    System.out.println("true3");
				else
				System.out.println("false3");
				
			
				
				//geneNames = readExDS();
						
				/*try {
					m.store2(dataSetFileName,namesTemp);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				
				/*File inputWorkbook = dataSetFile;
			    Workbook w;
			    try 
			    {
			        w = Workbook.getWorkbook(inputWorkbook);
			        // Get the first sheet
			        Sheet sheet = w.getSheet(0);
			        // Loop over first 10 column and lines
			        
			        for (int j = 2; j < sheet.getColumns(); j++) 
			        {
			        	Cell cell = sheet.getCell(j, 2);
			        	CellType type = cell.getType();
			        	System.out.println("I got a label "+ cell.getContents());
				
			        } 
			    } 
			    
			    catch (BiffException e1)
			    {
			        e1.printStackTrace();
			    } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				
				/*rdbtnUsePrerankedList.setEnabled(false);
				rdbtnSelectPhentypeFile.setEnabled(true);
				rdbtnSelectPhenotypeManually.setEnabled(true);
			}
		}); */
		
		
		
		// Radiobutton action for the prerankedlist --> enables "txtPLPath and browsePL, disables txtDSPath, loadDS and browseDS
		rdbtnUsePrerankedList.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				txtDSPath.setEnabled(false);
				//browseDS.setEnabled(false);
				//loadDS.setEnabled(false);
				txtPLPath.setEnabled(true);
				browsePL.setEnabled(true);
			}
		});
		
		//Filechooser action for PreRanked List browse button
		browsePL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				preRankedList = chooser.getSelectedFile();
				String pathOfFile =preRankedList.getAbsolutePath();
				txtPLPath.setText(pathOfFile);
			}
		});
		
		//Actionlistener3  to ensure that load is only set active when text is present in the "txtPLPath"
		final ActionListener actionListener3 = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
		    	{
					if (e.getActionCommand().equalsIgnoreCase("Enable"))
		            {
						loadPL.setEnabled(true);
		            }
		            else if (e.getActionCommand().equalsIgnoreCase("Disable"))
		            {
		            	loadPL.setEnabled(false);
		            }
		        }
		};
		        
		//Works together with "actionlistener3" created before  to ensure that load is only set active when text is present in the "txtPLPath"

		final Runnable target3 = new Runnable()
		{

			public void run()
		    {
				while (true)
				{
					final ActionListener[] listeners = txtPLPath.getActionListeners();
		            	for (ActionListener listener : listeners)
		                {
		            		if (txtPLPath.getText().trim().length() > 0)
		            		{
		            			final ActionEvent event = new ActionEvent(txtPLPath, 1, "Enable");
		                        listener.actionPerformed(event);
		                    }
		                    else
		                    {
		                    	final ActionEvent event = new ActionEvent(txtPLPath, 1, "Disable");
		                        listener.actionPerformed(event);
		                    }
		                }
				}
		    }
		};

		new Thread(target3).start();
		txtPLPath.addActionListener(actionListener3);
		
		// Load button action for the prerankedlist --> adds the name of the file to the created JList for display        
		loadPL.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				preRankedFileName= preRankedList.getName();
				((DefaultListModel)listLoadedFileNames.getModel()).addElement(preRankedFileName);
				doneLoad.setEnabled(true);
				rdbtnUseExpressionDataset.setEnabled(false);
				preRanked = true;
				
				
			}
		});
		        
	
		
		// radiobutton action for selecting phenotype file --> enables "txtPFPath and browsePF, disables manualSelection
		rdbtnSelectPhentypeFile.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) 
        	{
        		browsePF.setEnabled(true);
        		txtPFPath.setEnabled(true);
        		manualSelection.setEnabled(false);
        	}
        });
		
		//Filechooser action for PhenotypeFile browse button
		browsePF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				phenotypeFile = chooser.getSelectedFile();
				String pathOfFile =phenotypeFile.getAbsolutePath();
				txtPFPath.setText(pathOfFile);
			}
		});
		
		
		
		
		//Actionlistener4  to ensure that load is only set active when text is present in the "txtPFPath"
		final ActionListener actionListener4 = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (e.getActionCommand().equalsIgnoreCase("Enable"))
                {
                	loadPF.setEnabled(true);
                }
                else if (e.getActionCommand().equalsIgnoreCase("Disable"))
                {
                	loadPF.setEnabled(false);
                }
            }
        };
        
        
        //Works together with "actionlistener4" created before  to ensure that load is only set active when text is present in the "txtPFPath"
        final Runnable target4 = new Runnable()
        {

            public void run()
            {
                while (true)
                {
                    final ActionListener[] listeners = txtPFPath.getActionListeners();
                    for (ActionListener listener : listeners)
                    {
                        if (txtPFPath.getText().trim().length() > 0)
                        {
                            final ActionEvent event = new ActionEvent(txtPFPath, 1, "Enable");
                            listener.actionPerformed(event);
                        }
                        else
                        {
                            final ActionEvent event = new ActionEvent(txtPFPath, 1, "Disable");
                            listener.actionPerformed(event);
                        }
                    }
                }
            }
        };

        new Thread(target4).start();
        txtPFPath.addActionListener(actionListener4);
		
		
     // Load button action for the Phenotype file --> adds the name of the file to the created JList for display
        loadPF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				phenotypeFileName = phenotypeFile.getName();
				((DefaultListModel)listLoadedFileNames.getModel()).addElement(phenotypeFileName);
				rdbtnSelectPhenotypeManually.setEnabled(false);
				doneLoad.setEnabled(true);
				
			}
		});
		
		
     // Radiobutton action for select phenotypes manually --> enables manual selection button, disables txtPFPath, loadPF and browsePF
		rdbtnSelectPhenotypeManually.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				loadPF.setEnabled(false);
				browsePF.setEnabled(false);
				txtPFPath.setEnabled(false);
				manualSelection.setEnabled(true);
				
			}
		});
		
		manualSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				ManualSelectionPage manual = new ManualSelectionPage();
				manual.run();
			}
		});
		
		
	//done button  
		doneLoad.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				MainFrameGSEA.enableSetButton();
				MainFrameGSEA.colorLoadButtonGreen();
				MainFrameGSEA.disableLoadButton();
				dispose();
				
				////Methods m = new Methods();
				
				//m.reader2(geneSet);
			}
		});
		
	}
	
	//Methods
	
	
	public List<String> openPathway(File gpmlFile, IDMapper sgdb, DataSource targetDs) throws ParseException, SAXException, IDMapperException, IOException{
		List<String> geneSet = new ArrayList<String>();
		try
		{
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			PathwayParser pwyParser = new PathwayParser(gpmlFile, xmlReader);
			for (XrefWithSymbol ref : pwyParser.getGenes()) 
			{
				for (Xref dest : sgdb.mapID(ref.asXref(), targetDs)) {
					geneSet.add(dest.getId());
				}
			}
		}
		catch(ParseException ex) {
			JOptionPane.showMessageDialog(null, "Error: Enable to open pathway " + ex.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return geneSet;
	}
	
	/*public List<String> readExDS()
	{
		File inputWorkbook = dataSetFile;
	    Workbook w;
        Cell cell;
        List<String> geneNames1 = new ArrayList<String>();
       // String[] geneNames1 = null;
       
	    try 
	    {
	        w = Workbook.getWorkbook(inputWorkbook);
	        // Get the first sheet
	        Sheet sheet = w.getSheet(0);
	        // Loop over first 10 column and lines
	        
	       
	        int j ;
	        for (j = 2; j < sheet.getColumns(); j++) 
	        {
	        	cell = sheet.getCell(j, 2);
	        	CellType type = cell.getType();
	        	System.out.println("I got a label "+ cell.getContents());
	        	
	        	namesTemp.add(cell.getContents());
	        	double[] arrayED = new double[sheet.getRows()-2];

	            
	            	for (int i = 3; i < sheet.getRows(); i++)
	            {
	                
	                Cell cell1 = sheet.getCell(j, i);
		        	CellType type1 = cell1.getType();
		        	double value = Double.parseDouble(cell1.getContents());
		        	arrayED[i-3] = value;
		        	
	                
	            }
	            	map.put(cell.getContents(),arrayED);
	            	
                
	        }
	        
	        mylist.add(map);
	       
	        
	        for (int i = 3; i < sheet.getRows(); i++)
	        {
	        Cell cell2 = sheet.getCell(0, i);
	        CellType type1 = cell2.getType();
	        geneNames1.add(cell2.getContents());
	        //Arrays.fill(geneNames1, i-3, sheet.getRows()-3, cell2.getContents());
	        }
	        
	        
	         
	    } 
	    
	    catch (BiffException e1)
	    {
	        e1.printStackTrace();
	    } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    return geneNames1;
	    
	}*/

}
