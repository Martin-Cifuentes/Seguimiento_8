package model;

public class Client {
	public String id;
	public Type type;
	
	public Client(String id, Type t) {
		this.id = id;
		this.type = t;
	}

	@Override
	public String toString() {
		return "Cliente [tipo de documento = " + type + ", id = " + id + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
}
