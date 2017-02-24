/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.ulima.randitos.blogapp;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
            ConexionMongo gestor = new ConexionMongo();

            String usuario = req.queryParams("usuario");
            String password = req.queryParams("password");

            Document filtro = new Document();
            filtro.append("usuario", usuario);
            filtro.append("password", password);

            //saca el usuario que entro puede ser alumno , profe
            Document myDoc = gestor.getColUsu().find(filtro).first();
            map.put("tipo_usuario", myDoc.getString("tipo"));
            map.put("nombre", myDoc.getString("nombre"));
            map.put("usuario", myDoc.getString("usuario"));
            map.put("asesorado", myDoc.getString("asesorado"));

            //si es alumno 
            if (myDoc.getString("tipo").equalsIgnoreCase("alumno")) {
                Document doc
                        = gestor.getColUsu().find(new Document("usuario", myDoc.getString("asesor"))).first();
                //buscamos los datos del profe
                map.put("nombreAse", doc.getString("nombre"));
                map.put("celular", doc.getString("celular"));
                map.put("disponibilidad", doc.getString("disponibilidad"));
                map.put("correo", doc.getString("correo"));

                //profe
            } else {
                Document doc
                        = gestor.getColUsu().find(new Document("nombre", myDoc.getString("asesorado"))).first();
                map.put("ttesis", doc.getString("ttesis"));
            }

            map.put("reunion", gestor.obtenerReuniones());

            if (myDoc == null) {
                return new ModelAndView(null, "login.html");

            } else {
                return new ModelAndView(map, "main.html");
            }
        }, new Jinja2TemplateEngine());

        get("/buscarAsesor", (req, resp) -> {
            map.get("tipo_usuario");
            return new ModelAndView(map, "buscarAsesor.html");
        }, new Jinja2TemplateEngine());

        post("/registrarTema", (req, resp) -> {

            map.get("tipo_usuario");
            ConexionMongo gestor = new ConexionMongo();

            String ttesis = req.queryParams("ttesis");
            String escuela = req.queryParams("escuela");
            String etema = req.queryParams("etema");
            String asesor = req.queryParams("asesor");
            String autor = (String) map.get("nombre");

            Document myDoc = new Document();
            myDoc.append("ttesis", ttesis);
            myDoc.append("escuela", escuela);
            myDoc.append("etema", etema);
            myDoc.append("asesor", asesor);
            myDoc.append("estado", "activo");
            myDoc.append("autor", autor);

            gestor.getColTema().insertOne(myDoc);

            return new ModelAndView(map, "buscarAsesor.html");

        }, new Jinja2TemplateEngine());

        get("/asesoriaTesis", (req, resp) -> {
            map.get("tipo_usuario");
            ConexionMongo gestor = new ConexionMongo();
            map.put("temas", gestor.obtenerTemas((String) map.get("usuario")));

            return new ModelAndView(map, "asesoriaTesis.html");
        }, new Jinja2TemplateEngine());

        post("/asesoriaTesis", (req, resp) -> {
            map.get("tipo_usuario");
            ConexionMongo gestor = new ConexionMongo();
            map.put("temas", gestor.obtenerTemas((String) map.get("usuario")));

            String aprobar = req.queryParams("aprobar");
            String[] cadena = aprobar.split("&");
            String ttesis = cadena[0];
            String escuela = cadena[1];
            String etema = cadena[2];
            String asesor = cadena[3];
            String autor = cadena[4];

            Document myDoc = new Document();
            myDoc.append("ttesis", ttesis);
            myDoc.append("autor", autor);
            myDoc.append("fecha", gestor.obtenerFecha());
            myDoc.append("facultad", escuela);
            myDoc.append("etema", etema);
            myDoc.append("asesor", asesor);

            gestor.getColtesis().insertOne(myDoc);

            Document filtro = new Document();
            filtro.append("ttesis", ttesis);
            gestor.getColTema().updateOne(filtro, new Document("$set", new Document("estado", "desactivado")));

            Document filtro2 = new Document();
            filtro2.append("nombre", autor);

            Document doc1 = new Document();
            doc1.append("asesor", asesor);
            doc1.append("ttesis", ttesis);

            gestor.getColUsu().updateOne(filtro2, new Document("$set", doc1));

            Document filtro3 = new Document();
            filtro3.append("usuario", asesor);

            Document doc = new Document();
            doc.append("asesorado", autor);

            gestor.getColUsu().updateOne(filtro3, new Document("$set", doc));

            return new ModelAndView(map, "asesoriaTesis.html");
        }, new Jinja2TemplateEngine());

        get("/registrarReunion", (req, resp) -> {
            map.get("tipo_usuario");
            return new ModelAndView(map, "registrarReunion.html");
        }, new Jinja2TemplateEngine());

        //alumnos
        post("/registrarReunion", (req, resp) -> {
            map.get("tipo_usuario");
            ConexionMongo gestor = new ConexionMongo();

            String reunion = req.queryParams("reunion");
            String obAlumno = req.queryParams("obAlumno");

            Document myDoc = new Document();
            myDoc.append("reunion", reunion);
            myDoc.append("obAlumno", obAlumno);
            myDoc.append("obAsesor", "");
            myDoc.append("estado", "activo");
            myDoc.append("autor", map.get("nombre"));
            myDoc.append("ttesis", map.get("ttesis"));
            myDoc.append("fecha", gestor.obtenerFecha());

            gestor.getColReunion().insertOne(myDoc);

            return new ModelAndView(map, "registrarReunion.html");
        }, new Jinja2TemplateEngine());

        //profes
        get("/registroReunionProfe", (req, resp) -> {
            map.get("tipo_usuario");
            ConexionMongo gestor = new ConexionMongo();
            map.put("reuniones", gestor.obtenerReuniones());

            return new ModelAndView(map, "registroReunionProfe.html");
        }, new Jinja2TemplateEngine());

        post("/registroReunionProfe", (req, resp) -> {
            map.get("tipo_usuario");
            ConexionMongo gestor = new ConexionMongo();
            map.put("reuniones", gestor.obtenerReuniones());

            String[] obAsesor = req.queryParamsValues("obAsesor");
            String firmar = req.queryParams("firmar");
            String[] id = req.queryParamsValues("id");

            String[] cadena = firmar.split("&");
            String autor = cadena[0];
            String ttesis = cadena[1];
            String reunion = cadena[2];
            String obAlumno = cadena[3];

            Document filtro = new Document();
            filtro.append("autor", autor);
            filtro.append("ttesis", ttesis);
            filtro.append("reunion", reunion);

            int posicion = 0;
            String dato = null;
            for (int i = 0; i < obAsesor.length; i++) {
                if (!obAsesor[i].isEmpty()) {
                    posicion = i;
                    dato = obAsesor[i];
                }
            }
            System.out.println(dato);

            Document myDoc = new Document();
            myDoc.append("obAsesor", dato);
            myDoc.append("estado", "desactivado");

            gestor.getColReunion().updateOne(filtro, new Document("$set", myDoc));

            return new ModelAndView(map, "registroReunionProfe.html");
        }, new Jinja2TemplateEngine());

        get("/verificarActasReunion", (req, resp) -> {
            map.get("tipo_usuario");
            ConexionMongo gestor = new ConexionMongo();
            map.put("actas", gestor.obtenerActas());

            return new ModelAndView(map, "verificarActasReunion.html");
        }, new Jinja2TemplateEngine());

        get("/feedbackProfe", (req, resp) -> {
            map.get("tipo_usuario");
            ConexionMongo gestor = new ConexionMongo();
            map.put("tesis", gestor.obtenerTesis((String) map.get("usuario")));

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
