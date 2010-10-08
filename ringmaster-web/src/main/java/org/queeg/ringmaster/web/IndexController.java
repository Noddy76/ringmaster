package org.queeg.ringmaster.web;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.queeg.ringmaster.Ringmaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

@Controller
public class IndexController {
  @Autowired
  private Ringmaster ringmaster;

  @RequestMapping( { "index.html", "/" })
  public View index() {
    return new View() {

      @Override
      public String getContentType() {
        return "text/plain";
      }

      @Override
      public void render(Map<String, ?> model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PrintWriter pw = resp.getWriter();
        pw.print("Hello! (" + ringmaster + ")\n");
      }
    };
  }
}
