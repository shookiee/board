package kr.or.ddit.attachFile.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.attachFile.model.AttachFileVO;
import kr.or.ddit.attachFile.service.AttachFileService;
import kr.or.ddit.attachFile.service.IAttachFileService;

@WebServlet("/downLoad")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class DownLoad1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private static final Logger logger = LoggerFactory
			.getLogger(DownLoad1.class);
	
	private IAttachFileService fileService;
	
	@Override
	public void init() throws ServletException {
		fileService = new AttachFileService();
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		logger.debug("fileDownload doPost()");
	
		String fileId = request.getParameter("fileId");
		AttachFileVO fileVo = fileService.getFile(fileId);
		String fileName = fileVo.getFileName();
		logger.debug("fileVo : {}", fileVo);
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+fileName);
		
		File file = new File(fileId);
		FileInputStream fileInputStream = new FileInputStream(file);
		ServletOutputStream servletOutputStream = response.getOutputStream();
		
		byte[] b = new byte[1024];
		int data = 0;
		
		while((data=(fileInputStream.read(b,0,b.length))) != -1){
			servletOutputStream.write(b,0,data);
		}
		
		servletOutputStream.flush();
		servletOutputStream.close();
		fileInputStream.close();

	}

}
