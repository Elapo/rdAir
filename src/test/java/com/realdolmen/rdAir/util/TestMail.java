package com.realdolmen.rdAir.util;

public class TestMail {
    public static void main(String[] args) {
        MailService ms = new MailService();
        if(ms.mail("frederik.vanh@gmail.com", "TestMail", "<h1>Here is your QR-code:</h1><br><img src=\"cid:image\">"))
            System.out.println("Success");
        else System.out.println("nope");
    }
}
