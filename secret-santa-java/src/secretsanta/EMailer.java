package secretsanta;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
class EMailer {
	private String to;
	private String subject;
	private String content;
	private String santa;
	private String recipient;
	private String group;
	
	private String from;
	private String username;
	private String password;
	private String host;
	
	public EMailer(String from,String username,String password,String host){
		this.from = from;
		this.username = username;
		this.password = password;
		this.host = host;
		
	}
	

	// Send a simple, single part, text/plain e-mail
	
public void sendEmail(String to2,String subject2,String content2,String santa2,String recipient2,String group2){
	// SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
    this.to = to2;
    this.subject = subject2;
    this.content = content2;
    this.santa = santa2;
	this.recipient = recipient2;
	this.group = group2;
    
    
    		//final String password = "aqwesmcsyopqrvwe";
    // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
    //String host = "smtp.gmail.com";

    // Create properties, get Session
    Properties props = new Properties();
    //Required for gmail
    props.put("mail.smtp.starttls.enable", "true");
    // If using static Transport.send(),
    // need to specify which host to send it to
    props.put("mail.smtp.host", host);
    // To see what is going on behind the scene
    props.put("mail.debug", "true");
    //Port
    props.put("mail.smtp.port", "587");
    //Authentication
    props.put("mail.smtp.auth", "true");
    
    Session session = Session.getInstance(props,new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	  });

    try {
        // Instantiate a message
        Message msg = new MimeMessage(session);

        //Set message attributes
        msg.setFrom(new InternetAddress(from));
        InternetAddress[] address = {new InternetAddress(to)};
        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setSubject(santa + ": "+ subject);
        msg.setSentDate(new Date());

        // Set message content
        msg.setText("Hello " + santa + "\nYour secret santa is "+ recipient + "\n"+content);

        //Send the message
        Transport.send(msg);
    }
    catch (MessagingException mex) {
        // Prints all nested (chained) exceptions as well
        mex.printStackTrace();
    }
}

	
}
