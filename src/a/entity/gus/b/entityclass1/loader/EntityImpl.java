package a.entity.gus.b.entityclass1.loader;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210820";}

	
	private Service nameToPath;
	private Service findCl;

	public EntityImpl() throws Exception
	{
		nameToPath = Outside.service(this,"m020.t.entity.nametopath");
		findCl = Outside.service(this,"gus.b.entityclass1.cl.find");
	}
	
	public Object t(Object obj) throws Exception
	{
		String entityName = (String) obj;
		String classPath = (String) nameToPath.t(entityName);
		
		ClassLoader cl = (ClassLoader) findCl.g();
		Class c = Class.forName(classPath, true, cl);
		ClassLoader cl1 = c.getClassLoader();
		
		return c;
	}
}
