package net.kondratenko.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsernameInUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private String urlPrefix;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private String fieldKey = "email";

    public UsernameInUrlAuthenticationFailureHandler(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    //Failure logic:
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //We inherited that method:

        saveException(request, exception);

        //Prepare URL:
        String username = request.getParameter(fieldKey);
        String redirectUrl = urlPrefix + username;

        //Redirect:
        redirectStrategy.sendRedirect(request, response, redirectUrl);
    }

    //Getters and setters:
    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

}
