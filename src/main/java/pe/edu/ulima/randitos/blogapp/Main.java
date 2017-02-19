/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.ulima.randitos.blogapp;


import java.util.HashMap;
import org.bson.Document;

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

        get("/main", (req, resp) -> {
            map.get("tipo_usuario");
            ConexionMongo gestor = new ConexionMongo();
            map.put("reunion", gestor.obtenerReuniones());

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

            map.put("reunion", gestor.obtenerReuniones());
           
            
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
            String obAsesor = req.queryParams("obAsesor");
            String obAlumno = req.queryParams("obAlumno");

            Document myDoc = new Document();
            myDoc.append("reunion", reunion);
            myDoc.append("obAsesor", obAsesor);
            myDoc.append("obAlumno", obAlumno);
            myDoc.append("estado", "activo");

            gestor.getColReunion().insertOne(myDoc);

            return new ModelAndView(map, "registrarReunion.html");

            //System.out.println(usuario);
            //System.out.println(contrasena);
        }, new Jinja2TemplateEngine());

        get("/asesoriaTesis", (req, resp) -> {
            map.get("tipo_usuario");
            ConexionMongo gestor = new ConexionMongo();

            map.put("temas", gestor.obtenerTemas());
            //System.out.println("\n\n\n");
            return new ModelAndView(map, "asesoriaTesis.html");
        }, new Jinja2TemplateEngine());
        
        
        get("/registroReunionProfe", (req, resp) -> {
            map.get("tipo_usuario");
            ConexionMongo gestor = new ConexionMongo();
            map.put("reuniones", gestor.obtenerReuniones());
            //System.out.println("\n\n\n");
            return new ModelAndView(map, "registroReunionProfe.html");
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
            map.put("tesis", gestor.obtenerTesis());
            //System.out.println("\n\n\n");

            return new ModelAndView(map, "repositorioTesis.html");
        }, new Jinja2TemplateEngine());
        
        
        
         get("/visualizarFeedback", (req, resp) -> {
            map.get("tipo_usuario");
            ConexionMongo gestor = new ConexionMongo();
            map.put("feeds", gestor.obtenerFeedback());
            //System.out.println("\n\n\n");
            return new ModelAndView(map, "visualizarFeedback.html");
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
