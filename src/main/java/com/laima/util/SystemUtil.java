package com.laima.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author jiayi.zhang
 * 
 */
@Configuration("systemUtil")
public class SystemUtil {

	@Value("${laima.laima}")
	private String test;
	

	
	
	

}
