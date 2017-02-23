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

    
    private String reunion;
    private String obAlumno;
    private String obAsesor;
    private String estado;
    private String autor;
    private String ttesis;
    private String fecha;
    private String _id;

    public Reunion(String reunion, String obAlumno, String obAsesor, String estado, String autor, String ttesis, String fecha, String _id) {
        this.reunion = reunion;
        this.obAlumno = obAlumno;
        this.obAsesor = obAsesor;
        this.estado = estado;
        this.autor = autor;
        this.ttesis = ttesis;
        this.fecha = fecha;
        this._id = _id;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

   

    public String getReunion() {
        return reunion;
    }

    public void setReunion(String reunion) {
        this.reunion = reunion;
    }

    public String getObAlumno() {
        return obAlumno;
    }

    public void setObAlumno(String obAlumno) {
        this.obAlumno = obAlumno;
    }

    public String getObAsesor() {
        return obAsesor;
    }

    public void setObAsesor(String obAsesor) {
        this.obAsesor = obAsesor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
    
    
    
    
    
}