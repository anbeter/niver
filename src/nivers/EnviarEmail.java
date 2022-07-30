package nivers;

import java.util.Properties;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnviarEmail {

    private String host = "smtp.adtur.to.gov.br"; /* endereço do servidor smtp */

    public class SMTPAuthenticator extends Authenticator {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String username = "";
            String password = "";
            return new PasswordAuthentication(username, password);
        }
    }

    public void EnviarEmailPara(String para, String assunto, String mensagem, String from) throws Exception {
//        String from = "cti@adtur.to.gov.br";
//        from = rem;
        String fileAttachment = "";
        Properties mailProps = System.getProperties();
        mailProps.put("mail.smtp.host", this.host);
        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getDefaultInstance(mailProps, auth);
        mailProps.put("mail.smtp.auth", "false");
        mailProps.put("mail.debug", "true");
        mailProps.put("mail.smtp.debug", "true");
        mailProps.put("mail.mime.charset", "ISO-8859-1");
        mailProps.put("mail.smtp.port", "25");
        MailcapCommandMap mc = (MailcapCommandMap)CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mc);
        // Definir mensagem
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(para));
        message.setSubject(assunto);
        // Criar parte da mensagem
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        // Texto da mensagem
        messageBodyPart.setText(mensagem);
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        // Enviar parte da mensagem
        message.setContent(multipart);
        message.setContent(mensagem.toString(), "text/html");
        // Enviar toda a mensagemSend the message
        Transport.send( message );
    }

}