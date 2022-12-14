package Business;

import java.util.ArrayList;
import java.util.Properties;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.security.auth.Subject;

import Controllers.TicketController;
import Entitity.Coupon;
import Entitity.Ticket; 


public class PaymentService{
    
    final String FROM_EMAIL = "CinemopolisTheatre@gmail.com"; //requires valid gmail id
    final String PASSWORD = "xslqzhlzmwhpozvi"; // correct PASSWORD for gmail id


    public PaymentService(){}


    /**
	 * Function to create a ticket confirmation email based on the provided ticket object
	 * 
	 * @param t Ticket object from which information is used
	*/
    public boolean makeEmail(Ticket t){
        Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication 
    	props.put("mail.smtp.starttls.required", "true");
        String toEmail = t.getEmail();

        Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
			}
		};
		Session session = Session.getInstance(props, auth);
        String subject;
        String body;
        subject = makeConfirmationSubject();
        body = makeBodyEmail(t);
        
        return EmailUtil.sendEmail(session, toEmail, subject, body, FROM_EMAIL);
    } 


    /**
	 * Function to create a cancellation email based on the provided coupon object
	 * 
	 * @param email Email of user to which email will be sent
     * @param id String containing the coupon ID
     * @param c Coupon object containing information to be used in the email
	*/
    public boolean makeEmail(String email, String id, Coupon c){
        Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication 
    	props.put("mail.smtp.starttls.required", "true");
        String toEmail = email;

        Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
			}
		};
		Session session = Session.getInstance(props, auth);
        String subject;
        String body;

        subject = makeCancellationSubject();
        body = makeBodyEmail(id, c);

        return EmailUtil.sendEmail(session, toEmail,subject, body,FROM_EMAIL);
    } 


    /**
	 * Function to create confirmation subject 
	 * 
	 * @param None
	*/
    public String makeConfirmationSubject(){
            String subject = "Your Cinemopolis Ticket Purchase Confirmation";
            return subject;
    }
       
    /**
	 * Function to create cancellation subject
	 * 
	 * @param None
	*/
    public String makeCancellationSubject(){
            String subject = "Your Cinemopolis Ticket Cancellation Confirmation";
            return subject;
    }


    /**
	 * Function to create the body of a confirmation email
	 * 
	 * @param t Ticket object to be used for the email
	*/
    public String makeBodyEmail(Ticket T){
        TicketController tc = new TicketController();
        int id = tc.getRecentTicket();
        
        String body = "Thank you for your purchase at Cinemopolis!\n\n";
        body+= "Below you will find the information for your tickets:\n\n";
        body+= "Movie: " + T.getMovie() + '\n';
        body+= "Theatre: " + T.getTheatre()+ '\n';
        body+= "Showtime: " + T.getShowtime() + '\n';
        body+= "Quantity: " + T.getSeats().size() + "\n";
        body+= "Seats: ";
        for(int val : T.getSeats()){
            int v = val%20;

            if(v == 0){
                body+=" Seat " + 20 + ",";
            }
            else{
                body+=" Seat " + val%20 + ",";
            }
        }
        body = body.substring(0,body.length()-1);
        body+='\n';
        body+= "Price: " + T.getPrice() + '\n';
        body+= "Your order ID is: " + id + '\n';
        body+='\n';
        body+= "We hope to see you again!";
        return body;
    }


    /**
	 * Function to create the body of a cancellation email
	 * 
	 * @param id String containing the coupon code
     * @param c Coupon object used for the email
	*/
    public String makeBodyEmail(String id, Coupon C){
        String body = "Your Cinemopolis Ticket Cancellation Confirmation :(\n";
        body+= "Your order with ID: " + id + " has successfully been canceled.\n\n";
        body+="Coupon Code: " + C.getID() + '\n';
        body+="Coupon Value: " + C.getValue() + '\n'; 
        body+="We hope to see you again!";
        return body;
    }
}

class EmailUtil {
    /**
	 * Function that handles sending out the email to the reciepent
	 * 
	 * @param session Session object
     * @param toEmail Email ID of the recipient
     * @param subject Subject line of the email
     * @param body Body of the email
     * @param FROM_EMAIL Email ID of the sender
	*/
    public static boolean sendEmail(Session session, String toEmail, String subject, String body, String FROM_EMAIL){
        try{
          MimeMessage msg = new MimeMessage(session);
          //set message headers
          msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
          msg.addHeader("format", "flowed");
          msg.addHeader("Content-Transfer-Encoding", "8bit");
          msg.setFrom(new InternetAddress(FROM_EMAIL));
          msg.setReplyTo(InternetAddress.parse(toEmail, false));
          msg.setSubject(subject, "UTF-8");
          msg.setText(body, "UTF-8");
          msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
          Transport.send(msg);
          return true;
        }
        catch (Exception e) {
          return false;
        }
    }
}

