package secretsanta;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

// Send a simple, single part, text/plain e-mail
public class Mailer {

    public static void main(String[] args) {

        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        String to = "vipan@vipan.com";
        String from = "vipan@vipan.com";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        String host = "smtp.yourisp.net";

        // Create properties, get Session
        Properties props = new Properties();

        // If using static Transport.send(),
        // need to specify which host to send it to
        props.put("mail.smtp.host", host);
        // To see what is going on behind the scene
        props.put("mail.debug", "true");
        Session session = Session.getInstance(props);

        try {
            // Instantiate a message
            Message msg = new MimeMessage(session);

            //Set message attributes
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("Test E-Mail through Java");
            msg.setSentDate(new Date());

            // Set message content
            msg.setText("This is a test of sending a " +
                        "plain text e-mail through Java.\n" +
                        "Here is line 2.");

            //Send the message
            Transport.send(msg);
        }
        catch (MessagingException mex) {
            // Prints all nested (chained) exceptions as well
            mex.printStackTrace();
        }
    }
}//End of class