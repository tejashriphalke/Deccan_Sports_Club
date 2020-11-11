package com.cybage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.TargetDataLine;

import com.cybage.model.Users;
import com.cybage.pojos.AllBatchInfo;
import com.cybage.pojos.Batch;

import com.cybage.service.ManagerServiceI;
import com.cybage.service.ManagerServiceImpl;

@ServletSecurity(
		value = @HttpConstraint(
				rolesAllowed = {"manager"}
				)
		)
public class ManagerController extends HttpServlet {
	
	ManagerServiceI manager=new ManagerServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		
		if(path.equals("/listbatch")) {			
			try {
				List<AllBatchInfo> listbatch=manager.getAllBatches();
				
				request.setAttribute("batches", listbatch);
				request.getRequestDispatcher("/manager/manager-batches.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(path.equals("/addbatch")) {			
			try {
				Batch batchinfo= new Batch();
				
				batchinfo.setStartDate(manager.getDate(request.getParameter("startDate")));
				batchinfo.setEndDate(manager.getDate(request.getParameter("endDate")));
			
				batchinfo.setBatchSize(Integer.parseInt(request.getParameter("batchSize")));
				batchinfo.setSportId(Integer.parseInt(request.getParameter("sportId")));
				
				manager.addBatch(batchinfo);
				response.sendRedirect("listbatch");								
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(path.equals("/deletebatch")) {			
			try {
				manager.removeBatch(Integer.parseInt(request.getParameter("batchId")));
				
				request.setAttribute("deletemsg", "Batch deleted successfully");
				response.sendRedirect("listbatch");								
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		if(path.equals("/editbatch")) {			
			try {
				Batch batch=manager.getBatch(Integer.parseInt(request.getParameter("batchId")));
				
				request.setAttribute("batches", batch);
				request.getRequestDispatcher("/manager/update-batch.jsp").forward(request, response);								
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(path.equals("/updatebatch")) {			
			try {
				
				Batch batchinfo =new Batch();
				batchinfo.setBatchId(Integer.parseInt(request.getParameter("batchId")));
				batchinfo.setStartDate(manager.getDate(request.getParameter("startDate")));
				batchinfo.setEndDate(manager.getDate(request.getParameter("endDate")));
			
				batchinfo.setBatchSize(Integer.parseInt(request.getParameter("batchSize")));
				batchinfo.setSportId(Integer.parseInt(request.getParameter("sportId")));
				manager.updateBatch(batchinfo);
				
				
				response.sendRedirect("listbatch");										
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
 	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

				
		
		
		
		
}
	

	

