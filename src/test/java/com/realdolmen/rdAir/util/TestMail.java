package com.realdolmen.rdAir.util;

import com.realdolmen.rdAir.services.MailService;

public class TestMail {
    public static void main(String[] args) {
        String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\" \"http://www.w3.org/TR/REC-html40/loose.dtd\">\n" +
                "<html><body style=\"font-family: sans-serif;\">\n" +
                "\n" +
                "    <h2>Thank you for your order!</h2>\n" +
                "    <p>Summary:</p>\n" +
                "<table style=\"border-collapse: collapse; border: 1px solid black;\">\n" +
                "    <tr>\n" +
                "        <th style=\"border-collapse: collapse; width: 4%; text-align: center; border: 1px solid black;\" align=\"center\">Ticket number</th>\n" +
                "        <th style=\"border-collapse: collapse; width: 4%; text-align: center; border: 1px solid black;\" align=\"center\">Destination</th>\n" +
                "        <th style=\"border-collapse: collapse; width: 4%; text-align: center; border: 1px solid black;\" align=\"center\">Date</th>\n" +
                "        <th style=\"border-collapse: collapse; width: 4%; text-align: center; border: 1px solid black;\" align=\"center\">Price</th>\n" +
                "    </tr>\n" +
                "    <!-- foreach ticket in order -->\n" +
                "</table>\n" +
                "</body></html>\n";

        MailService ms = new MailService();
        if(ms.mail("frederik.vanh@gmail.com", "TestMail", html))
            System.out.println("Success");
        else System.out.println("nope");
    }
}
