
import static spark.Spark.*;

//adding stuff to work with handlebars
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView; //Spark tool that allows us to pass dynamic information, like variables, from our App.java file to our template files.
import spark.template.handlebars.HandlebarsTemplateEngine;



public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

//        get("/", (request, response) -> { //request for route happens at this location
//            Map<String, Object> model = new HashMap<String, Object>(); // new model is made to store information
//            return new ModelAndView(model, "hello.hbs"); // assemble individual pieces and render
//        }, new HandlebarsTemplateEngine()); //



        //homepage: form that takes money amount
        get("/", (request, response) -> {
            Map<String, String> model = new HashMap<String, String>();
            return new ModelAndView(model, "main.hbs");

        }, new HandlebarsTemplateEngine());

        //result: page detailing the coin combinations used to create change
        get("/result", (request, response) -> {
            Map<String, String> model = new HashMap<String, String>();

            String money = request.queryParams("money");
            model.put("money", money);

            ChangeMachine myChangeMachine = new ChangeMachine();
            float fMoney = Float.parseFloat(money);
            String change = myChangeMachine.makeChange(fMoney);
            model.put("change", change);

            return new ModelAndView(model, "result.hbs");

        }, new HandlebarsTemplateEngine());

    }
}
