package a.entity.gus.c.devtool1.maingui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210814";}


	private JPanel panel;
	
	public EntityImpl() throws Exception {
		panel = new JPanel(new BorderLayout());
	}
	
	
	public Object i() throws Exception {
		return panel;
	}
}
