package a.entity.gus.b.convert1.stringtoborder.lowered;

import javax.swing.BorderFactory;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}


	public Object t(Object obj) throws Exception
	{
		if(obj!=null) throw new Exception("Invalid rule for loweredbevelborder: "+obj);
		return BorderFactory.createLoweredBevelBorder();
	}
}