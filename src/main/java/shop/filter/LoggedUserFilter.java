package shop.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import shop.entity.User;

import java.io.IOException;
import java.util.Arrays;

@Component
public class LoggedUserFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (pagesRequiredLoggedUser(requestURI,"/user", "/checkout", "/cart", "/order")) {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect("/login");
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean pagesRequiredLoggedUser(String uri, String... pages){
        return Arrays.stream(pages).anyMatch(uri::startsWith);
    }
}
