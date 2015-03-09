package cranfield.group.project.airfoil.api.model;

public class OptimizationObject {
	protected int id;
	protected String name;
	
	public OptimizationObject(int id, String name){
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
