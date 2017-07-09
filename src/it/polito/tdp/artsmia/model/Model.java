package it.polito.tdp.artsmia.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {

	public List<Integer> anni;
	private ArtsmiaDAO dao;
	private WeightedGraph<ArtObject, DefaultWeightedEdge> grafo;
	private List<ArtObject> opereCorrenti;
	private Map<Integer, ArtObject> idMapOpereCorrenti;
	
	public Model(){
		this.dao=new ArtsmiaDAO();
		this.idMapOpereCorrenti=new HashMap<>();
	}
	
	public List<Integer> getAnni() {
		if(this.anni==null)
			this.anni=this.dao.getListAnni();
		return this.anni;
	}

	public void creaGrafo(Integer anno) {

		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		this.getOpereCorrenti(anno);
		
		Graphs.addAllVertices(grafo, this.opereCorrenti);
		
		
		
		for (ArtObject o1 : grafo.vertexSet()) {
			for (ArtObject o2 : grafo.vertexSet()) {
				if(!o1.equals(o2)){
					Graphs.addEdge(grafo, o1, o2, this.getNumeroOpereInComune(o1,o2));
				}
			}
		}
		
		System.out.println("Grafo creato!");
		System.out.println("# vertici: "+grafo.vertexSet().size());
		System.out.println("# archi: "+grafo.edgeSet().size());
	}

	private double getNumeroOpereInComune(ArtObject o1, ArtObject o2) {
		Set<Integer> intersection = new HashSet<Integer>(o1.getSetIdEsibizioni());
		intersection.retainAll(o2.getSetIdEsibizioni());
		
		return intersection.size();
	}

	private void getOpereCorrenti(Integer anno) {

		this.opereCorrenti=this.dao.getListOpere(anno, idMapOpereCorrenti);
		
	}

	public Object getClassifica() {

		
		
		
		return null;
	}

}
