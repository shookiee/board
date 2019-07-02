package kr.or.ddit.attachFile.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import kr.or.ddit.attachFile.model.AttachFileVO;
import kr.or.ddit.attachFile.service.IAttachFileService;

@RequestMapping(path = "/downLoad", method = RequestMethod.POST)
@Controller
public class DownLoad implements View {
    
	@Resource(name="attachFileService")
	private IAttachFileService fileService;
	
	@Override
	public String getContentType() {
		return "img";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		AttachFileVO fileVo = (AttachFileVO) model.get("fileVo");
		
		ServletOutputStream sos = null;
		
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileInputStream fis = null;
		String filePath = null;
		
		if(fileVo.getFilePath() != null) {
			filePath= fileVo.getFilePath();
		} else {
			filePath = request.getServletContext().getRealPath("/img/no_image.gif");
		}
		
		File file = new File(filePath);
		try {
			fis = new FileInputStream(file);
			int len = 0;
			byte[] buffer = new byte[1024];
			while((len=fis.read(buffer, 0, 1024)) != -1) {
				sos.write(buffer);
			}	
			fis.close();
			sos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	
	}

}
