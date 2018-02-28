
//GSEA plugin for PathVisio,

//Gene set enrichment plugin 

//Copyright 2017 BiGCaT Bioinformatics

//

//Licensed under the Apache License, Version 2.0 (the "License");

//you may not use this file except in compliance with the License.

//You may obtain a copy of the License at

//
//http://www.apache.org/licenses/LICENSE-2.0
//

//Unless required by applicable law or agreed to in writing, software

//distributed under the License is distributed on an "AS IS" BASIS,

//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.

//See the License for the specific language governing permissions and

//limitations under the License.

package org.pathvisio.ora.plugin;


import java.awt.EventQueue;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.Shape;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollBar;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

/**
* @ Jesse
* 
*   This class represents the ManualSelection box that is used to select phenotypes manually.
* 
*/
public class ManualSelectionPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private JPanel contentPane;
	private JTextField txtManualPhenotypeSelection;
	
	
	public static File GeneSetFinal;
	public static String manualSelectText = new String("'Phenotypes are manually selected'");
	public static List<String> phen1Group = new ArrayList<String>();
	public static List<String> phen2Group = new ArrayList<String>();
	
	
	/**
	 * Launch the application.
	 */
	
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ManualSelectionPage frame = new ManualSelectionPage();
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
	public ManualSelectionPage() {
		
		
		setBounds(100, 100, 585, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtManualPhenotypeSelection = new JTextField();
		txtManualPhenotypeSelection.setBounds(185, 0, 175, 22);
		txtManualPhenotypeSelection.setBackground(Color.GRAY);
		txtManualPhenotypeSelection.setText("Manual Phenotype Selection");
		contentPane.add(txtManualPhenotypeSelection);
		txtManualPhenotypeSelection.setColumns(10);
		
		JButton btnAdd = new JButton("Add1");
		btnAdd.setBounds(227, 106, 97, 25);
		contentPane.add(btnAdd);
		btnAdd.setEnabled(false);
		
		
		JButton btnRemove = new JButton("Remove1");
		btnRemove.setBounds(227, 144, 97, 25);
		contentPane.add(btnRemove);
		btnRemove.setEnabled(false);
		
		JButton btnAdd_1 = new JButton("Add2");
		btnAdd_1.setBounds(227, 272, 97, 25);
		contentPane.add(btnAdd_1);
		btnAdd_1.setEnabled(false);
		
		JButton btnRemove_1 = new JButton("Remove2");
		btnRemove_1.setBounds(227, 310, 97, 25);
		contentPane.add(btnRemove_1);
		btnRemove_1.setEnabled(false);
		
		JButton btnDone = new JButton("Done");
		btnDone.setBounds(284, 450, 97, 25);
		contentPane.add(btnDone);
		btnDone.setEnabled(false);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setBounds(175, 450, 97, 25);
		contentPane.add(btnLoad);
		btnLoad.setEnabled(true);
		
		final DefaultListModel parts = new DefaultListModel();
		//final JList<String> list_3 = new JList<String>(new DefaultListModel<String>());
		final JList list_3 = new JList(parts);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 46, 155, 356);
		scrollPane.setViewportView(list_3);
		contentPane.add(scrollPane);
		
		final DefaultListModel partSelected1 = new DefaultListModel();
		

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(353, 46, 169, 169);
		contentPane.add(scrollPane_2);
		final JList list_1 = new JList(partSelected1);
		scrollPane_2.setViewportView(list_1);
		
		final DefaultListModel partSelected2 = new DefaultListModel();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(353, 238, 168, 164);
		contentPane.add(scrollPane_1);
		final JList list_2 = new JList(partSelected2);
		scrollPane_1.setViewportView(list_2);
		
		List<String> NamesTemp1 = LoadDataPage.namesTemp;
		
		for(String s : NamesTemp1)
		{
			parts.addElement(s);
		}
		
		
		list_1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) 
			{
				btnAdd.setEnabled(false);
				btnAdd_1.setEnabled(false);
				btnRemove.setEnabled(true);
				
			}
		});
		
		
		list_2.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) 
			{
				btnAdd.setEnabled(false);
				btnAdd_1.setEnabled(false);
				btnRemove_1.setEnabled(true);
			}
		});
		
		
		list_3.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) 
			{
				btnAdd.setEnabled(true);
				btnAdd_1.setEnabled(true);
				btnRemove.setEnabled(false);
				btnRemove_1.setEnabled(false);
				
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				
				for (Object selectedValue : list_3.getSelectedValuesList()) 
				{
	                partSelected1.addElement(selectedValue);
	                parts.removeElement(selectedValue);
	                int iSelected = list_3.getSelectedIndex();
	                if (iSelected == -1) 
	                {
	                    return;
	                }
	            }
			}
		});
		
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				for (Object selectedValue : list_3.getSelectedValuesList()) 
				{
	                partSelected2.addElement(selectedValue);
	                parts.removeElement(selectedValue);
	                int iSelected = list_3.getSelectedIndex();
	                if (iSelected == -1) 
	                {
	                    return;
	                }
	            }

			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
		          for (Object selectedValue : list_1.getSelectedValuesList()) 
		          	{
		                parts.addElement(selectedValue);
		                partSelected1.removeElement(selectedValue);
		                int selected = list_1.getSelectedIndex();
		                if (selected == -1) {
		                    return;
		                }
		            }
		          
			}
		});
		
		btnRemove_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				for (Object selectedValue : list_2.getSelectedValuesList())
				{
	                parts.addElement(selectedValue);
	                partSelected2.removeElement(selectedValue);
	                int selected = list_2.getSelectedIndex();
	                if (selected == -1) {
	                    return;
	                }
	            }
				
			}
		});
		
	
		btnLoad.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(list_1.getModel().getSize() == 0||list_2.getModel().getSize() == 0)
				{
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Make sure both phenotype lists on the right are not empty");
				}
				else
				{
					
					for (int i = 0; i < list_1.getModel().getSize(); i++) 
					{
						
					    phen1Group.add((String) list_1.getModel().getElementAt(i));
					    
					    
					}
					
					  for(String array : phen1Group)
					    {
					        System.out.println(array);
					    }
					
					
					
					
					for (int i = 0; i < list_2.getModel().getSize(); i++) 
					{
						phen2Group.add((String) list_2.getModel().getElementAt(i));  
					}
					
					  for(String array1 : phen2Group)
					    {
					        System.out.println(array1);
					    }
					  
					  btnLoad.setEnabled(false); 
					  btnDone.setEnabled(true); 
					
				}
			}
		});
		
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
			 
		        
				dispose();
				LoadDataPage.doneLoad.setEnabled(true);
				((DefaultListModel)LoadDataPage.listLoadedFileNames.getModel()).addElement(manualSelectText);
				
			}
		});
		
	
	
	}
}

