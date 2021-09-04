package a.entity.gus.b.entitysrc2.database.entity.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import a.framework.Entity;
import a.framework.P;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210904";}

	public static final String TABLENAME_ENTITY = "entity";

	public static final String COL_NAME = "name";
	public static final String COL_FEATURES = "features";
	public static final String COL_CREATIONDATE = "creationdate";
	public static final String COL_LENGTH = "length";
	public static final String COL_CALLNB = "callnb";


	public EntityImpl() throws Exception {
		
	}
	
	
	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];
		
		Object name = data.get(COL_NAME);
		Object features = data.get(COL_FEATURES);
		Object creationdate = data.get(COL_CREATIONDATE);
		Object length = data.get(COL_LENGTH);
		Object callNb = data.get(COL_CALLNB);
		
		String sql = "UPDATE "+TABLENAME_ENTITY+" SET "
				+ COL_FEATURES + "=?, "
				+ COL_CREATIONDATE + "=?, "
				+ COL_LENGTH + "=?, "
				+ COL_CALLNB + "=? "
				+ "WHERE " + COL_NAME + "=?";
		
		executeUpdate(cx, sql, 
				features, creationdate, length, callNb,
				name);
	}
	
	
	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for(int i=0;i<params.length;i++) st.setObject(i+1,params[i]);
		st.executeUpdate();
		st.close();
	}
}
