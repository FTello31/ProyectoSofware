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
    private MongoCollection colUsu;
    private MongoDatabase db;
    private MongoCollection<Document> collection;
    private MongoCollection colTema;
    private MongoCollection colReunion;

    public ConexionMongo() {
        mClient = new MongoClient(new MongoClientURI("mongodb://pepe:pepe@ds147979.mlab.com:47979/tesisuldb"));
        db = mClient.getDatabase("tesisuldb");
        colUsu = db.getCollection("usuario");
        collection = db.getCollection("usuario");
        colTema = db.getCollection("tema");
        colReunion = db.getCollection("reunion");
    }

    public List<Usuario> obtenerUsuario() {
        List<Usuario> usuarios = new ArrayList<>();
        ArrayList<Document> usuDocs
                = (ArrayList<Document>) colUsu.find().into(new ArrayList());
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
    
    
    
    

    public MongoCollection getColUsu() {
        return colUsu;
    }

    public MongoDatabase getDb() {
        return db;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public MongoCollection getColTema() {
        return colTema;
    }

    public void setColTema(MongoCollection colTema) {
        this.colTema = colTema;
    }
    
    
    public void setColReunion(MongoCollection colReunion) {
        this.colReunion = colReunion;
    }

    public MongoCollection getColReunion() {
        return colReunion;
    }
}
