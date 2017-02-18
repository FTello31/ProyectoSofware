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
import java.util.List;
import org.bson.Document;
import pe.edu.ulima.randitos.bean.Tema;
import pe.edu.ulima.randitos.bean.Usuario;

/**
 *
 * @author Fernando
 */
public class ConexionMongo {

    private final MongoClient mClient;
    //private MongoCollection colUsu;
    private MongoDatabase db;
    private MongoCollection<Document> collection;
    private MongoCollection<Document> colTema;
    private MongoCollection<Document> colReunion;

    public ConexionMongo() {
        mClient = new MongoClient(new MongoClientURI("mongodb://pepe:pepe@ds147979.mlab.com:47979/tesisuldb"));
        db = mClient.getDatabase("tesisuldb");
        
        collection = db.getCollection("usuario");
        colTema = db.getCollection("tema");
        colReunion = db.getCollection("reunion");
    }

    public List<Usuario> obtenerUsuario() {
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
    public List<Tema> obtenerTemas() {
        List<Tema> temas = new ArrayList<>();
        ArrayList<Document> usuTemas
                = (ArrayList<Document>) colTema.find().into(new ArrayList());
        usuTemas.stream().forEach((reg) -> {
            temas.add(
                    new Tema(
                            reg.getString("ttesis"),
                            reg.getString("escuela"),
                            reg.getString("etema"),
                            reg.getString("asesor")
                    )
            );
        });
        return temas;

    }


    public MongoDatabase getDb() {
        return db;
    }

    public void setDb(MongoDatabase db) {
        this.db = db;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public void setCollection(MongoCollection<Document> collection) {
        this.collection = collection;
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
    
    
  
}
