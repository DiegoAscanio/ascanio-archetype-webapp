package controllers;

import models.BasicModel;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class BasicControllerTest {

    private BasicController controller;

    @Before
    public void setup() {
        controller = new BasicController();
    }

    private static class MapRequest implements HttpServletRequest {
        private final Map<String, String> params = new HashMap<>();
        public void setParam(String k, String v) { params.put(k, v); }
        @Override public String getParameter(String name) { return params.get(name); }
        // --- minimal stub: throw UnsupportedOperationException for unused methods ---
        @Override public Object getAttribute(String name){throw new UnsupportedOperationException();}
        @Override public java.util.Enumeration<String> getAttributeNames(){throw new UnsupportedOperationException();}
        @Override public String getCharacterEncoding(){throw new UnsupportedOperationException();}
        @Override public void setCharacterEncoding(String env){throw new UnsupportedOperationException();}
        @Override public int getContentLength(){throw new UnsupportedOperationException();}
        @Override public long getContentLengthLong(){throw new UnsupportedOperationException();}
        @Override public String getContentType(){throw new UnsupportedOperationException();}
        @Override public javax.servlet.ServletInputStream getInputStream(){throw new UnsupportedOperationException();}
        @Override public String getProtocol(){throw new UnsupportedOperationException();}
        @Override public String getScheme(){throw new UnsupportedOperationException();}
        @Override public String getServerName(){throw new UnsupportedOperationException();}
        @Override public int getServerPort(){throw new UnsupportedOperationException();}
        @Override public java.io.BufferedReader getReader(){throw new UnsupportedOperationException();}
        @Override public String getRemoteAddr(){throw new UnsupportedOperationException();}
        @Override public String getRemoteHost(){throw new UnsupportedOperationException();}
        @Override public void setAttribute(String name, Object o){throw new UnsupportedOperationException();}
        @Override public void removeAttribute(String name){throw new UnsupportedOperationException();}
        @Override public java.util.Locale getLocale(){throw new UnsupportedOperationException();}
        @Override public java.util.Enumeration<java.util.Locale> getLocales(){throw new UnsupportedOperationException();}
        @Override public boolean isSecure(){throw new UnsupportedOperationException();}
        @Override public javax.servlet.RequestDispatcher getRequestDispatcher(String path){throw new UnsupportedOperationException();}
        @Override public String getRealPath(String path){throw new UnsupportedOperationException();}
        @Override public int getRemotePort(){throw new UnsupportedOperationException();}
        @Override public String getLocalName(){throw new UnsupportedOperationException();}
        @Override public String getLocalAddr(){throw new UnsupportedOperationException();}
        @Override public int getLocalPort(){throw new UnsupportedOperationException();}
        @Override public javax.servlet.ServletContext getServletContext(){throw new UnsupportedOperationException();}
        @Override public javax.servlet.AsyncContext startAsync(){throw new UnsupportedOperationException();}
        @Override public javax.servlet.AsyncContext startAsync(javax.servlet.ServletRequest servletRequest, javax.servlet.ServletResponse servletResponse){throw new UnsupportedOperationException();}
        @Override public boolean isAsyncStarted(){throw new UnsupportedOperationException();}
        @Override public boolean isAsyncSupported(){throw new UnsupportedOperationException();}
        @Override public javax.servlet.AsyncContext getAsyncContext(){throw new UnsupportedOperationException();}
        @Override public javax.servlet.DispatcherType getDispatcherType(){throw new UnsupportedOperationException();}
        @Override public String getAuthType(){throw new UnsupportedOperationException();}
        @Override public javax.servlet.http.Cookie[] getCookies(){throw new UnsupportedOperationException();}
        @Override public long getDateHeader(String name){throw new UnsupportedOperationException();}
        @Override public String getHeader(String name){throw new UnsupportedOperationException();}
        @Override public java.util.Enumeration<String> getHeaders(String name){throw new UnsupportedOperationException();}
        @Override public java.util.Enumeration<String> getHeaderNames(){throw new UnsupportedOperationException();}
        @Override public int getIntHeader(String name){throw new UnsupportedOperationException();}
        @Override public String getMethod(){throw new UnsupportedOperationException();}
        @Override public String getPathInfo(){throw new UnsupportedOperationException();}
        @Override public String getPathTranslated(){throw new UnsupportedOperationException();}
        @Override public String getContextPath(){throw new UnsupportedOperationException();}
        @Override public String getQueryString(){throw new UnsupportedOperationException();}
        @Override public String getRemoteUser(){throw new UnsupportedOperationException();}
        @Override public boolean isUserInRole(String role){throw new UnsupportedOperationException();}
        @Override public java.security.Principal getUserPrincipal(){throw new UnsupportedOperationException();}
        @Override public String getRequestedSessionId(){throw new UnsupportedOperationException();}
        @Override public String getRequestURI(){throw new UnsupportedOperationException();}
        @Override public StringBuffer getRequestURL(){throw new UnsupportedOperationException();}
        @Override public String getServletPath(){throw new UnsupportedOperationException();}
        @Override public javax.servlet.http.HttpSession getSession(boolean create){throw new UnsupportedOperationException();}
        @Override public javax.servlet.http.HttpSession getSession(){throw new UnsupportedOperationException();}
        @Override public String changeSessionId(){throw new UnsupportedOperationException();}
        @Override public boolean isRequestedSessionIdValid(){throw new UnsupportedOperationException();}
        @Override public boolean isRequestedSessionIdFromCookie(){throw new UnsupportedOperationException();}
        @Override public boolean isRequestedSessionIdFromURL(){throw new UnsupportedOperationException();}
        @Override public boolean isRequestedSessionIdFromUrl(){throw new UnsupportedOperationException();}
        @Override public boolean authenticate(HttpServletResponse response){throw new UnsupportedOperationException();}
        @Override public void login(String username, String password){throw new UnsupportedOperationException();}
        @Override public void logout(){throw new UnsupportedOperationException();}
        @Override public java.util.Collection<javax.servlet.Part> getParts(){throw new UnsupportedOperationException();}
        @Override public javax.servlet.Part getPart(String name){throw new UnsupportedOperationException();}
        @Override public <T extends javax.servlet.http.HttpUpgradeHandler> T upgrade(Class<T> handlerClass){throw new UnsupportedOperationException();}
        @Override public java.util.Map<String, String[]> getParameterMap(){throw new UnsupportedOperationException();}
        @Override public java.util.Enumeration<String> getParameterNames(){throw new UnsupportedOperationException();}
        @Override public String[] getParameterValues(String name){throw new UnsupportedOperationException();}
    }

    private static class WriterResponse implements HttpServletResponse {
        private final StringWriter buffer = new StringWriter();
        private final PrintWriter writer = new PrintWriter(buffer);
        private int status = HttpServletResponse.SC_OK;
        private String contentType;
        @Override public void setStatus(int sc) { this.status = sc; }
        @Override public int getStatus() { return status; }
        @Override public void setContentType(String type) { this.contentType = type; }
        @Override public String getContentType() { return contentType; }
        @Override public PrintWriter getWriter() { return writer; }
        public String body() { writer.flush(); return buffer.toString(); }
        // --- minimal stub ---
        @Override public void addCookie(javax.servlet.http.Cookie cookie){throw new UnsupportedOperationException();}
        @Override public boolean containsHeader(String name){throw new UnsupportedOperationException();}
        @Override public String encodeURL(String url){throw new UnsupportedOperationException();}
        @Override public String encodeRedirectURL(String url){throw new UnsupportedOperationException();}
        @Override public String encodeUrl(String url){throw new UnsupportedOperationException();}
        @Override public String encodeRedirectUrl(String url){throw new UnsupportedOperationException();}
        @Override public void sendError(int sc, String msg){this.status = sc;}
        @Override public void sendError(int sc){this.status = sc;}
        @Override public void sendRedirect(String location){throw new UnsupportedOperationException();}
        @Override public void setDateHeader(String name, long date){throw new UnsupportedOperationException();}
        @Override public void addDateHeader(String name, long date){throw new UnsupportedOperationException();}
        @Override public void setHeader(String name, String value){throw new UnsupportedOperationException();}
        @Override public void addHeader(String name, String value){throw new UnsupportedOperationException();}
        @Override public void setIntHeader(String name, int value){throw new UnsupportedOperationException();}
        @Override public void addIntHeader(String name, int value){throw new UnsupportedOperationException();}
        @Override public void setContentLength(int len){throw new UnsupportedOperationException();}
        @Override public void setContentLengthLong(long len){throw new UnsupportedOperationException();}
        @Override public void setBufferSize(int size){throw new UnsupportedOperationException();}
        @Override public int getBufferSize(){throw new UnsupportedOperationException();}
        @Override public void flushBuffer(){throw new UnsupportedOperationException();}
        @Override public void resetBuffer(){throw new UnsupportedOperationException();}
        @Override public boolean isCommitted(){throw new UnsupportedOperationException();}
        @Override public void reset(){throw new UnsupportedOperationException();}
        @Override public void setLocale(java.util.Locale loc){throw new UnsupportedOperationException();}
        @Override public java.util.Locale getLocale(){throw new UnsupportedOperationException();}
        @Override public javax.servlet.ServletOutputStream getOutputStream(){throw new UnsupportedOperationException();}
        @Override public void setCharacterEncoding(String charset){throw new UnsupportedOperationException();}
        @Override public String getCharacterEncoding(){throw new UnsupportedOperationException();}
    }

    @Test
    public void getListAllWhenNoHostname() throws ServletException, IOException {
        MapRequest req = new MapRequest();
        WriterResponse res = new WriterResponse();
        // Seed via POST
        req.setParam("hostname", "h1");
        req.setParam("username", "u1");
        controller.doPost(req, res);

        // GET list
        MapRequest getReq = new MapRequest();
        WriterResponse getRes = new WriterResponse();
        controller.doGet(getReq, getRes);
        String body = getRes.body();
        assertTrue(body.contains("h1|u1"));
        assertEquals(HttpServletResponse.SC_OK, getRes.getStatus());
    }

    @Test
    public void getSingleFoundAndNotFound() throws ServletException, IOException {
        MapRequest req = new MapRequest();
        WriterResponse res = new WriterResponse();
        req.setParam("hostname", "h2");
        req.setParam("username", "u2");
        controller.doPost(req, res);

        MapRequest getReq = new MapRequest();
        getReq.setParam("hostname", "h2");
        WriterResponse getRes = new WriterResponse();
        controller.doGet(getReq, getRes);
        assertTrue(getRes.body().contains("h2|u2"));
        assertEquals(HttpServletResponse.SC_OK, getRes.getStatus());

        MapRequest missingReq = new MapRequest();
        missingReq.setParam("hostname", "missing");
        WriterResponse missingRes = new WriterResponse();
        controller.doGet(missingReq, missingRes);
        assertEquals(HttpServletResponse.SC_NOT_FOUND, missingRes.getStatus());
    }

    @Test
    public void postValidCreatesAndInvalidReturnsBadRequest() throws ServletException, IOException {
        MapRequest req = new MapRequest();
        WriterResponse res = new WriterResponse();
        req.setParam("hostname", "h3");
        req.setParam("username", "u3");
        controller.doPost(req, res);
        assertEquals(HttpServletResponse.SC_CREATED, res.getStatus());
        assertTrue(res.body().contains("created h3"));

        MapRequest badReq = new MapRequest();
        WriterResponse badRes = new WriterResponse();
        controller.doPost(badReq, badRes);
        assertEquals(HttpServletResponse.SC_BAD_REQUEST, badRes.getStatus());
    }

    @Test
    public void putUpdatesExistingOr404WhenMissing() throws ServletException, IOException {
        MapRequest postReq = new MapRequest();
        postReq.setParam("hostname", "h4");
        postReq.setParam("username", "u4");
        WriterResponse postRes = new WriterResponse();
        controller.doPost(postReq, postRes);

        MapRequest putReq = new MapRequest();
        putReq.setParam("hostname", "h4");
        putReq.setParam("username", "u4b");
        WriterResponse putRes = new WriterResponse();
        controller.doPut(putReq, putRes);
        assertTrue(putRes.body().contains("updated h4"));
        assertEquals(HttpServletResponse.SC_OK, putRes.getStatus());

        MapRequest missingReq = new MapRequest();
        missingReq.setParam("hostname", "missing");
        missingReq.setParam("username", "x");
        WriterResponse missingRes = new WriterResponse();
        controller.doPut(missingReq, missingRes);
        assertEquals(HttpServletResponse.SC_NOT_FOUND, missingRes.getStatus());
    }

    @Test
    public void deleteRemovesOr404WhenMissing() throws ServletException, IOException {
        MapRequest postReq = new MapRequest();
        postReq.setParam("hostname", "h5");
        postReq.setParam("username", "u5");
        WriterResponse postRes = new WriterResponse();
        controller.doPost(postReq, postRes);

        MapRequest delReq = new MapRequest();
        delReq.setParam("hostname", "h5");
        WriterResponse delRes = new WriterResponse();
        controller.doDelete(delReq, delRes);
        assertTrue(delRes.body().contains("deleted h5"));
        assertEquals(HttpServletResponse.SC_OK, delRes.getStatus());

        MapRequest missingReq = new MapRequest();
        WriterResponse missingRes = new WriterResponse();
        controller.doDelete(missingReq, missingRes);
        assertEquals(HttpServletResponse.SC_BAD_REQUEST, missingRes.getStatus());

        MapRequest missingReq2 = new MapRequest();
        missingReq2.setParam("hostname", "missing");
        WriterResponse missingRes2 = new WriterResponse();
        controller.doDelete(missingReq2, missingRes2);
        assertEquals(HttpServletResponse.SC_NOT_FOUND, missingRes2.getStatus());
    }
}
