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
public class Tesis {

    private String titulo;
    private String autor;
    private String fecha;
    private String facultad;
    private String estado;
    private String archivo;

    public Tesis(String titulo, String autor, String fecha, String facultad, String estado, String archivo) {
        this.titulo = titulo;
        this.autor = autor;
        this.fecha = fecha;
        this.facultad = facultad;
        this.estado = estado;
        this.archivo = archivo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    
    
    
    
}
