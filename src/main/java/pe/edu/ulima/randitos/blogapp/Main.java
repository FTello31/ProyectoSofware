/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.ulima.randitos.blogapp;

import com.google.gson.Gson;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bson.Document;
import spark.ModelAndView;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.stop;

public class Main {

    public static void main(String[] args) {

        get("/parar", (req, resp) -> {
            stop();
            return "";
        });

        get("/listar_post", (req, resp) -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id_menu", "listar_post");
            map.put("tipo_usuario", "publicador");

            return new ModelAndView(map, "main.html");
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

        get("/login", (req, resp) -> {
            ConexionMongo gestor = new ConexionMongo();
            
            
            try {
                System.out.println("\n*****************************");
                System.out.println(new Gson().toJson(gestor.obtener()));
                System.out.println("*****************************\n");
                
                
                
                
                
            } catch (Exception e) {
                //  return e.getMessage();
            }

            return new ModelAndView(null, "login.html");
        }, new Jinja2TemplateEngine());


    }

}
