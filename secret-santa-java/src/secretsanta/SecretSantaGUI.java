package secretsanta;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



class SecretSantaGUI extends JFrame implements ActionListener{
	JPanel panLeft,panRight,panRightTop,panRightBottom,panRightBottomRight;
	JButton btnInsert, btnFetch, btnGenerate, btnFind,btnEmail,btnInputEmails;
	JTextField txtName;
	JTextArea txtList;
	JLabel lblName,lblInstructions;
	JScrollPane scrPane;
	Secret_Santa s = new Secret_Santa();
	String nl = System.getProperty("line.separator");
	public SecretSantaGUI(){
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setTitle("Secret Santa Program");
		setLayout(new GridLayout(1,2));
		setSize(800, 600);
		
		panLeft = new JPanel(new GridLayout(1,1));
		panRight = new JPanel(new GridLayout(2,1));
		panRightTop = new JPanel(new GridLayout(4,1));
		panRightBottom = new JPanel(new GridLayout(2,2));
		panRightBottomRight = new JPanel(new GridLayout(2,1));
		
		btnInsert = new JButton("Insert a Single Name");
		btnInsert.addActionListener(this);
		
		btnFetch= new JButton("Fetch names from file");
		btnFetch.addActionListener(this);
			
		btnGenerate= new JButton("Generate Secret Santa");
		btnGenerate.addActionListener(this);
		btnGenerate.setEnabled(false);
		
		
		btnFind= new JButton("Find Your Secret Santa");
		btnFind.addActionListener(this);
		btnFind.setEnabled(false);	
		
		btnEmail = new JButton("Send out emails");
		btnEmail.addActionListener(this);
		btnEmail.setEnabled(false);
		
		btnInputEmails = new JButton("Input All Email Addresses");
		btnInputEmails.addActionListener(this);
		btnInputEmails.setEnabled(false);
		
		txtList = new JTextArea(50,2);
		txtList.setEditable(false);
		
		scrPane = new JScrollPane(txtList); 
		txtName = new JTextField();
		txtName.setHorizontalAlignment(JTextField.CENTER);
		txtName.setEnabled(false);	
		
		lblName = new JLabel("Insert Your Name:");
		lblName.setEnabled(false);	
		lblInstructions = new JLabel("The file should contain names separated by commas.");
	
		
		
		panRightBottom.add(lblName);
		panRightBottom.add(txtName);
		panRightBottom.add(btnFind);
		panRightBottom.add(panRightBottomRight);
		
		panRightBottomRight.add(btnEmail);
		panRightBottomRight.add(btnInputEmails);
		
		
		panRightTop.add(btnInsert);
		panRightTop.add(btnFetch);
		panRightTop.add(lblInstructions);
		panRightTop.add(btnGenerate);
		
		panLeft.add(scrPane);
		panRight.add(panRightTop);
		panRight.add(panRightBottom);
		add(panLeft);
		add(panRight);
		
		
		setVisible(true);
		setEnabled(true);
					
					 
	}
	@Override
	public void actionPerformed(ActionEvent action) {
		
		
			
		
		if(action.getSource() == btnInsert){
			
			boolean got = false;
			String name = "null";
			while(!got){
			name = JOptionPane.showInputDialog("Enter a name: ");
			if(name != null && !name.isEmpty()){
			System.out.println("Valid Name entered.");
			got = true;
			
			try{
			
			s.setName(name);
					System.out.println(name);
			txtList.setText(txtList.getText() + nl +name + nl);
			}catch(NameAlreadyExistsException n){
				System.out.println("Skipped word: " + name +", was already added.");
			}
			//System.out.println();
			
			}else{
				JOptionPane.showMessageDialog(this, "Invalid name");
			}
		}
			if(s.getPeople().size() > 2){
				btnGenerate.setEnabled(true);
				}
		}
		
		else if(action.getSource() == btnFind){
			String name = txtName.getText();
			String recipient = null;
			if(name !=null && !name.isEmpty()){
				try{
				recipient = s.getReferences().findRecipient(name);
				if(recipient == null){
					JOptionPane.showMessageDialog(this, "Your name cannot be found, make sure it is spelled as it appears on the right.");
					
				}JOptionPane.showMessageDialog(this, "Your need to buy a present for " + recipient);
				txtName.setText("");
			}
				
			catch(NameNotFoundException n){
				JOptionPane.showMessageDialog(this, "Please insert a name and try again.");
			}
				
		}
		}
		else if(action.getSource() == btnFetch){
			boolean got = false;
			String fileLocation = "null";
			FileReader fr = null;
			StringTokenizer st;
			while(!got){
			fileLocation = JOptionPane.showInputDialog("Enter a name: ");
			if(fileLocation != null && !fileLocation.isEmpty())
				try{
				
			fr = new FileReader(fileLocation);
			got = true;
				}catch(FileNotFoundException e){
					got = false;
				}
			
			}
			System.out.println("Valid file location entered.");
			System.out.println(fileLocation);
			
			BufferedReader br = new BufferedReader(fr);
			try {
				while(br.ready()){
					String line = br.readLine();
					st = new StringTokenizer(line,",");
					while(st.hasMoreTokens()){
						String in = st.nextToken();
						if(in != null && !in.isEmpty()){
							try{
						s.setName(in);
						txtList.setText(txtList.getText() + nl +in + nl);
						System.out.println("Name entered: "+ in);
							}catch(NameAlreadyExistsException n){
								System.out.println("Skipped word: " + in +", was already added.");
							}
						}
					}
				}
			} catch (IOException e) {
				System.err.println("No data in input file!");
				e.printStackTrace();
			}
			
			if(s.getPeople().size() > 3){
			btnGenerate.setEnabled(true);
			}
		}
		
		else if(action.getSource() == btnGenerate){
			s.setPairs();
			btnFind.setEnabled(true);
			lblName.setEnabled(true);
			txtName.setEnabled(true);
			btnInputEmails.setEnabled(true);
			btnGenerate.setEnabled(false);
		}
		
		else if(action.getSource() == btnInputEmails){
			int size = s.getPeopleSize();
			String email = null;
			
		
			boolean valid = false;
			for(Person person : s.getPeople()){
				valid = false;
				while(valid == false){
				
				email = JOptionPane.showInputDialog("Enter email for " + person.getName());
				
				if(email.contains("@")) valid = true;
				}
				txtList.setText(txtList.getText() + nl +email + nl);
				person.setEmail(email);
				System.out.println("Email for " + person.getName() +" set to " + email);
				//c++;
				btnInputEmails.setEnabled(false);
			}
			btnEmail.setEnabled(true);
		}
		else if(action.getSource() == btnEmail){
			EMailer em = new EMailer("camieac@gmail.com","camieac@gmail.com","aqwesmcsyopqrvwe", "smtp.gmail.com");
			String email;
			String n = "null";
			for(Person p : s.getPeople()){
				email = p.getEmail();
				try {
					n = s.getReferences().findRecipient(p.getName());
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(email != null && !email.isEmpty()){
					em.sendEmail(p.getEmail(), "FLACID Secret Santa", "You have a set budget of £10", p.getName(), n, "GROUP");
				}
			}
			btnEmail.setEnabled(false);
		}
	}
	
	
	public static void main(String[] args){
		SecretSantaGUI s = new SecretSantaGUI();
		//Secret_Santa s = new Secret_Santa();
	}

}
