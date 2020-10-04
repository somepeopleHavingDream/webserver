//import connector.ConnectorUtils;
//import connector.HttpStatus;
//import connector.Request;

import org.yangxin.webserver.connector.ConnectorUtils;
import org.yangxin.webserver.connector.HttpStatus;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServlet implements Servlet {

  @Override
  public void init(ServletConfig servletConfig) {

  }

  @Override
  public ServletConfig getServletConfig() {
    return null;
  }

  @Override
  public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
    PrintWriter out = servletResponse.getWriter();
    out.println(ConnectorUtils.renderStatus(HttpStatus.SC_OK));
    out.println("What time is it now?");
    out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
          .format(new Date()));
  }

  @Override
  public String getServletInfo() {
    return null;
  }

  @Override
  public void destroy() {

  }
}
