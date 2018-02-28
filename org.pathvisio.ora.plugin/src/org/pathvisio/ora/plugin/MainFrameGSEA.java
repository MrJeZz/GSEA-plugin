

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


import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import org.pathvisio.desktop.PvDesktop;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;

/**
 * @ Jesse
 * 
 * This is the main frame of the GSEA plug-in. 
 * It is an interactive frame and is the core frame of the program.
 * The user will return to this frame on several instances. 
 */


public class MainFrameGSEA {

	private JFrame frame;
	private JTextField txtGettingStarted;
	private static JButton btnLoadData;
	private static JButton btnSetRunConfiguration;
	private static JButton btnRun;
	private PvDesktop desktop;
	

	/**
	 * Launch the application.
	 */
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameGSEA window = new MainFrameGSEA();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrameGSEA() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Create the frame
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		//Create Title
		txtGettingStarted = new JTextField();
		txtGettingStarted.setText("Getting Started");
		txtGettingStarted.setBounds(170, 13, 94, 22);
		frame.getContentPane().add(txtGettingStarted);
		txtGettingStarted.setColumns(10);
		txtGettingStarted.setEditable(false);
		
		
		//Create "load" button, "configuration" button and "run" button
		//Set "run" button and "configuration" button on false 
		
		btnLoadData = new JButton("Load Data");
		btnLoadData.setBounds(105, 69, 94, 79);
		frame.getContentPane().add(btnLoadData);
		
		btnSetRunConfiguration = new JButton("Set run configuration");
		btnSetRunConfiguration.setBounds(239, 69, 94, 79);
		frame.getContentPane().add(btnSetRunConfiguration);
		btnSetRunConfiguration.setEnabled(false);
		
		btnRun = new JButton("Run");
		btnRun.setBounds(170, 188, 97, 25);
		frame.getContentPane().add(btnRun);
		btnRun.setEnabled(false);
		
		//From here on are the actionListeners
		
		//Opens the load data page when button is pressed.
		
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//JOptionPane.showMessageDialog(null,"Load data page");
				
				LoadDataPage loadData = new LoadDataPage(desktop);
				loadData.run();
			}
		});
		
		//Opens the SetConfigurationsPage when button is pressed.
		btnSetRunConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				SetConfigPage setConfig = new SetConfigPage();
				setConfig.run();
			}
		});
		
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				RunPage runPage = new RunPage();
				runPage.run();
			}
		});
	}
	
	public static void enableSetButton()
	{
		btnSetRunConfiguration.setEnabled(true);
	}
	
	public static void enableRunButton()
	{
		btnRun.setEnabled(true);
	}
	

	public static void colorLoadButtonGreen()
	{
		btnLoadData.setBackground(Color.GREEN);
	}
	public static void colorSetButtonGreen()
	{
		btnSetRunConfiguration.setBackground(Color.GREEN);
	}
	
	
	public static void disableLoadButton()
	{
		btnLoadData.setEnabled(false);
	}
	public static void disableSetButton()
	{
		btnSetRunConfiguration.setEnabled(false);
	}
	
	
}
