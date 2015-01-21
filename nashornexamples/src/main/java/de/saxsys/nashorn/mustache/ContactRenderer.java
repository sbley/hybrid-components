package de.saxsys.nashorn.mustache;

import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ContactRenderer {

    private final String TEMPLATE = "Email addresses of {{contact.name}}:\n" + "{{#contact.emails}}\n" + "- {{.}}\n"
            + "{{/contact.emails}}";

    private final String JSON_TEMPLATE = "{" + "\"contact\": {" + "\"name\": \"Mr A\", \"emails\": ["
            + "\"contact@some.tld\", \"sales@some.tld\"" + "]}}";

    public String renderEmailAdresses(String name, String... mailAdresses) {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("nashorn");

        Invocable invocable = (Invocable) engine;
        Object data = null;
        Object mustache = null;
        Object result = null;
        try {
            // Load mustache.js
            engine.eval(new FileReader(loadMustacheFilePath()));

            Object json = engine.eval("JSON");
            data = invocable.invokeMethod(json, "parse", JSON_TEMPLATE);
            System.out.println(data.toString());

            mustache = engine.eval("Mustache");
            result = invocable.invokeMethod(mustache, "render", TEMPLATE, data);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    private String loadMustacheFilePath() {
        return ContactRenderer.class.getResource("mustache.js").getPath();
    }
}
