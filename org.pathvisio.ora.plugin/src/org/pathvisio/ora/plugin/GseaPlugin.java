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

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.pathvisio.data.DataInterface;
import org.pathvisio.desktop.PvDesktop;
import org.pathvisio.desktop.plugin.Plugin;

public class GseaPlugin implements Plugin, BundleActivator
{
	private PvDesktop desktop;

	public void done()
	{
		desktop.unregisterMenuAction("Data", oraWizardAction);
	}

	public void init(PvDesktop desktop)
	{
		this.desktop = desktop;
		oraWizardAction = new OraWizardAction(desktop);		
		desktop.registerMenuAction ("Data", oraWizardAction);	
	}

	private OraWizardAction oraWizardAction;

	public static class OraWizardAction extends AbstractAction
	{
		private final PvDesktop desktop;

		public OraWizardAction(PvDesktop pvDesktop)
		{
			this.desktop = pvDesktop;
			putValue (NAME, "Gene Set Enrichment Analysis");
			putValue (SHORT_DESCRIPTION, "Perform simple over-representation analysis to find changed pathways.");
		}

		public void actionPerformed (ActionEvent e)
		{
			//DataInterface gex = desktop.getGexManager().getCurrentGex();
			//if (gex == null)
			//{
			//	JOptionPane.showMessageDialog(desktop.getFrame(), "Select an expression dataset first");
			//}
			//else
			
			MainFrameGSEA bf = new MainFrameGSEA();
			bf.run() ;
		
		}
	}

	@Override
	public void start(BundleContext context) throws Exception {
		GseaPlugin plugin = new GseaPlugin();
		context.registerService(Plugin.class.getName(), plugin, null);	
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		desktop.unregisterMenuAction ("Data", oraWizardAction);	
	}
}
