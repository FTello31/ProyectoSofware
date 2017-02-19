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
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
import pe.edu.ulima.randitos.bean.Tema;
import pe.edu.ulima.randitos.bean.Tesis;
import pe.edu.ulima.randitos.bean.Usuario;
import spark.ModelAndView;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.stop;

public class Main {

    public static void main(String[] args) {
        port(obtenerPuertoHeroku());
        HashMap<String, Object> map = new HashMap<>();

        get("/parar", (req, resp) -> {
            stop();
            return "";
        });

        get("/", (req, resp) -> {
            return new ModelAndView(null, "login.html");
        }, new Jinja2TemplateEngine());

        get("/home", (req, resp) -> {
            map.get("tipo_usuario");
            return new ModelAndView(map, "main.html");
        }, new Jinja2TemplateEngine());

        post("/main", (req, resp) -> {

            //jala del login.html metodo post action listar_post
            ConexionMongo gestor = new ConexionMongo();

            String usuario = req.queryParams("usuario");
            String password = req.queryParams("password");

            Document filtro = new Document();
            filtro.append("usuario", usuario);
            filtro.append("password", password);

            Document myDoc = gestor.getCollection().find(filtro).first();
            map.put("tipo_usuario", myDoc.getString("tipo"));
            map.put("nombre", myDoc.getString("nombre"));
            map.put("correo", myDoc.getString("correo"));
            map.put("celular", myDoc.getString("celular"));
            
            //System.out.println(usuario);
            //System.out.println(contrasena);
            if (myDoc == null) {
                return new ModelAndView(null, "login.html");

            } else {
                return new ModelAndView(map, "main.html");
            }
        }, new Jinja2TemplateEngine());

        get("/buscarAsesor", (req, resp) -> {
            map.get("tipo_usuario");
            return new ModelAndView(map, "registrarTesis.html");
        }, new Jinja2TemplateEngine());

        post("/registrarTesis", (req, resp) -> {

            map.get("tipo_usuario");

            //jala del login.html metodo post action listar_post
            ConexionMongo gestor = new ConexionMongo();

            String ttesis = req.queryParams("ttesis");
            String escuela = req.queryParams("escuela");
            String etema = req.queryParams("etema");
            String asesor = req.queryParams("asesor");

            Document myDoc = new Document();
            myDoc.append("ttesis", ttesis);
            myDoc.append("escuela", escuela);
            myDoc.append("etema", etema);
            myDoc.append("asesor", asesor);
            myDoc.append("estado", "activo");

            gestor.getColTema().insertOne(myDoc);

            return new ModelAndView(map, "registrarTesis.html");

            //System.out.println(usuario);
            //System.out.println(contrasena);
        }, new Jinja2TemplateEngine());

        get("/registrarReunion", (req, resp) -> {
            map.get("tipo_usuario");
            return new ModelAndView(map, "registrarReunion.html");
        }, new Jinja2TemplateEngine());

        post("/registrarReunion", (req, resp) -> {

            map.get("tipo_usuario");

            //jala del login.html metodo post action listar_post
            ConexionMongo gestor = new ConexionMongo();

            String reunion = req.queryParams("reunion");
            String ob_asesor = req.queryParams("ob_asesor");
            String ob_alumno = req.queryParams("ob_alumno");

            Document myDoc = new Document();
            myDoc.append("reunion", reunion);
            myDoc.append("ob_asesor", ob_asesor);
            myDoc.append("ob_alumno", ob_alumno);
            myDoc.append("estado", "open");

            gestor.getColReunion().insertOne(myDoc);

            return new ModelAndView(map, "registrarReunion.html");

            //System.out.println(usuario);
            //System.out.println(contrasena);
        }, new Jinja2TemplateEngine());

        get("/asesoriaTesis", (req, resp) -> {
            map.get("tipo_usuario");
            ConexionMongo gestor = new ConexionMongo();
            List<Tema> temas = new ArrayList<>();

            for (Document cur : gestor.getColTema().find()) {
                temas.add(new Tema(cur.getString("ttesis"),
                        cur.getString("escuela"),
                        cur.getString("etema"),
                        cur.getString("asesor")
                ));
            }
            map.put("temas", temas);
            //System.out.println("\n\n\n");
            return new ModelAndView(map, "asesoriaTesis.html");
        }, new Jinja2TemplateEngine());

      
        
        
        get("/verificarActasReunion", (req, resp) -> {
            map.get("tipo_usuario");
            return new ModelAndView(map, "verificarActasReunion.html");
        }, new Jinja2TemplateEngine());

        get("/feedbackProfe", (req, resp) -> {
            map.get("tipo_usuario");
            return new ModelAndView(map, "feedbackProfe.html");
        }, new Jinja2TemplateEngine());

        get("/repositorioTesis", (req, resp) -> {
            map.get("tipo_usuario");
    
            ConexionMongo gestor = new ConexionMongo();
            List<Tesis> tesis = new ArrayList<>();

            for (Document cur : gestor.getColtesis().find()) {
                tesis.add(new Tesis(cur.getString("titulo"),
                        cur.getString("autor"),
                        cur.getString("fecha"),
                        cur.getString("facultad"),
                        cur.getString("estado"),
                        cur.getString("archivo")
                         
                ));
            }
            map.put("tesis", tesis);
            //System.out.println("\n\n\n");
            
            
            
            
            
            
            
            
            return new ModelAndView(map, "repositorioTesis.html");
        }, new Jinja2TemplateEngine());

        /*get("/add_post", (req, resp) -> {

            map.put("tipo_usuario", "publicador");

            return new ModelAndView(map, "add-post.html");
            // se va al html --> add-post.html
        }, new Jinja2TemplateEngine());

        get("/help", (req, resp) -> {

            map.put("tipo_usuario", "publicador");

            return new ModelAndView(map, "help.html");
        }, new Jinja2TemplateEngine());

        get("/ver", (req, resp) -> {
            map.put("tipo_usuario", "visitante");
            return new ModelAndView(map, "main.html");
        }, new Jinja2TemplateEngine());
         */
    }

    static int obtenerPuertoHeroku() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

}
