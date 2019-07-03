package kr.or.ddit.attachFile.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.attachFile.model.AttachFileVO;
import kr.or.ddit.attachFile.service.IAttachFileService;

public class Download extends AbstractView {

	@Resource(name = "attachFileService")
	private IAttachFileService fileService;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String fileId = (String) model.get("fileId");
		
		AttachFileVO fileVo = fileService.getFile(fileId);
		
		String fileName = fileVo.getFileName();
		
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		response.setContentType("application/octet-stream");
		
		File file = new File(fileId);
		FileInputStream fis = new FileInputStream(file);
		ServletOutputStream sos = response.getOutputStream();
		
		byte[] buff = new byte[1024];
		int len = -1;
		
		while( (len = fis.read(buff)) != -1 ) {
			sos.write(buff);
		}
		
		sos.close();
		fis.close();
	}
    
}
