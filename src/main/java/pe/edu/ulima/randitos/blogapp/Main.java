/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.ulima.randitos.blogapp;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bson.Document;
import spark.ModelAndView;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.stop;

public class Main {

    public static void main(String[] args) {

        port(obtenerPuertoHeroku());

        get("/parar", (req, resp) -> {
            stop();
            return "";
        });

        post("/listar_post", (req, resp) -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id_menu", "listar_post");
            map.put("tipo_usuario", "publicador");

            //jala del login.html metodo post action listar_post
            ConexionMongo gestor = new ConexionMongo();

            String usuario = req.queryParams("usuario");
            String contrasena = req.queryParams("contrasena");

            Document filtro = new Document();
            filtro.append("usuario", usuario);
            filtro.append("contrasena", contrasena);

            Document myDoc = gestor.getCollection().find(filtro).first();

            //System.out.println(usuario);
            //System.out.println(contrasena);
            if (myDoc == null) {
                return new ModelAndView(null, "login.html");

            } else {
                return new ModelAndView(map, "main.html");
            }
        }, new Jinja2TemplateEngine());

        get("/add_post", (req, resp) -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id_menu", "add_post");
            map.put("tipo_usuario", "publicador");

            return new ModelAndView(map, "add-post.html");
            // se va al html --> add-post.html
        }, new Jinja2TemplateEngine());

        get("/help", (req, resp) -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id_menu", "help");
            map.put("tipo_usuario", "publicador");

            return new ModelAndView(map, "help.html");
        }, new Jinja2TemplateEngine());

        get("/ver", (req, resp) -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id_menu", "listar_post");
            map.put("tipo_usuario", "visitante");
            return new ModelAndView(map, "main.html");
        }, new Jinja2TemplateEngine());

        get("/", (req, resp) -> {
            return new ModelAndView(null, "login.html");
        }, new Jinja2TemplateEngine());

    }

    static int obtenerPuertoHeroku() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

}
