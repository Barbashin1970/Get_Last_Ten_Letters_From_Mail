import secret.KeyLogin;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;


public class MailChecker extends KeyLogin {


    public static void main(String[] args) {
        getPostFromYandex();
        getPostFromMailRu();
    }

    public static void getPostFromYandex() {
        try {
            String email = emailYandex;
            String password = passwordYandex;

            // Создаем соединение с почтовым сервером
            Properties properties = new Properties();
            properties.setProperty("mail.store.protocol", "imaps");
            properties.setProperty("mail.imaps.ssl.protocols", "TLSv1.2");
            properties.setProperty("mail.imaps.ssl.ciphersuites", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");

            Session session = Session.getInstance(properties);

            Store store = session.getStore("imaps");
            store.connect("imap.yandex.ru", 993, email, password);

            // Открываем папку INBOX
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Получаем последние 10 писем
            int messageCount = inbox.getMessageCount();
            Message[] messages = inbox.getMessages(messageCount - 9, messageCount);
            System.out.println("-------------- yandex.ru ---------------------");

            // Выводим информацию о письмах
            for (Message message : messages) {
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Sent Date: " + message.getSentDate());
                System.out.println("Content: " + message.getContent());
                System.out.println("----------------------------------------------");
            }

            // Закрываем соединение
            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getPostFromMailRu() {
        try {
            String email = emailForMailRu;
            String password = passwordMail;

            // Создаем соединение с почтовым сервером
            Properties properties = new Properties();
            properties.setProperty("mail.store.protocol", "imaps");
            properties.setProperty("mail.imaps.ssl.protocols", "TLSv1.2");
            properties.setProperty("mail.imaps.ssl.ciphersuites", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");
            Session session = Session.getInstance(properties);

            Store store = session.getStore("imaps");
            store.connect("imap.mail.ru", 993, email, password);

            // Открываем папку INBOX
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Получаем последние 10 писем
            int messageCount = inbox.getMessageCount();
            Message[] messages = inbox.getMessages(messageCount - 9, messageCount);
            System.out.println("------------------- mail.ru ------------------");

            // Выводим информацию о письмах
            for (Message message : messages) {
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Sent Date: " + message.getSentDate());
                System.out.println("Content: " + message.getContent());
                System.out.println("----------------------------------------------");
            }

            // Закрываем соединение
            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


