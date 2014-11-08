package secretsanta;

class Pair {
	private Person santa;
	private Person recipient;
public Pair(Person santa, Person recipient){
	this.santa = santa;
	this.recipient = recipient;
}

public void setPair(Person s, Person r){
	this.santa = s;
	this.recipient = r;
	}
public String getSantaName(){
	return this.santa.getName();
}
public String getRecipientName(){
	return this.recipient.getName();
}
public String getSantaEmail(){
	return this.santa.getEmail();
}
public String getRecipientEmail(){
	return this.recipient.getEmail();
}
public Person getSanta(){
	return this.santa;
}
public Person getRecipient(){
	return this.recipient;
}

}
