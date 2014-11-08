package secretsanta;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class GetInfoGUI extends JFrame implements ActionListener,Runnable{
private JPanel frmTop;
private JTextField txtUserInput;
private JButton btnSubmit;
private JLabel lblInputType;
private String userInput;
private boolean gotInfo;
public GetInfoGUI(String infoType){
	addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
			//this.dispose();
		}
	});
	setTitle("Enter " + infoType);
	setLayout(new GridLayout(2,1));
	setSize(300,100);
	
	lblInputType = new JLabel(infoType);
	txtUserInput = new JTextField();
	btnSubmit = new JButton("Submit");
	
	gotInfo = false;
	
	btnSubmit.addActionListener(this);
	frmTop = new JPanel(new GridLayout(1,2));
	frmTop.add(lblInputType);
	frmTop.add(txtUserInput);
	add(frmTop);
	add(btnSubmit);
	setEnabled(true);
	setVisible(true);
}
@Override
public void actionPerformed(ActionEvent action) {
	// TODO Auto-generated method stub
	if(action.getSource() == btnSubmit){
		String in = txtUserInput.getText();
		if(in != null && !in.isEmpty()){
			userInput = txtUserInput.getText();
			gotInfo = true;
			//System.exit(0);
			this.dispose();
		}else{
			userInput = null;
			gotInfo = false;
			
		}
}
	}
//public static void main(String[] args){
//	GetInfoGUI g = new GetInfoGUI("Name:");
//}
public String getUserInput(){
	return this.userInput;
}
public boolean gotInfo(){
	return gotInfo;
}
@Override
public void run() {
	System.out.println("Hello from a thread!");
	
	
}
}
