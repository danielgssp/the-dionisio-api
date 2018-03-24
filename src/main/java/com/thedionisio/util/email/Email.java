package com.thedionisio.util.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import java.net.MalformedURLException;

/**
 * Created by jonathan on 4/5/17.
 */
public class Email {

        public void main(String[] args) throws EmailException, MalformedURLException {
        }

        private static String HostName = "smtp.googlemail.com";
        private static Integer SmtpPort = 587;
        private static String EmailRemetente = "thedionisio.birl@gmail.com";
        private static String Senha = "qnvaidaroq";


        public void EnviarTextoSimples(String Destinatario, String Assunto, String TextoSimples) throws EmailException{
            SimpleEmail email = new SimpleEmail();
            email.setHostName(HostName);
            email.setSmtpPort(SmtpPort);
            email.setAuthenticator(new DefaultAuthenticator(EmailRemetente, Senha));
            email.setSSLOnConnect(true);
            email.setFrom(EmailRemetente);
            email.addTo(Destinatario);
            email.setSubject(Assunto);
            email.setMsg(TextoSimples);
            email.send();

            System.out.println("Email de texto simples enviado para :" + Destinatario);
        }


        public  void EnviarTextoHTML(String Destinatario, String Assunto, String TextoHtml,  String TextoSimples) throws EmailException, MalformedURLException{

            HtmlEmail email = new HtmlEmail();
            email.setHostName(HostName);
            email.setSmtpPort(SmtpPort);
            email.setAuthenticator(new DefaultAuthenticator(EmailRemetente, Senha));
            email.setSSLOnConnect(true);
            email.setFrom(EmailRemetente);
            email.addTo(Destinatario);
            email.setSubject(Assunto);
            email.setHtmlMsg(TextoHtml);
            email.setTextMsg(TextoSimples);
            email.send();

            System.out.println("Email de texto html enviado para :" + Destinatario);
        }


}
