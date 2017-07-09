package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import it.polito.tdp.artsmia.model.ArtObject;

public class ArtsmiaDAO {

	public List<ArtObject> listObject() {
		
		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
//				result.add(new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
//						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
//						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
//						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Integer> getListAnni() {
		String sql = "SELECT DISTINCT begin FROM exhibitions ORDER BY begin DESC";
		
		List<Integer> result = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(res.getInt("begin"));
				
				 }

			conn.close();
			return result;
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new RuntimeErrorException(null,"Errore duante l'estrazione della lista degli anni");
			
		}
	}

	public List<ArtObject> getListOpere(Integer anno, Map<Integer, ArtObject> idMapOpereCorrenti) {
		//ob.object_id,ob.classification,ob.continent,ob.country,
		//ob.curator_approved,ob.dated,ob.department,ob.medium,
		//ob.nationality,ob.object_name,ob.restricted,ob.rights_type,
		//ob.role,ob.room,ob.style,ob.title
		
		String sql= "SELECT ob.object_id,ob.classification,ob.continent,ob.country,"
				+ "ob.curator_approved,ob.dated,ob.department,ob.medium,"
				+ "ob.nationality,ob.object_name,ob.restricted,ob.rights_type, "
				+ "ob.role,ob.room,ob.style,ob.title "
				+ "FROM objects AS ob WHERE ob.object_id IN (SELECT DISTINCT eo.object_id "
														  + "FROM exhibition_objects AS eo "
														  + "WHERE eo.exhibition_id "
														  			+ "IN (SELECT ex.exhibition_id "
														  			+ "FROM exhibitions AS ex "
														  			+ "WHERE ex.begin>=?))";

		List<ArtObject> result = new ArrayList<>();	

		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				ArtObject a = new ArtObject(res.getInt("ob.object_id"),res.getString("ob.style"), res.getString("ob.title"));
				
				idMapOpereCorrenti.put(a.getObjectId(), a);
				result.add(a);
				
				
				
				
				 }

			conn.close();
			return result;
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new RuntimeErrorException(null,"Errore duante l'estrazione della lista degli anni");
			
		}
	}
}
