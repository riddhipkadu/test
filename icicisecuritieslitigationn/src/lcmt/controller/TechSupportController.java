package lcmt.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lcmt.domain.TechSupport;
import lcmt.service.UtilitiesService;

@Controller
@RequestMapping("/*")
public class TechSupportController {

	@Autowired
	UtilitiesService utilitiesService;
	
	private @Value("#{config['mail_user_name']?: 'null'}") String username;
	private @Value("#{config['mail_password']?: 'null'}") String password;
	private @Value("#{config['mail_smtp_host']?: 'null'}") String hostName;
	private @Value("#{config['mail_smtp_port']?: 'null'}") String portNo;
	private @Value("#{config['mail_from']?: 'null'}") String mailFrom;
	private @Value("#{config['project_name']?: 'null'}") String projectName;
	
	@RequestMapping(value = "/techSupport",method = RequestMethod.GET)
	public ModelAndView serachDocument(){
		ModelAndView modelAndView = new ModelAndView("techSupport");
		
		return modelAndView;
	}

	@RequestMapping(value = "/getSupportQuery", method = RequestMethod.POST)
	public String getSupportQuery(@RequestParam("Cname") String Cname,
			@RequestParam("Cmobile") String Cmobile,
			@RequestParam("Cemail") String Cemail,
			@RequestParam("Cmessage") String Cmessage){
		try {
			/*--------------------------Code to send mail---------------------*/
			//final String username = "";
			//final String password = "";
			
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", hostName);
			props.put("mail.smtp.port", portNo);
			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(mailFrom));
				//message.setRecipients(Message.RecipientType.CC,InternetAddress.parse("harshad.padole@lexcareglobal.com"));
				message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("tejashri.zurunge@lexcareglobal.com"));
				message.setRecipients(Message.RecipientType.CC,InternetAddress.parse("support@lexcareglobal.com"));
				message.setSubject("Query raised from LCMT tool");
				message.setText("Name: "+Cname+"\n"+"Email: "+Cemail+"\n"
				+"Mobile: "+Cmobile+"\n"+"Message: "+Cmessage+"\n\n This is a system generated mail. Please do not reply to this mail.\n In case of any doubt or difficulty, please call Helpdesk as per details given on the support page.");
 
				Transport.send(message);
				TechSupport techSupportObj = new TechSupport();
				techSupportObj.setSupp_user_name(Cname);
				techSupportObj.setSupp_user_email(Cemail);
				techSupportObj.setSupp_user_mobile(Cmobile);
				techSupportObj.setSupp_user_message(Cmessage);
				
				utilitiesService.persistTechSupport(techSupportObj);
				
				utilitiesService.addMailToLog(username, "Query raised from tool");
				
				System.out.println("Done");
				return "redirect:techSupport";
			} 

			catch (Exception e) 
			{
				// throw new RuntimeException(e);
				e.printStackTrace();
				return "";
				
			}

			/*----------------------Code to send mail ends here---------------*/
		} catch (Exception e) {
			e.printStackTrace();
			return "";

		}

	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: list mail details
	@RequestMapping(value = "/listTechSupport", method = RequestMethod.GET)
	public ModelAndView listTechSupport() {
		try {
			ModelAndView modelAndView = new ModelAndView("listTechSupport", "listTechSupport",utilitiesService.getAllTechSupport());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
