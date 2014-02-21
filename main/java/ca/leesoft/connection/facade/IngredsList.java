package ca.leesoft.connection.facade;

import java.util.ArrayList;
import java.util.List;

import ca.leesoft.connection.pojo.Ingred;

public class IngredsList {
	private List<Ingred> list=new ArrayList<Ingred>();

	public List<Ingred> getList() {
		return list;
	}

	public void setList(List<Ingred> list) {
		this.list = list;
	}
	
}
