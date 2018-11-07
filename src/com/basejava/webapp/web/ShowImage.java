package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.storage.SqlStorage;
import com.basejava.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

@WebServlet(name = "ShowImage", urlPatterns = {"/ShowImage"})
public class ShowImage extends HttpServlet {
    private static final long serialVersionUID = 995497791471805172L;
    private final String CLASS_NAME = getClass().getName();
    private final Logger LOG = Logger.getLogger(CLASS_NAME);
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();
        try {
            String index = request.getParameter("index");
            int length = ((SqlStorage)storage).getPhoto(index).length;
            if (length != 0) {
                response.setContentLength(((SqlStorage) storage).getPhoto(index).length);
                out.write(((SqlStorage) storage).getPhoto(index));
            } else {
                InputStream is = Config.class.getResourceAsStream("/pic/undefined.png");
                int len = (int) is.available();
                byte[] buffer = new byte[(int) is.available()];
                for (int i = 0; i < len; i++) {
                    buffer[i] = (byte) is.read();
                }
                out.write(buffer);
            }
        } finally {
            out.flush();
            out.close();
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException e) {
            LOG.info(CLASS_NAME + ": " + " doGet: " + e.getMessage());
        }
    }

}