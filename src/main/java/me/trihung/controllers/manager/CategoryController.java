package me.trihung.controllers.manager;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import me.trihung.config.Constant;
import me.trihung.models.CategoryModel;
import me.trihung.services.ICategoryService;
import me.trihung.services.impl.CategoryServiceImpl;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/manager/category", "/manager/category/edit", "/manager/category/update",
		"/manager/category/insert", "/manager/category/delete" , "/manager/category/search"})
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ICategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("/manager/category/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			System.out.println(id + " >");
			CategoryModel category = categoryService.findById(id);
			System.out.println(category.getImages());
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/view/manager/category-edit.jsp").forward(req, resp);
		} else if (url.contains("/manager/category/insert")) {
			req.getRequestDispatcher("/view/manager/category-insert.jsp").forward(req, resp);

		} else if (url.contains("/manager/category/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			CategoryModel category = categoryService.findById(id);
			categoryService.delete(category.getCategoryid());
			System.out.println("delete category id: " + id);
			loadCategories(req, resp);
		} else if (url.contains("/manager/category/search")) {
			String name = req.getParameter("search");
			System.out.println("search by name: "+name);
			if (name.strip().length()==0) {
				List<CategoryModel> list = categoryService.findAll();
				req.setAttribute("listcate", list);
				req.getRequestDispatcher("/view/manager/category-list.jsp").forward(req, resp);
				return;
			}
			List<CategoryModel> list = categoryService.findName(name);
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/view/manager/category-list.jsp").forward(req, resp);
		} else {
			List<CategoryModel> list = categoryService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/view/manager/category-list.jsp").forward(req, resp);
		}
	}
	

	private String getFileName(Part part) {
		return part.getSubmittedFileName();
	}
 
	public String uploadFileImage(HttpServletRequest request) {
		String uploadPath = getServletContext().getRealPath("") + Constant.UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		
		if (!uploadDir.exists())
			uploadDir.mkdirs();
		try {
			String fileName = "";
			Part part = request.getPart("images");
			fileName = getFileName(part);
			if (fileName==null || fileName.length() <= 0)
				return null;
			System.out.println("write " + fileName+"|");
			System.out.println(uploadPath);
			part.write(uploadPath + fileName);
			request.setAttribute("message", "File " + fileName + " has uploaded successfully!");
			return fileName;

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "There was an error: " + e.getMessage());

		}
		return null;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("/manager/category/update")) {
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			String categoryname = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));
			String images = uploadFileImage(req);
			CategoryModel category = categoryService.findById(categoryid);
			category.setCategoryid(categoryid);
			category.setCategoryname(categoryname);
			if (images!=null)
				category.setImages(images);
			category.setStatus(status);
			categoryService.update(category);
			loadCategories(req, resp);

		} else if (url.contains("/manager/category/insert")) {
			String categoryname = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));
			String images = uploadFileImage(req);
			CategoryModel category = new CategoryModel();
			category.setCategoryname(categoryname);
			category.setImages(images);
			category.setStatus(status);
			System.out.println(categoryname);
			System.out.println(images);
			System.out.println(status);
			categoryService.insert(category);
			loadCategories(req, resp);
		}
	}

	public void loadCategories(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect(req.getContextPath() + "/manager/category");
	}

}
