

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

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
* @ Jesse
* 
*   This class represents the SetConfigurationsPage where the number of permutations, 
*   permutation type and the type of ranking can be set. 
* 
*/

public class SetConfigPage extends JFrame {

	private JPanel contentPane;
	private JTextField title;
	private JTextField txtNumberOfPermutations;
	private JTextField txtPermutationType;
	private JTextField permutationNumberInput;
	private JSeparator separator_1;
	
	private static JTextField txtRankingMetric;
	private static JComboBox<String> rankingCombobox;

	/**
	 * Launch the application.
	 */
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetConfigPage frame = new SetConfigPage();
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
	public SetConfigPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Create the title
		title = new JTextField();
		title.setText("Set Configurations Page");
		title.setBounds(130, 0, 193, 22);
		contentPane.add(title);
		title.setColumns(10);
		title.setEditable(false);
		
		//Create text fields
		txtNumberOfPermutations = new JTextField();
		txtNumberOfPermutations.setText("Number of permutations");
		txtNumberOfPermutations.setBounds(12, 41, 169, 22);
		contentPane.add(txtNumberOfPermutations);
		txtNumberOfPermutations.setColumns(10);
		txtNumberOfPermutations.setEditable(false);
		
		txtPermutationType = new JTextField();
		txtPermutationType.setText("Permutation Type");
		txtPermutationType.setBounds(12, 90, 131, 22);
		contentPane.add(txtPermutationType);
		txtPermutationType.setColumns(10);
		txtPermutationType.setEditable(false);
		
		txtRankingMetric = new JTextField();
		txtRankingMetric.setText("Ranking Metric");
		txtRankingMetric.setBounds(12, 172, 116, 22);
		contentPane.add(txtRankingMetric);
		txtRankingMetric.setColumns(10);
		txtRankingMetric.setEditable(false);
		
		permutationNumberInput = new JTextField();
		permutationNumberInput.setBounds(207, 41, 72, 22);
		contentPane.add(permutationNumberInput);
		permutationNumberInput.setColumns(10);
		
		//Create combobox for permutation types
		JComboBox<String> permutationCombobox = new JComboBox<String>();
		permutationCombobox.setBounds(207, 90, 152, 22);
		permutationCombobox.addItem("Permute on phenotypes");
		permutationCombobox.addItem("Permute on genes");
		contentPane.add(permutationCombobox);
		
		//Create combobox for ranking type
		rankingCombobox = new JComboBox<String>();
		rankingCombobox.setBounds(207, 172, 152, 22);
		rankingCombobox.addItem("SNR");
		contentPane.add(rankingCombobox);
		
		//Create separator lines
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 157, 432, 2);
		contentPane.add(separator);
		
		
		separator_1 = new JSeparator();
		separator_1.setBounds(0, 284, 432, 2);
		contentPane.add(separator_1);
		
		//Create done button
		JButton done = new JButton("Done");
		done.setBounds(167, 345, 97, 25);
		contentPane.add(done);
		
		//Uses MyIntFilter Subclass on the permutation input box 
		//to ensure that only numbers can be entered there
		PlainDocument doc = (PlainDocument) permutationNumberInput.getDocument();
	      doc.setDocumentFilter(new MyIntFilter());
	      
	      if (LoadDataPage.preRanked)
	      {
	    	  txtRankingMetric.setEnabled(false);
		  		rankingCombobox.setEnabled(false);
	      }
	  
	    	  
	      
	      	//From here on are actionlisteners
	      	//Actionlistener  to ensure that done button is only set active when text is present in
	      	// the permutation input box
			final ActionListener actionListener = new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	                if (e.getActionCommand().equalsIgnoreCase("Enable"))
	                {
	                	done.setEnabled(true);
	                }
	                else if (e.getActionCommand().equalsIgnoreCase("Disable"))
	                {
	                	done.setEnabled(false);
	                }
	            }
	        };
	        
	        //Works together with "actionlistener" created before  to ensure that done buttton is only set 
	        //active when text is present in
	      	// the permutation input box

	        final Runnable target = new Runnable()
	        {

	            public void run()
	            {
	                while (true)
	                {
	                    final ActionListener[] listeners = permutationNumberInput.getActionListeners();
	                    for (ActionListener listener : listeners)
	                    {
	                        if (permutationNumberInput.getText().trim().length() > 0)
	                        {
	                            final ActionEvent event = new ActionEvent(permutationNumberInput, 1, "Enable");
	                            listener.actionPerformed(event);
	                        }
	                        else
	                        {
	                            final ActionEvent event = new ActionEvent(permutationNumberInput, 1, "Disable");
	                            listener.actionPerformed(event);
	                        }
	                    }
	                }
	            }
	        };

	        new Thread(target).start();
	        permutationNumberInput.addActionListener(actionListener);
	      
	      //Updates buttons in the MainFrame  
	      done.addActionListener(new ActionListener() 
	      {
				public void actionPerformed(ActionEvent arg0) 
				{
					MainFrameGSEA.disableSetButton();
					MainFrameGSEA.colorSetButtonGreen();
					MainFrameGSEA.enableRunButton();
					dispose();
				}
	      });
	}
	
	
}

//Subclass that is used to make textfields sensitive for numbers only
class MyIntFilter extends DocumentFilter {
	   @Override
	   public void insertString(FilterBypass fb, int offset, String string,
	         AttributeSet attr) throws BadLocationException {

	      Document doc = fb.getDocument();
	      StringBuilder sb = new StringBuilder();
	      sb.append(doc.getText(0, doc.getLength()));
	      sb.insert(offset, string);

	      if (test(sb.toString()))
	      {
	         super.insertString(fb, offset, string, attr);
	      } 
	      else 
	      {
	         // warn the user and don't allow the insert
	      }
	   }

	   private boolean test(String text) {
	      try {
	         Integer.parseInt(text);
	         return true;
	      } catch (NumberFormatException e) {
	         return false;
	      }
	   }

	   @Override
	   public void replace(FilterBypass fb, int offset, int length, String text,
	         AttributeSet attrs) throws BadLocationException {

	      Document doc = fb.getDocument();
	      StringBuilder sb = new StringBuilder();
	      sb.append(doc.getText(0, doc.getLength()));
	      sb.replace(offset, offset + length, text);

	      if (test(sb.toString()))
	      {
	         super.replace(fb, offset, length, text, attrs);
	      } 
	      else 
	      {
	         // warn the user and don't allow the insert
	      }

	   }

	   @Override
	   public void remove(FilterBypass fb, int offset, int length)
	         throws BadLocationException {
	      Document doc = fb.getDocument();
	      StringBuilder sb = new StringBuilder();
	      sb.append(doc.getText(0, doc.getLength()));
	      sb.delete(offset, offset + length);

	      if(sb.toString().length() == 0) { super.replace(fb, offset, length, "", null); }
	      else 
	      {
	    	  if (test(sb.toString()))
	    	  {
	    		  super.remove(fb, offset, length);
	    	  } 
	    	  else 
	    	  {
	         // warn the user and don't allow the insert
	    	  }
	      }

	   }
	}
