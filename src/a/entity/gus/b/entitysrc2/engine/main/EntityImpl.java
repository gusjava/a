package a.entity.gus.b.entitysrc2.engine.main;

import java.io.File;
import java.sql.Connection;
import java.util.Map;

import a.framework.E;
import a.framework.Entity;
import a.framework.F;
import a.framework.G;
import a.framework.Outside;
import a.framework.R;
import a.framework.S1;
import a.framework.Service;
import a.framework.V;

public class EntityImpl extends S1 implements Entity, G, R, V, E, F {
	public String creationDate() {return "20210829";}
	
	public static final String PERSIST_KEY = EntityImpl.class.getName()+"_last";

	
	private Service dataLoader;
	private Service getConnection;
	private Service getRootDir;
	private Service findErrors;
	private Service persist;
	private String devId;
	
	private Object selected;
	private Object added;
	private Object renamed;
	private Object duplicated;
	private Object deleted;
	private Object modified;
	
	private Long lastTime;
	private Map data;
	private Map errors;
	
	

	public EntityImpl() throws Exception
	{
		dataLoader = Outside.service(this,"gus.b.entitysrc2.engine.dataloader");
		getConnection = Outside.service(this,"gus.b.entitysrc2.database.cx.main");
		getRootDir = Outside.service(this,"gus.b.entitysrc1.rootdir");
		findErrors = Outside.service(this,"gus.b.entitysrc2.database.entity_compile_err.findall.asmap");
		persist = Outside.service(this,"gus.b.persist1.main");
		
		devId = (String) Outside.resource(this, "param#dev");
		if(devId==null) throw new Exception("dev not found inside params");
		
		load();
	}
	
	
	
	public void e() throws Exception
	{
		load();
		changed();
	}
	
	
	
	public Object g() throws Exception
	{return data;}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("rootDir")) return getRootDir();
		if(key.equals("binDir")) return getBinDir();
		if(key.equals("libDir")) return getLibDir();
		
		if(key.equals("devId")) return devId;
		if(key.equals("errors")) return errors;
		if(key.equals("lastTime")) return lastTime;
		if(key.equals("cx")) return getConnection();
		
		if(key.equals("selected")) return selected;
		if(key.equals("added")) return added;
		if(key.equals("renamed")) return renamed;
		if(key.equals("duplicated")) return duplicated;
		if(key.equals("deleted")) return deleted;
		if(key.equals("modified")) return modified;
		
		if(key.equals("keys")) return new String[] {
				"rootDir","binDir","libDir",
				"devId","lastTime","errors","cx",
				"selected","added","renamed","duplicated","deleted","modified"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("select")) {selectEntity(obj);return;}
		if(key.equals("added")) {entityAdded(obj);return;}
		if(key.equals("renamed")) {entityRenamed(obj);return;}
		if(key.equals("duplicated")) {entityDuplicated(obj);return;}
		if(key.equals("deleted")) {entityDeleted(obj);return;}
		if(key.equals("modified")) {entityModified(obj);return;}
		if(key.equals("modifiedAndSelected")) {entityModifiedAndSelected(obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}

	
	

	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String permission = (String) o[0];
		if(permission.equals("canDeleteEntity")) return canDeleteEntity((String) o[1]);
		if(permission.equals("canRenameEntity")) return canRenameEntity((String) o[1]);
		if(permission.equals("canDuplicateEntity")) return canDuplicateEntity((String) o[1]);
		if(permission.equals("canModifyEntity")) return canModifyEntity((String) o[1]);
		
		throw new Exception("Unknown permission: "+permission);
	}
	
	
	
	
	
	/*
	 * LOAD
	 */
		
	private void load() throws Exception
	{
		lastTime = findLastTime();
		data = (Map) dataLoader.t(this);
		errors = (Map) findErrors.t(getConnection());
		persistLastTime();
	}
	
	
	/*
	 * LAST TIME
	 */
	
	private long findLastTime() throws Exception
	{
		String str = (String) persist.r(PERSIST_KEY);
		return str!=null ? Long.parseLong(str) : 0L;
	}
	
	private void persistLastTime() throws Exception
	{
		persist.v(PERSIST_KEY, ""+System.currentTimeMillis());
	}
	
	
	
	
	
	
	private File getRootDir() throws Exception
	{return (File) getRootDir.g();}
	
	
	
	private Connection getConnection() throws Exception
	{return (Connection) getConnection.g();}
	
	
	
	private File getBinDir() throws Exception
	{
		File rootDir = (File) getRootDir.g();
		return new File(rootDir.getParentFile(), "bin");
	}
	
	private File getLibDir() throws Exception
	{
		File rootDir = (File) getRootDir.g();
		return new File(rootDir.getParentFile(), "lib");
	}
	
	
	
	
	
	
	private boolean canDeleteEntity(String entityName)
	{
		return isMyEntity(entityName);
	}
	
	private boolean canRenameEntity(String entityName)
	{
		return isMyEntity(entityName);
	}
	
	private boolean canDuplicateEntity(String entityName)
	{
		return true;
	}
	
	private boolean canModifyEntity(String entityName)
	{
		return isMyEntity(entityName);
	}
	
	private boolean isMyEntity(String entityName)
	{
		return entityName!=null && entityName.startsWith(devId+".");
	}
	
	
	
	
	
	private void selectEntity(Object selected)
	{
		this.selected = selected;
		
		selected();
	}
	
	
	private void entityAdded(Object added) throws Exception
	{
		this.added = added;
		this.selected = added;
		
		load();
		changedAndSelected();
	}
	
	
	private void entityRenamed(Object renamed) throws Exception
	{
		this.renamed = renamed;
		this.selected = ((String[]) renamed)[1];
		
		load();
		changedAndSelected();
	}
	
	
	private void entityDuplicated(Object duplicated) throws Exception
	{
		this.duplicated = duplicated;
		this.selected = ((String[]) duplicated)[1];
		
		load();
		changedAndSelected();
	}
	
	
	private void entityDeleted(Object deleted) throws Exception
	{
		this.deleted = deleted;
		
		load();
		changed();
	}
	
	
	private void entityModified(Object modified) throws Exception
	{
		this.modified = modified;
		this.selected = modified;
		
		load();
		changedAndSelected();
	}
	
	private void entityModifiedAndSelected(Object info) throws Exception
	{
		this.modified = ((String[]) info)[0];
		this.selected = ((String[]) info)[1];
		
		load();
		changedAndSelected();
	}
	
	
	
	
	/*
	 * EVENTS
	 */
	
	private void changed()
	{send(this,"changed()");}
	
	private void selected()
	{send(this,"selected()");}
	
	private void changedAndSelected()
	{send(this,"changedAndSelected()");}
}
