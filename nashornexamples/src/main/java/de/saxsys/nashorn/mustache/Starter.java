package de.saxsys.nashorn.mustache;

public class Starter {
    public static void main(String[] args) {
        String renderEmailAdresses = new ContactRenderer().renderEmailAdresses("Alex", "lol@lol.de", "rofl@rofl.de");
        System.out.println(renderEmailAdresses);
    }
}
