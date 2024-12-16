package com.gaotianchi.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.vo.VO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author gaotianchi
 * @since 2024/11/26 13:14
 **/
@Component
public class AccessDeniedHandlerFilter implements AccessDeniedHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(objectMapper.writeValueAsString(VO.response(Code.AUTH_ACCESS_DENIED, accessDeniedException.getMessage())));
        response.getWriter().flush();
    }
}
