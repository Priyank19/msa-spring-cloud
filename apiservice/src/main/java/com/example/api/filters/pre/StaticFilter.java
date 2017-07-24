package com.example.api.filters.pre;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class StaticFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(StaticFilter.class);

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		String path = RequestContext.getCurrentContext().getRequest().getRequestURI();
		return "/api/static".equals(path);
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		// Set the response 
		ctx.setResponseStatusCode(HttpServletResponse.SC_OK);
		if (ctx.getResponseBody() == null) {
			ctx.setResponseBody("static content");
			ctx.setSendZuulResponse(false);
		}
		return null;
	}
}
