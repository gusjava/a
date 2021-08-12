package a.entity.gus.b.convert1.stringtoborder.bevel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}


	private Service stringToColor;

	public EntityImpl() throws Exception
	{stringToColor = Outside.service(this,"gus.b.convert1.stringtocolor");}
	

	public Object t(Object obj) throws Exception
	{return build((String) obj);}
	
	
	
	private Border build(String rule) throws Exception
	{
		if(rule==null) throw new Exception("No bevel border rule found");
		String[] n = rule.split(" ");

		if(n.length==1) return BorderFactory.createBevelBorder(int_(n[0]));
		if(n.length==3) return BorderFactory.createBevelBorder(int_(n[0]),color(n[1]),color(n[2]));
		if(n.length==5) return BorderFactory.createBevelBorder(int_(n[0]),color(n[1]),color(n[2]),color(n[3]),color(n[4]));
		
		throw new Exception("Invalid bevel border rule: "+rule);
	}
	
	
	private Color color(String s) throws Exception
	{return (Color) stringToColor.t(s);}
	
	private int int_(String s)
	{return Integer.parseInt(s);}
}