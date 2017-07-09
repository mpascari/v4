package it.polito.tdp.artsmia.model;

import java.util.HashSet;
import java.util.Set;

public class ArtObject {

	private int objectId;
	private String style;
	private String title;
	private Set<Integer> setIdEsibizioni;
	
	public ArtObject(int objectId, String style, String title) {
		super();
		this.objectId = objectId;
		this.style = style;
		this.title = title;
		this.setIdEsibizioni=new HashSet<>();
	}
	
	
	public Set<Integer> getSetIdEsibizioni() {
		return setIdEsibizioni;
	}


	public void setSetIdEsibizioni(Set<Integer> setIdEsibizioni) {
		this.setIdEsibizioni = setIdEsibizioni;
	}


	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + objectId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArtObject other = (ArtObject) obj;
		if (objectId != other.objectId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ArtObject [style=" + style + ", title=" + title + "]";
	}

	

}
