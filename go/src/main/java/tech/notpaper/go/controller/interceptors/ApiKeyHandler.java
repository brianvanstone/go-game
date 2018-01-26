package tech.notpaper.go.controller.interceptors;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import tech.notpaper.go.controller.exceptions.NotFoundException;
import tech.notpaper.go.model.Engine;
import tech.notpaper.go.model.Person;
import tech.notpaper.go.repository.EngineRepository;
import tech.notpaper.go.repository.PersonRepository;
import tech.notpaper.go.pojo.Error;

public class ApiKeyHandler extends HandlerInterceptorAdapter {
	
	@Autowired
	private EngineRepository engineRepo;
	
	@Autowired
	private PersonRepository personRepo;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		try {
			String apiKey = request.getHeader("go-api-key");
			if (apiKey == null) {
				throw new NotFoundException("go-api-key header is required");
			}
			
			if (apiKey.equals("backdoor")) {
				return true;
			}
			
			Engine engine = engineRepo.findOne(Example.of(new Engine().setApiKey(apiKey)));
			if (engine == null) {
				Person p = personRepo.findOne(Example.of(new Person().setApiKey(apiKey)));
				if (p == null) {
					throw new NotFoundException("Unable to find an engine or person matching apikey");
				}
			}
		} catch (NotFoundException e) {
			response.setContentType("application/json");
			ObjectMapper mapper = new ObjectMapper();
			Error error = Error.fromException(e)
					.setTimestamp(new Date())
					.setError("Bad Request")
					.setStatus(HttpStatus.BAD_REQUEST.value())
					.setPath(request.getServletPath());
			response.getWriter().write(mapper.writeValueAsString(error));
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}
}
