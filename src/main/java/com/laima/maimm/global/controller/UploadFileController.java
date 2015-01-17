package com.laima.maimm.global.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/global/upload")
public class UploadFileController {
	
	@RequestMapping(value="/uploadFile.lm")
	public @ResponseBody String uploadFile(MultipartHttpServletRequest request){
		Map<String,MultipartFile> fileMap =  request.getFileMap();
		String newFilename = null;
		for(String filename : fileMap.keySet()){			
			try {
				MultipartFile file = fileMap.get(filename);
				newFilename = String.valueOf(System.currentTimeMillis());
				Thumbnails.of(file.getInputStream()).size(150, 150).toFile(new File("d:/uploads",newFilename));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "success";
	}

}
