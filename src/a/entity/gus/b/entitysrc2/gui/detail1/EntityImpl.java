package a.entity.gus.b.entitysrc2.gui.detail1;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.R;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210903";}

	private Service tabHolder;
	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;
	
	private JPanel panel;
	private JTabbedPane tab;
	private JLabel labelTitle;
	private Icon entityIcon;
	
	private Object holder;
	

	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this,"*gus.b.swing1.tabbedpane.holder1");
		gui1 = Outside.service(this,"*gus.b.entitysrc2.gui.detail1.src");
		gui2 = Outside.service(this,"*gus.b.entitysrc2.gui.detail1.infos");
		gui3 = Outside.service(this,"*gus.b.entitysrc2.gui.detail1.doc");
		gui4 = Outside.service(this,"*gus.b.entitysrc2.gui.detail1.err");
		
		entityIcon = (Icon) Outside.resource(this,"icon#ELEMENT_entity");
		
		labelTitle = new JLabel(" ");
		labelTitle.setBorder(BorderFactory.createRaisedBevelBorder());
		
		tabHolder.v("FILE_java#Sources", gui1);
		tabHolder.v("UTIL_infos#Infos", gui2);
		tabHolder.v("UTIL_doc#Doc", gui3);
		tabHolder.v("UTIL_error#Errors", gui4);
		
		tab = (JTabbedPane) tabHolder.i();
		
		panel = new JPanel(new BorderLayout());
		panel.add(labelTitle, BorderLayout.NORTH);
		panel.add(tab, BorderLayout.CENTER);
	}
	
	
	public void p(Object obj) throws Exception {
		if(obj==null) {reset();return;}
		
		holder = obj;
		
		String entityName = (String) ((R) holder).r("entityName");
		List errors = (List) ((R) holder).r("errors");
		
		int errorNumber = errors.size();
		String errorTitle = errorNumber==0 ? "Errors" : "Errors ("+errorNumber+")";
		tab.setTitleAt(3, errorTitle);
		
		labelTitle.setText(entityName);
		labelTitle.setIcon(entityIcon);
		
		gui1.p(holder);
		gui2.p(holder);
		gui3.p(holder);
		gui4.p(holder);
	}
	
	
	private void reset() throws Exception {
		holder = null;
		
		labelTitle.setText(" ");
		labelTitle.setIcon(null);
		
		gui1.p(null);
		gui2.p(null);
		gui3.p(null);
		gui4.p(null);
	}
	
	
	public Object i() throws Exception
	{return panel;}
}
