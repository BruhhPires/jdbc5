package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityExcepition;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st =  null;
		
		try {
			
			conn = DB.getConnection();
			
			st = conn.prepareStatement( 
					"DELETE FROM Department " // COMANDO PARA DELETER
					+ "WHERE "			      // ATENÇÃO, SEMPRE USAR O WHERE(ONDE) PRA DEFINIR ONDE SERÁ FEITO A MANUTENÇÃO, OU SERÁ FEITO EM TUDO
					+ "Id = ? ");			  // ONDE SERÁ FEITO A ATUALIZAÇÃO	  
			
			st.setInt(1, 2);                  // 1(POSIÇÃO DO '?' -- 2(NUMERO DO ID)
			
			int rowsAffect = st.executeUpdate();
			
			System.out.println("Done! Rows Affected: " + rowsAffect);
			
		}
		catch (SQLException e) {
			throw new DbIntegrityExcepition(e.getMessage());
		}
		finally {
			DB.closeConnection();
			DB.closeStatement(st);
		}

	}

}
