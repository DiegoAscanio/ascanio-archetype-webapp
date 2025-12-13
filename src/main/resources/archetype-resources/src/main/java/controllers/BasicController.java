package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.BasicModel;
import repositories.BasicRepo;
import repositories.BasicRepoMemory;

public class BasicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BasicRepo repo = new BasicRepoMemory();

	// GET /basic?hostname=HOST -> single; GET /basic -> list all
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		String hostname = request.getParameter("hostname");
		if (hostname == null || hostname.isEmpty()) {
			List<BasicModel> all = repo.findAll();
			for (BasicModel model : all) {
				response.getWriter().println(model.getHostname() + "|" + model.getUsername());
			}
			return;
		}

		repo.findByHostname(hostname).ifPresentOrElse(model -> {
			try {
				response.getWriter().println(model.getHostname() + "|" + model.getUsername());
			} catch (IOException e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}, () -> response.setStatus(HttpServletResponse.SC_NOT_FOUND));
	}

	// POST /basic with hostname & username -> create
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		String hostname = request.getParameter("hostname");
		String username = request.getParameter("username");
		if (hostname == null || username == null || hostname.isEmpty() || username.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println("hostname and username are required");
			return;
		}

		BasicModel created = repo.create(new BasicModel(hostname, username));
		response.setStatus(HttpServletResponse.SC_CREATED);
		response.getWriter().println("created " + created.getHostname());
	}

	// PUT /basic with hostname & username -> update existing
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		String hostname = request.getParameter("hostname");
		String username = request.getParameter("username");
		if (hostname == null || username == null || hostname.isEmpty() || username.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println("hostname and username are required");
			return;
		}

		try {
			BasicModel updated = repo.update(new BasicModel(hostname, username));
			response.getWriter().println("updated " + updated.getHostname());
		} catch (IllegalArgumentException ex) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().println(ex.getMessage());
		}
	}

	// DELETE /basic?hostname=HOST -> delete by hostname
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		String hostname = request.getParameter("hostname");
		if (hostname == null || hostname.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println("hostname is required");
			return;
		}

		boolean deleted = repo.deleteByHostname(hostname);
		if (!deleted) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().println("not found: " + hostname);
			return;
		}

		response.getWriter().println("deleted " + hostname);
	}
}
