package a.entity.gus.b.entitysrc2.engine.entityholder;

import java.io.File;
import java.sql.Connection;
import java.util.List;
import java.util.Set;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.R;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210926";}

	private Service findPackageDir;
	private Service findJavaFiles;
	private Service findDownLinks;
	private Service findUpLinks;
	private Service findServices;
	private Service findResources;
	private Service findErrors;

	public EntityImpl() throws Exception
	{
		findPackageDir = Outside.service(this,"gus.a.entity.src.find.packagedir");
		findJavaFiles = Outside.service(this,"gus.a.dir.listing0.files.java");
		findDownLinks = Outside.service(this,"gus.b.entitysrc2.database.entity_link.find2");
		findUpLinks = Outside.service(this,"gus.b.entitysrc2.database.entity_link.find1");
		findServices = Outside.service(this,"gus.b.entitysrc2.database.entity_service.find");
		findResources = Outside.service(this,"gus.b.entitysrc2.database.entity_resource.find");
		findErrors = Outside.service(this,"gus.b.entitysrc2.database.entity_compile_err.find");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String path = (String) o[0];
		Object engine = o[1];
		
		return new Holder(path, engine);
	}
	
	
	private class Holder implements R
	{
		private String path;
		private String entityName;
		private String anchor;
		private Object engine;
		
		private File packageDir;
		private File[] javaFiles;
		private Set downLinks;
		private Set upLinks;
		private Set services;
		private Set resources;
		private List errors;
		
		public Holder(String path, Object engine)
		{
			this.path = path;
			this.engine = engine;
			
			String[] n = path.split("@",2);
			entityName = n[0];
			anchor = n.length==2 ? n[1] : null;
		}

		public Object r(String key) throws Exception
		{
			if(key.equals("path")) return path;
			if(key.equals("entityName")) return entityName;
			if(key.equals("anchor")) return anchor;
			if(key.equals("engine")) return engine;
			
			if(key.equals("cx")) return cx();
			if(key.equals("rootDir")) return rootDir();
			
			if(key.equals("packageDir")) return packageDir();
			if(key.equals("javaFiles")) return javaFiles();
			
			if(key.equals("downLinks")) return downLinks();
			if(key.equals("upLinks")) return upLinks();
			
			if(key.equals("services")) return services();
			if(key.equals("resources")) return resources();
			if(key.equals("errors")) return errors();
			
			if(key.equals("keys")) return new String[] {
					"path","entityName","anchor","engine",
					"cx", "rootDir", 
					"packageDir", "javaFiles", 
					"downLinks", "upLinks", 
					"services", "resources", "errors"};
			throw new Exception("");
		}
		
		
		private File rootDir() throws Exception
		{
			return (File) ((R) engine).r("rootDir");
		}
		
		private Connection cx() throws Exception
		{
			return  (Connection) ((R) engine).r("cx");
		}
		
		private File packageDir() throws Exception
		{
			if(packageDir==null) packageDir = (File) findPackageDir.t(new Object[]{rootDir(), entityName});
			return packageDir;
		}
		
		private File[] javaFiles() throws Exception
		{
			if(javaFiles==null) javaFiles = (File[]) findJavaFiles.t(packageDir());
			return javaFiles;
		}
		
		private Set downLinks()  throws Exception
		{
			if(downLinks==null) downLinks = (Set) findDownLinks.t(new Object[] {cx(), entityName});
			return downLinks;
		}
		
		private Set upLinks()  throws Exception
		{
			if(upLinks==null) upLinks = (Set) findUpLinks.t(new Object[] {cx(), entityName});
			return upLinks;
		}
		
		private Set services()  throws Exception
		{
			if(services==null) services = (Set) findServices.t(new Object[] {cx(), entityName});
			return services;
		}
		
		private Set resources()  throws Exception
		{
			if(resources==null) resources = (Set) findResources.t(new Object[] {cx(), entityName});
			return resources;
		}
		
		private List errors()  throws Exception
		{
			if(errors==null) errors = (List) findErrors.t(new Object[] {cx(), entityName});
			return errors;
		}
	}
}
