package data;

public class Animals {
	protected String name;
	protected String id;
	protected String attribute;
	protected double weight;
	protected int type;
	
	public Animals() {}

	public Animals(String id, String name, String attribute, double weight, int type) {
		super();
		this.id = id;
		this.name = name;
		this.attribute = attribute;
		this.weight = weight;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public void print() {
		System.out.format("|%-10s|%-15s|%-30s|%-10.2f|%-5d\n", id, name, attribute, weight, type);
	}
}
