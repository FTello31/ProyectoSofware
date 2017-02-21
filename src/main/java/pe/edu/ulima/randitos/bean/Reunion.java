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
public class Reunion {

    private String autor;
    private String ttesis;
    private String reunion;
    private String obAsesor;
    private String obAlumno;
    private String estado;

    public Reunion(String autor, String ttesis, String reunion, String obAsesor, String obAlumno, String estado) {
        this.autor = autor;
        this.ttesis = ttesis;
        this.reunion = reunion;
        this.obAsesor = obAsesor;
        this.obAlumno = obAlumno;
        this.estado = estado;
    }

 
    public String getTtesis() {
        return ttesis;
    }

    public void setTtesis(String ttesis) {
        this.ttesis = ttesis;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


    public String getReunion() {
        return reunion;
    }

    public void setReunion(String reunion) {
        this.reunion = reunion;
    }

    public String getObAsesor() {
        return obAsesor;
    }

    public void setObAsesor(String obAsesor) {
        this.obAsesor = obAsesor;
    }

    public String getObAlumno() {
        return obAlumno;
    }

    public void setObAlumno(String obAlumno) {
        this.obAlumno = obAlumno;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
