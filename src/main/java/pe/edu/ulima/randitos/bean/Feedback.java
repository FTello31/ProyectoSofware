
package pe.edu.ulima.randitos.bean;


public class Feedback {
    
    private String tesis;
    private String feedback;
    private String calificacion;

    public Feedback(String tesis, String feedback, String calificacion) {
        this.tesis = tesis;
        this.feedback = feedback;
        this.calificacion = calificacion;
    }

    public String getTesis() {
        return tesis;
    }

    public void setTesis(String tesis) {
        this.tesis = tesis;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }
    
    
}