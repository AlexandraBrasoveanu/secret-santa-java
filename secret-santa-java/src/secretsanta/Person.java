package secretsanta;

class Person {
	private String name;
	private String email;

	public Person(){
		this.name = "Not Provided";
		this.email = "Not Provided";
	}
	public Person(String name, String email){
		this.name = name;
		this.email = email;
	}
	public Person(String name){
		this.name = name;
		this.email = "Not Provided";
	}
	
	public void setName(String name){
		this.name = name;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getName(){
		return this.name;
	}
	public String getEmail(){
		return this.email;
	}
}
