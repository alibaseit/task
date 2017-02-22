package com.dogan.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dogan.exceptionhandle.AbsException;
import com.dogan.model.Task;
import com.dogan.service.TaskService;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {
	@Autowired
	private TaskService taskService;

	@GetMapping("/{id}")
	public List<Task> getTaskById(@PathVariable("id") int id) throws AbsException {
		if (id > 10) {
			throw new AbsException();
		} else
			return taskService.getTask(id);
	}

	@GetMapping("/unfinished")
	public List<Task> pendingTasks() {
		return taskService.allPendingTasks();
	}

	@GetMapping("/finished")
	public List<Task> completedTasks() {
		return taskService.allCompletedTasks();
	}

	@GetMapping
	public Iterable<Task> all() {
		return taskService.findAll();
	}

	@PostMapping
	public Task save(@RequestBody Task task) {
		return taskService.save(task);
	}

	@PutMapping
	public Task add(@RequestBody Task task) {
		return taskService.save(task);
	}

	@DeleteMapping("/{id}")
	public Task delete(@PathVariable("id") long id) {
		return taskService.delete(id);
	}
	
	@GetMapping("/report")
	@ResponseBody
	public void report(HttpServletResponse response) {
		InputStream jasperStream = this.getClass().getResourceAsStream("/reports/TaskListReport.jrxml");
		try {
			JasperDesign design = JRXmlLoader.load(jasperStream);
			JasperReport report = JasperCompileManager.compileReport(design);
			List<Task> tasks = taskService.findAll();
			JRDataSource jrDataSource = new JRBeanCollectionDataSource(tasks);
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("datasource", jrDataSource);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameterMap, jrDataSource);
			response.setContentType("application/x-pdf");
			response.setHeader("Content-Disposition", "inline: filename=task.pdf");
			final OutputStream outputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		} catch (JRException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
