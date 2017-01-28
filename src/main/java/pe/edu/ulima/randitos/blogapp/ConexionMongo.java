/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.ulima.randitos.blogapp;

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

    public ConexionMongo() {
        mClient = new MongoClient(new MongoClientURI("mongodb://pepe:pepe@ds133249.mlab.com:33249/tesisuldb"));
    }

    public List<Usuario> obtener() {
        //  mc=new MongoClient("localhost",27017);

        MongoDatabase db = mClient.getDatabase("tesisuldb");
        MongoCollection colEval
                = db.getCollection("usuario");

        List<Usuario> usuarios = new ArrayList<>();
        ArrayList<Document> usuDocs
                = (ArrayList<Document>) colEval.find().into(new ArrayList());
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

}
