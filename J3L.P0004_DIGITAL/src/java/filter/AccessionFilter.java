/*
 * Copyright(C) 2005,  FPT University.
 * J3.L.P0004:
 * Digital News
 *
 * Record of change:
 * DATE          Version             AUTHOR             DESCRIPTION
 * 2021-05-16     1.0              TungCTHE141487      First Implement
 */
package filter;

import dao.IDigitalDAO;
import dao.impl.DigitalNewsDAO;
import entity.Digital;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Represent check accession of user, cannot access when page contain wrong file
 * except error page
 *
 * @author TungCTHE141487
 */
@WebFilter(filterName = "AccessionFilter", urlPatterns = {"/*"})
public class AccessionFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    /**
     * constructor AccessionFilter
     */
    public AccessionFilter() {
    }
    /**
     * Accession Filter do it before Processing
     * 
     * @param request The servlet request is the <code> HttpServletRequest
     * </code> object
     * @param response The servlet response is the <code> HttpServletResponse
     * </code> object
     * @throws IOException
     * @throws ServletException 
     */
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AccessionFilter:DoBeforeProcessing");
        }

    }
    /**
     * Accession Filter do it after Processing
     * 
     * @param request The servlet request is the <code> HttpServletRequest
     * </code> object
     * @param response The servlet response is the <code> HttpServletResponse
     * </code> object
     * @throws IOException
     * @throws ServletException 
     */
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AccessionFilter:DoAfterProcessing");
        }

    }

    /**
     * Do not allow user access to page jsp, it will return user to the Error
     * page
     *
     * @param request The servlet request is the <code> HttpServletRequest
     * </code> object
     * @param response The servlet response is the <code> HttpServletResponse
     * </code> object
     * @param chain The filter chain we are processing in the filter chain, when
     * thread ens, it will invokes the target servlet
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("AccessionFilter:doFilter()");
        }

        doBeforeProcessing(request, response);
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = httpRequest.getServletPath();

        try {
            IDigitalDAO digitalDAO = new DigitalNewsDAO();
            List<Digital> list;
            list = digitalDAO.getTop(6);
            // get the newest news
            Digital top1 =  list.remove(0);
            request.setAttribute("top1", top1);
            // get top 5 newss
           
            request.setAttribute("top5", list);
        } catch (Exception ex) {
            httpRequest.getRequestDispatcher("Error.jsp").forward(request, response);
            Logger.getLogger(AccessionFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        // check url access
        if (url.endsWith(".jsp")) {
            request.setAttribute("errormess", "You cannot access this page");
            httpRequest.getRequestDispatcher("Error.jsp").forward(request, response);
        }

        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AccessionFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AccessionFilter()");
        }
        StringBuffer sb = new StringBuffer("AccessionFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
