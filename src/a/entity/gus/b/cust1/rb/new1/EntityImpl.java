package a.entity.gus.b.cust1.rb.new1;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210826";}
	
	
	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		String info = (String) data[1];
		Class c = Class.forName(info);
		return c.newInstance();
	}
}
