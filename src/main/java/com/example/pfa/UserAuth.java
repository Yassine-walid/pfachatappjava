package com.example.pfa;

import java.io.File;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class UserAuth {
    private static final String XML_FILE_PATH = "C:\\Users\\Stephen\\Documents\\Projects\\pfa\\users.xml";

    public static boolean registerUser(String username, String password) {
        boolean test = false;
        try {
            File xmlFile = new File(XML_FILE_PATH);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Check if username already exists
            NodeList userList = doc.getElementsByTagName("user");
            for (int i = 0; i < userList.getLength(); i++) {
                Element user = (Element) userList.item(i);
                String existingUsername = user.getElementsByTagName("username").item(0).getTextContent();
                if (existingUsername.equals(username)) {
                    System.out.println("Username already exists. Registration failed.");
                }
            }

            Element newUser = doc.createElement("user");
            Element newUsername = doc.createElement("username");
            newUsername.appendChild(doc.createTextNode(username));
            newUser.appendChild(newUsername);

            Element newPassword = doc.createElement("password");
            newPassword.appendChild(doc.createTextNode(password));
            newUser.appendChild(newPassword);

            doc.getDocumentElement().appendChild(newUser);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(XML_FILE_PATH);
            transformer.transform(source, result);



            test = true;

            System.out.println("Registration successful.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }

    public static Boolean login(String username, String password) {
        Boolean test = false;
        try {
            File xmlFile = new File(XML_FILE_PATH);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Check if username and password match
            NodeList userList = doc.getElementsByTagName("user");
            for (int i = 0; i < userList.getLength(); i++) {
                Element user = (Element) userList.item(i);
                String storedUsername = user.getElementsByTagName("username").item(0).getTextContent();
                String storedPassword = user.getElementsByTagName("password").item(0).getTextContent();
                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    System.out.println("Login successful.");
                    test = true;
                }
            }

            System.out.println("Invalid username or password. Login failed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }
}
