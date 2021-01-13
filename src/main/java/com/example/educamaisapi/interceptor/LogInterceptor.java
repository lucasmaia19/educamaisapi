package com.example.educamaisapi.interceptor;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.info("Pre Handle method is Calling");
		
		String paramQuery = request.getQueryString();
		String paramBody = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
		
		String paramParts = "";
		//for (Part p : request.getParts()) {
			//log.info("Part Name:" + p.getName());
			//log.info("Part Content:" + request.getPart(p.getName()));
			//log.info("Part inpuStream:" + IOUtils.toString(request.getPart(p.getName()).getInputStream(), StandardCharsets.UTF_8));
			//paramParts += IOUtils.toString(request.getPart(p.getName()).getInputStream(), StandardCharsets.UTF_8);
		//}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		log.info("Post Handle method is Calling");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {

		log.info("Request and Response is completed");
	}

}
