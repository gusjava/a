package a.entity.gus.b.dataview1.feature.i;

import javax.swing.JLabel;

import a.framework.*;

public class EntityImpl implements Entity, G, P, I {
	public String creationDate() {return "20210811";}


	private JLabel label;
	
	private I data;

	public EntityImpl() throws Exception {
		label = new JLabel("PENDING ...");
	}
	
	
	public Object g() throws Exception {
		return data;
	}
	
	
	public void p(Object obj) throws Exception {
		data = (I) obj;
	}
	
	
	public Object i() throws Exception {
		return label;
	}
}
