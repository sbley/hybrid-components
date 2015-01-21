package de.saxsys.nashorn;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaScriptApiCall {

    public static void main(String[] args) throws ScriptException, FileNotFoundException, NoSuchMethodException {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("nashorn");

        engine.eval(new FileReader(loadMustacheFilePath()));

        Invocable invocable = (Invocable) engine;

        String template =
                "Email addresses of {{contact.name}}:\n" + "{{#contact.emails}}\n" + "- {{.}}\n"
                        + "{{/contact.emails}}";

        String contactJson =
                "{" + "\"contact\": {" + "\"name\": \"Mr A\", \"emails\": ["
                        + "\"contact@some.tld\", \"sales@some.tld\"" + "]}}";

        Object json = engine.eval("JSON");
        Object data = invocable.invokeMethod(json, "parse", contactJson);

        Object mustache = engine.eval("Mustache");
        System.out.println(invocable.invokeMethod(mustache, "render", template, data));
    }

    private static String loadMustacheFilePath() {
        return JavaScriptApiCall.class.getResource("mustache.js").getPath();
    }
}
