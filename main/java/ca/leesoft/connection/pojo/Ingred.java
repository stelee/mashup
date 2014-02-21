package ca.leesoft.connection.pojo;

public class Ingred {
	private long id;
	String code;
	String name;
	String description;
	String imageUrl;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
