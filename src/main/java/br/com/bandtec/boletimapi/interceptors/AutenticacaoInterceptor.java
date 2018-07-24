package br.com.bandtec.boletimapi.interceptors;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author José Yoshiriro
 */
public class AutenticacaoInterceptor extends HandlerInterceptorAdapter {

    public static final String NOME_TOKEN = "token";

    public static final String ATRIBUTO_REQUISICAO_USUARIO = "usuario-atual";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(NOME_TOKEN);

        if (token == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        if (token.length() < 3) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }

        request.setAttribute(ATRIBUTO_REQUISICAO_USUARIO, token);

        return super.preHandle(request, response, handler);
    }

}

