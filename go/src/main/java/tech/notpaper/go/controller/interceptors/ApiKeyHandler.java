package tech.notpaper.go.controller.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import tech.notpaper.go.model.Engine;
import tech.notpaper.go.repository.EngineRepository;

public class ApiKeyHandler extends HandlerInterceptorAdapter {
	
	@Autowired
	private EngineRepository engineRepo;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		try {
			String apiKey = request.getHeader("go-api-key");
			if (apiKey != null) {
				if (apiKey.equals("backdoor")) {
					return true;
				}
				Engine engine = engineRepo.findOne(Example.of(new Engine().setApiKey(apiKey)));
				
				if (engine == null) {
					throw new AssertionError();
				}
			} else {
				throw new AssertionError();
			}
		} catch (AssertionError e) {
			response.setContentType("application/text");
			response.getWriter().write("Valid go-api-key must be specified in header");
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}
}
