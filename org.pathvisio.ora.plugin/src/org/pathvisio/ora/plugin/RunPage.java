package org.pathvisio.ora.plugin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLDocument.Iterator;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class RunPage extends JFrame {

	private JPanel contentPane;
	
	public static double[] meansPhen1Group;
	public static double[] meansPhen2Group;
	public static double[] sdPhen1Group;
	public static double[] sdPhen2Group;
	public static HashMap<String, Double> mapNamesSNR = new HashMap<String, Double>();
	public static LinkedHashMap<String, Double> sortmapNamesSNR = new LinkedHashMap<String, Double>();
	
	
	/**
	 * Launch the application.
	 */
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RunPage frame = new RunPage();
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
	public RunPage() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 671, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(0, 376, 653, 14);
		contentPane.add(progressBar);
		progressBar.setStringPainted(true);
		
		JButton btnShowResults = new JButton("Show results");
		btnShowResults.setBounds(263, 387, 131, 25);
		contentPane.add(btnShowResults);
		btnShowResults.setEnabled(false);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 0, 653, 374);
		contentPane.add(textArea);
		
		
		int intPercent = 0;
		progressBar.setValue(intPercent);
		textArea.setText("Calculating mean Phenotype 1 group");
	
		meansPhen1Group = calculateMean(ManualSelectionPage.phen1Group, LoadDataPage.map);
		intPercent = 50;
		progressBar.setValue(intPercent);
		textArea.setText("Calculating mean Phenotype 1 group..." + "\n" + "Done" 
				+ "\n" + "Calculating mean Phenotype 2 group...");
		meansPhen2Group = calculateMean(ManualSelectionPage.phen2Group, LoadDataPage.map);
		intPercent = 100;
		progressBar.setValue(intPercent);
		textArea.setText("Calculating mean Phenotype 1 group..." + "\n" + "Done" 
				+ "\n" + "Calculating mean Phenotype 2 group..."+ "\n" + "Done" 
				+ "Calculating sd Phenotype 1 group...");
		
		sdPhen1Group = calculateSD(ManualSelectionPage.phen1Group, LoadDataPage.map,meansPhen1Group);
		intPercent = 50;
		progressBar.setValue(intPercent);
		textArea.setText("Calculating mean Phenotype 1 group..." + "\n" + "Done" 
				+ "\n" + "Calculating mean Phenotype 2 group..."+ "\n" + "Done" 
				+ "Calculating sd Phenotype 1 group..." + "\n" + "Done"
				+ "Calculating sd Phenotype 2 group...");
		sdPhen2Group = calculateSD(ManualSelectionPage.phen2Group, LoadDataPage.map,meansPhen2Group);
		intPercent = 100;
		progressBar.setValue(intPercent);
		textArea.setText("Calculating mean Phenotype 1 group..." + "\n" + "Done" 
				+ "\n" + "Calculating mean Phenotype 2 group..."+ "\n" + "Done" 
				+ "Calculating sd Phenotype 1 group..." + "\n" + "Done"
				+ "Calculating sd Phenotype 2 group..." + "\n" + "Done");
		
		
		mapNamesSNR = calculateSNR(meansPhen1Group,meansPhen2Group,sdPhen1Group,sdPhen2Group);
		sortmapNamesSNR = sortMap(mapNamesSNR);
		
		
	}
	public double[] calculateMean(List<String> l,HashMap<String, double[]> map)
	{
		double sum = 0;
		double mean= 0;
		double[] values = new double [map.get(l.get(0)).length];
		double[] meanValues = new double [map.get(l.get(0)).length];
		for(int i=0; i< map.get(l.get(0)).length; i++)
		{
			for (int k=0; k<l.size();k++)
			{
				
				values = map.get(l.get(k));
				sum = sum + values[i];
			}
			
			mean = sum / l.size();
			Arrays.fill(meanValues, i, map.get(l.get(0)).length-1, mean); 
			sum = 0;
		}
		/*for (int f=0; f<3;f++)
		{
		System.out.println(meanValues[f]);
		}
		System.out.println(meanValues.length);*/
		
		return meanValues;
	}
	public double[] calculateSD(List<String> l,HashMap<String, double[]> map, double [] mean)
	{
		double sum = 0;
		double test = 0;
		double difference = 0;
		double diffSquare = 0;
		double sumDiv = 0;
		double sd= 0;
		double[] values = new double [map.get(l.get(0)).length];
		double[] standardDev = new double [map.get(l.get(0)).length];
		
		for(int i=0; i< map.get(l.get(0)).length; i++)
		{
			for(int k=0; k< l.size(); k++)
			{
				values = map.get(l.get(k));
				difference = values[i] - mean[i];
				diffSquare = Math.pow(difference,2);
				sum = sum + diffSquare;
			}
			sumDiv = sum / l.size();
			sd = Math.sqrt(sumDiv);
			Arrays.fill(standardDev, i, map.get(l.get(0)).length-1, sd);	
			sum = 0;
		}
		
	/*	for (int f=0; f<3;f++)
		{
		System.out.println(standardDev[f]);
		}
		System.out.println(standardDev.length);*/
		return standardDev;
	}
	
	public HashMap<String, Double> calculateSNR(double[] mean1, double[]mean2, double[]sd1,double[]sd2)
	{
		double[] signalNRs = new double [mean1.length];
		double snr = 0;
		HashMap<String, Double> mapNamesSNR1 = new HashMap<String, Double>();
		
		for(int i=0; i<mean1.length-1; i++)
		{
			snr = (mean1[i]-mean2[i])/(sd1[i]-sd2[i]);
			mapNamesSNR1.put(LoadDataPage.geneNames.get(i),snr);
			//Arrays.fill(signalNRs, i, mean1.length-1, snr);
			
		}
		
		
		
		/*for (int f=0; f<3;f++)
		{
		System.out.println(signalNRs[f]);
		}
		
		Arrays.sort(signalNRs);
		
		for(int i = 0; i < signalNRs.length / 2; i++)
		{
		    double temp = signalNRs[i];
		    signalNRs[i] = signalNRs[signalNRs.length - i - 1];
		    signalNRs[signalNRs.length - i - 1] = temp;
		}
		
		for (int f=0; f<2000;f++)
		{
		System.out.println(signalNRs[f]);
		}*/
		
		
		return mapNamesSNR1;
	}
	
	public LinkedHashMap<String, Double> sortMap(HashMap<String, Double> passedMap)
	{
	    List<String> mapKeys = new ArrayList<String>(passedMap.keySet());
	    List<Double> mapValues = new ArrayList<Double>(passedMap.values());
	    Collections.sort(mapValues);
	    Collections.sort(mapKeys);
	    int y= 0;

	    LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();
	    
	    java.util.Iterator<Double> valueIt = mapValues.iterator();
	    while (valueIt.hasNext()) {
	        Double val = valueIt.next();
	        java.util.Iterator<String> keyIt = mapKeys.iterator();

	        while (keyIt.hasNext()) {
	            String key = keyIt.next();
	            Double comp1 = passedMap.get(key);
	            Double comp2 = val;

	            if (comp1.equals(comp2)) {
	                keyIt.remove();
	                sortedMap.put(key, val);
	                if (y<=5)
	                {
	                	System.out.println(key + val);
	                	y++;
	                }
	                break;
	            }
	        }
	    }
	    
	 
	    
	    return sortedMap;

	}
	
}


