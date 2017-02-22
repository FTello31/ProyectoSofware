/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.ulima.randitos.blogapp;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.bson.Document;
import pe.edu.ulima.randitos.bean.Feedback;
import pe.edu.ulima.randitos.bean.Reunion;
import pe.edu.ulima.randitos.bean.Tema;
import pe.edu.ulima.randitos.bean.Tesis;
import pe.edu.ulima.randitos.bean.Usuario;

/**
 *
 * @author Fernando
 */
public class ConexionMongo {

    private final MongoClient mClient;
    //private MongoCollection colUsu;
    private MongoDatabase db;
    private MongoCollection<Document> colUsu;
    private MongoCollection<Document> colTema;
    private MongoCollection<Document> colReunion;
    private MongoCollection<Document> coltesis;
    private MongoCollection<Document> colFeed;

    public ConexionMongo() {
        mClient = new MongoClient(new MongoClientURI("mongodb://pepe:pepe@ds157479.mlab.com:57479/ulpruebaaaa"));
        
        //gestor.obtenerTemas
        db = mClient.getDatabase("ulpruebaaaa");

        colUsu = db.getCollection("usuario");
        colTema = db.getCollection("tema");
        colReunion = db.getCollection("reunion");
        coltesis = db.getCollection("tesis");
        colFeed = db.getCollection("feed");
    }

    /*public List<Usuario> obtenerUsuario() {
        List<Usuario> usuarios = new ArrayList<>();
        ArrayList<Document> usuDocs
                = (ArrayList<Document>) collection.find().into(new ArrayList());
        usuDocs.stream().forEach((reg) -> {
            usuarios.add(
                    new Usuario(
                            reg.getString("usuario"),
                            reg.getString("password"),
                            reg.getString("tipo")
                    )
            );
        });
        return usuarios;

    }
    
     */

    public List<Reunion> obtenerActas() {
        List<Reunion> reunion = new ArrayList<>();
        Document filtro = new Document();
        filtro.append("estado", "desactivado");
        
        for (Document cur : getColReunion().find(filtro)) {
            reunion.add(new Reunion(cur.getString("reunion"),
                    cur.getString("obAlumno"),
                    cur.getString("obAsesor"),
                    cur.getString("estado"),
                    cur.getString("autor"),
                    cur.getString("ttesis"),
                    cur.getString("fecha")
            ));
        }
        return reunion;

    }
    
    public List<Reunion> obtenerReuniones() {
        List<Reunion> reunion = new ArrayList<>();
        Document filtro = new Document();
        filtro.append("estado", "activo");
        
        for (Document cur : getColReunion().find(filtro)) {
            reunion.add(new Reunion(cur.getString("reunion"),
                    cur.getString("obAlumno"),
                    cur.getString("obAsesor"),
                    cur.getString("estado"),
                    cur.getString("autor"),
                    cur.getString("ttesis"),
                    cur.getString("fecha")
            ));
        }
        return reunion;

    }
    
    public List<Tema> obtenerTemas() {
        List<Tema> temas = new ArrayList<>();
        Document filtro = new Document();
        filtro.append("estado", "activo");

        for (Document cur : getColTema().find(filtro)) {
            temas.add(new Tema(cur.getString("ttesis"),
                    cur.getString("escuela"),
                    cur.getString("etema"),
                    cur.getString("asesor"),
                    cur.getString("autor")
            ));
        }
        return temas;
    }
    public List<Tema> obtenerTemas(String asesor) {
        List<Tema> temas = new ArrayList<>();
        Document filtro = new Document();
        filtro.append("estado", "activo");
        filtro.append("asesor", asesor);

        for (Document cur : getColTema().find(filtro)) {
            temas.add(new Tema(cur.getString("ttesis"),
                    cur.getString("escuela"),
                    cur.getString("etema"),
                    cur.getString("asesor"),
                    cur.getString("autor")
            ));
        }
        return temas;

    }
    
    public List<Tesis> obtenerTesis() {
        List<Tesis> tesis = new ArrayList<>();

        for (Document cur : getColtesis().find()) {
            tesis.add(new Tesis(cur.getString("ttesis"),
                    cur.getString("autor"),
                    cur.getString("fecha"),
                    cur.getString("facultad"),
                    cur.getString("estado"),
                    cur.getString("archivo")
            ));
        }
        return tesis;
    }

    public List<Tesis> obtenerTesis(String asesor) {
        List<Tesis> tesis = new ArrayList<>();
        
        Document filtro = new Document();
        filtro.append("asesor", asesor);

        for (Document cur : getColtesis().find()) {
            tesis.add(new Tesis(cur.getString("ttesis"),
                    cur.getString("autor"),
                    cur.getString("fecha"),
                    cur.getString("facultad"),
                    cur.getString("estado"),
                    cur.getString("archivo")
            ));
        }
        return tesis;

    }
    
    
    
    
    
    public List<Feedback> obtenerFeedback() {
        List<Feedback> feedback = new ArrayList<>();
        for (Document cur : getColFeed().find()) {
            feedback.add(new Feedback(cur.getString("tesis"),
                    cur.getString("feedback"),
                    cur.getString("calificacion")
            ));
        }
        return feedback;
    }

    public String obtenerFecha() {
        Calendar cal = new GregorianCalendar();
        String dia = Integer.toString(cal.get(Calendar.DATE));
        String mes = Integer.toString(cal.get(Calendar.MONTH));
        String annio = Integer.toString(cal.get(Calendar.YEAR));

        String fecha = dia + "/" + mes + "/" + annio;
        return fecha;
    }

    public MongoCollection<Document> getColFeed() {
        return colFeed;
    }

    public void setColFeed(MongoCollection<Document> colFeed) {
        this.colFeed = colFeed;
    }

    public MongoDatabase getDb() {
        return db;
    }

    public void setDb(MongoDatabase db) {
        this.db = db;
    }

    public MongoCollection<Document> getColUsu() {
        return colUsu;
    }

    public void setColUsu(MongoCollection<Document> colUsu) {
        this.colUsu = colUsu;
    }

    
    public MongoCollection<Document> getColTema() {
        return colTema;
    }

    public void setColTema(MongoCollection<Document> colTema) {
        this.colTema = colTema;
    }

    public MongoCollection<Document> getColReunion() {
        return colReunion;
    }

    public void setColReunion(MongoCollection<Document> colReunion) {
        this.colReunion = colReunion;
    }

    public MongoCollection<Document> getColtesis() {
        return coltesis;
    }

    public void setColtesis(MongoCollection<Document> coltesis) {
        this.coltesis = coltesis;
    }

}
