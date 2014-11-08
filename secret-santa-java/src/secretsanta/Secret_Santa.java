package secretsanta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


class Secret_Santa {
	
	//private static Secret_Santa this;
	private PairList references;
	private ArrayList<Person> people;
	private String fileLocation;
	public Secret_Santa(){
		
		//this = new Secret_Santa();
		this.references = new PairList();
		this.people = new ArrayList<Person>();
		
	}
	
public static void main (String [] argv)
{
	SecretSantaGUI sGUI = new SecretSantaGUI();
	
}
public void setName(String name) throws NameAlreadyExistsException{
	if(this.people.contains(name)) throw new NameAlreadyExistsException("Tried to add a name that already exists.");
	else this.people.add(new Person(name));
}

public ArrayList<Person> getPeople(){
	return this.people;
}
public void setFileLocation(String location){
	this.fileLocation = location;
}
public String getFileLocation(){
	return this.fileLocation;

}
public int getPeopleSize(){
	return people.size();
}
public Iterator<Person> getPeopleIterator(){
	return this.people.iterator();
}
public PairList getReferences(){
	return this.references;
}
private boolean isUniquePair(Pair p){
	if(p.getSantaName().equals(p.getRecipientName())) return false;
	else return true;
}
private void clearReferences(){
	this.references.clear();
}

public void setPairs(){
	boolean ok = false;
	ArrayList<Person> shuffle = null;
		while(!ok){
	shuffle = shufflePeople(people);
	clearReferences();
	int size = getPeopleSize();
	int i = 0;
	ok = false;
	

	for(i = 0;i<size;i++){
		System.out.println(i);
		Pair p = new Pair(people.get(i), shuffle.get(i));
		references.add(p);
	}
	boolean dupes = checkForDuplicates(references);
	System.out.println("Any dupes ?" + !dupes);
	if(checkForDuplicates(references)) ok = true;
	}
	System.out.println("Santas: " +toString(people));
	System.out.println("Reci..: " +toString(shuffle));
	System.out.println(references.toString());
	
	
	
}

private ArrayList<Person> shufflePeople(ArrayList<Person> p){
	long seed = System.nanoTime();
	ArrayList<Person> shuffle = (ArrayList<Person>) p.clone();
	Collections.shuffle(shuffle,new Random(seed));
	return shuffle;
}
private boolean checkForDuplicates(PairList p){
	//true = no duplicates, false = duplicates
	Iterator<Pair> it = p.iterator();
	boolean goodtogo = true;
	while(it.hasNext()){
		Pair pair = it.next();
		if(!isUniquePair(pair)){
			goodtogo = false;
		}
	}
	return goodtogo;
}

public String toString(ArrayList<Person> p){
	
	StringBuffer sb = new StringBuffer("[");
	int size = p.size();
	for(int i = 0; i<size;i++){
		sb.append(p.get(i).getName());
		if(i!=size-1) sb.append(",");
	}
	sb.append("]");
	return sb.toString();
}
private void clearPeople(){
	//references.clear();
	people.clear();
}
	
}
//private ArrayList<String> shuffleArrayList(ArrayList<String> array){
//	
//		  int s = array.size();
//		  String t, i;
//		 
//		  // While there remain elements to shuffle…
//		  for(int m = 0; m < s;m++) {
//		 
//		    // Pick a remaining element…
//		    i = Math.round(new Random(23) * m--);
//		 
//		    // And swap it with the current element.
//		    t = array.get(m);
//		    array[m] = array[i];
//		    array[i] = t;
//		  }
//		 
//		  return array;
//	
//}

