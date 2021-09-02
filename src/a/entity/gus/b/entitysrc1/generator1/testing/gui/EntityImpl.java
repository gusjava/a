package a.entity.gus.b.entitysrc1.generator1.testing.gui;

import a.framework.*;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210901";}


	private Service gui;
	private Service generator;

	public EntityImpl() throws Exception {
		gui = Outside.service(this,"*gus.a.features.p.string.inputgui1");
		generator = Outside.service(this,"gus.b.entitysrc1.generator1.testing");
		
		gui.p(generator);
	}
	
	
	public Object i() throws Exception {
		return gui.i();
	}
}
