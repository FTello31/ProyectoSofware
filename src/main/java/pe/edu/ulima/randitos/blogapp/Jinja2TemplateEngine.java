/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.ulima.randitos.blogapp;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.TemplateEngine;

/**
 *
 * @author Administrator
 */
public class Jinja2TemplateEngine extends TemplateEngine {

    public Jinja2TemplateEngine() {
    }

    @Override
    public String render(ModelAndView mav) {
        try {
            String template = Resources.toString(
                    Resources.getResource("templates/" + mav.getViewName()),
                    Charsets.UTF_8
            );
            Jinjava jinjava = new Jinjava();
            return jinjava.render(
                    template,
                    (Map<String, ?>) mav.getModel()
            );
        } catch (Exception ex) {
            Logger logger = LoggerFactory.getLogger(Jinja2TemplateEngine.class);
            logger.error(ex.getMessage());
        }
        return null;
    }

}
