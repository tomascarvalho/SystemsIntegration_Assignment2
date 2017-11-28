package ejb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class EmailEJB {

    @Resource(lookup = "java:/jboss/mail/gmail")
    private Session session;

    public EmailEJB() {

    }

    public void send(String to, long carID, String customerName) {
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            System.out.println(session);
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("A car you're following just changed price!");

            // Send the actual HTML message, as big as you like
            message.setContent("<h1>Greetings!</h1>" +
                    "<br>" +
                    "Hello " + customerName + "!" +
                    "A car you were following just updated its price!" +
                    "<br>" +
                    "Check it out: <a href=localhost:8080/projeto2-web/car?id=" + carID+"> here!</a>", "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
