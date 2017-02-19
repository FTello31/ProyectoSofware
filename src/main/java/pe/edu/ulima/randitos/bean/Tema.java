/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.ulima.randitos.bean;

/**
 *
 * @author Fernando
 */
public class Tema {

    private String ttesis;
    private String escuela;
    private String etema;
    private String asesor;

    private String autor;

    public Tema() {
    }

    public Tema(String ttesis, String escuela, String etema, String asesor, String autor) {
        this.ttesis = ttesis;
        this.escuela = escuela;
        this.etema = etema;
        this.asesor = asesor;
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTtesis() {
        return ttesis;
    }

    public void setTtesis(String ttesis) {
        this.ttesis = ttesis;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getEtema() {
        return etema;
    }

    public void setEtema(String etema) {
        this.etema = etema;
    }

    public String getAsesor() {
        return asesor;
    }

    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }

}
