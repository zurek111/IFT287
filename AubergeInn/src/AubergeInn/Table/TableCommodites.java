package AubergeInn.Table;

import java.sql.*;
import AubergeInn.Connexion;

public class TableCommodites 
{
    private PreparedStatement someStatement;
    private Connexion cx;
    
	public TableCommodites(Connexion cx) throws SQLException 
	{
		this.cx = cx;
		someStatement = cx.getConnection().prepareStatement(
				"todo");
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
}
