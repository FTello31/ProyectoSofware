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
   

    public ConexionMongo() {
        mClient = new MongoClient(new MongoClientURI("mongodb://pepe:pepe@ds133249.mlab.com:33249/tesisuldb"));
        db = mClient.getDatabase("tesisuldb");
        colUsu = db.getCollection("usuario");
        collection = db.getCollection("usuario");
        
    }

    public List<Usuario> obtener() {
        List<Usuario> usuarios = new ArrayList<>();
        ArrayList<Document> usuDocs
                = (ArrayList<Document>) colUsu.find().into(new ArrayList());
        usuDocs.stream().forEach((reg) -> {
            usuarios.add(
                    new Usuario(
                            reg.getString("usuario"),
                            reg.getString("contrasena")
                    )
            );
        });
        return usuarios;

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
    
    
    
    
    
    
    
    
}
