package AubergeInn.Table;

import java.sql.*;
import AubergeInn.Connexion;

public class TableChambres 
{
    private PreparedStatement someStatement;
    private Connexion cx;
    
	public TableChambres(Connexion cx) throws SQLException 
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
