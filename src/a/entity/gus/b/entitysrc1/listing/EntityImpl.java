package a.entity.gus.b.entitysrc1.listing;

import java.io.File;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210817";}
	
	
	private Service buildFromJar;
	private Service buildFromDir;

	public EntityImpl() throws Exception {
		buildFromJar = Outside.service(this,"gus.b.entitysrc1.listing.jar");
		buildFromDir = Outside.service(this,"gus.b.entitysrc1.listing.dir");
	}
	
	public Object t(Object obj) throws Exception {
		File location = (File) obj;
		if(location==null) return null;
		
		if(location.isFile()) return buildFromJar.t(location);
		if(location.isDirectory()) return buildFromDir.t(location);
		return null;
	}
}
