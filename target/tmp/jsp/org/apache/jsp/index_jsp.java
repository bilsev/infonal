package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"tr\">\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("<title>Kullanıcı Yönetimi Uygulaması</title>\n");
      out.write("<meta charset=\"utf-8\">\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\"\n");
      out.write("\thref=\"images/css/humanity/jquery-ui-1.10.4.custom.min.css\" />\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\n");
      out.write("\thref=\"images/css/pnotify.custom.min.css\">\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" href=\"images/css/styles.css\" />\n");
      out.write("\n");
      out.write("<script src=\"images/js/jquery-1.10.2.js\"></script>\n");
      out.write("<script src=\"images/js/jquery-ui-1.10.4.custom.min.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("<script src=\"images/js/app.js\"></script>\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\t<div class=\"body-container\">\n");
      out.write("\n");
      out.write("\t\t<div id=\"dialog-form\" title=\"Create new user\">\n");
      out.write("\t\t\t<p class=\"validateTips\">Tüm alanları doldurunuz.</p>\n");
      out.write("\n");
      out.write("\t\t\t<form id=\"formCreateUser\">\n");
      out.write("\t\t\t\t<fieldset id=\"fieldsetCreateUser\">\n");
      out.write("\t\t\t\t\t<label for=\"firstname\">İsim</label> <input type=\"text\"\n");
      out.write("\t\t\t\t\t\tname=\"firstname\" id=\"firstname\"\n");
      out.write("\t\t\t\t\t\tclass=\"text ui-widget-content ui-corner-all\"> <label\n");
      out.write("\t\t\t\t\t\tfor=\"lastname\">Soyisim</label> <input type=\"text\" name=\"lastname\"\n");
      out.write("\t\t\t\t\t\tid=\"lastname\" value=\"\" class=\"text ui-widget-content ui-corner-all\">\n");
      out.write("\t\t\t\t\t<label for=\"phone\">Telefon</label> <input type=\"text\"\n");
      out.write("\t\t\t\t\t\tname=\"phone\" id=\"phone\" value=\"\"\n");
      out.write("\t\t\t\t\t\tclass=\"text ui-widget-content ui-corner-all\">\n");
      out.write("\t\t\t\t\t<label for=\"captcha\"><img src=\"captcha.jpg\" /></label> <input type=\"text\"\n");
      out.write("\t\t\t\t\t\tname=\"captcha\" id=\"captcha\" value=\"\"\n");
      out.write("\t\t\t\t\t\tclass=\"text ui-widget-content ui-corner-all\">\n");
      out.write("\t\t\t\t</fieldset>\n");
      out.write("\t\t\t</form>\n");
      out.write("\t\t</div>\n");
      out.write("\n");
      out.write("\t\t<div id=\"users-contain\" class=\"ui-widget\">\n");
      out.write("\t\t\t<h1>Kullanıcılar:</h1>\n");
      out.write("\t\t\t<table id=\"users\" class=\"ui-widget ui-widget-content\">\n");
      out.write("\t\t\t\t<thead>\n");
      out.write("\t\t\t\t\t<tr class=\"ui-widget-header \">\n");
      out.write("\t\t\t\t\t\t<th>İsim</th>\n");
      out.write("\t\t\t\t\t\t<th>Soyisim</th>\n");
      out.write("\t\t\t\t\t\t<th>Telefon</th>\n");
      out.write("\t\t\t\t\t\t<th>İşlem</th>\n");
      out.write("\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t</thead>\n");
      out.write("\t\t\t\t<tbody>\n");
      out.write("\t\t\t\t</tbody>\n");
      out.write("\t\t\t</table>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<button id=\"create-user\">Yeni Kullanıcı Oluştur</button>\n");
      out.write("\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"modal\"></div>\n");
      out.write("\t\n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
