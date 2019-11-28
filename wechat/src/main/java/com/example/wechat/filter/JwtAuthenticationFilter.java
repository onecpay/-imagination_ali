//package com.example.wechat.filter;
//
//import com.example.wechat.content.SecurityToken;
//import com.example.wechat.exception.CustomerException;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * jwt 权限配置filter
// * 适合前后端分离
// * @author ONEC
// */
//@Slf4j
//public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
//
//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    /**
//     * 在拦截器中获取token并解析，拿到用户信息，放置到SecurityContextHolder，这样便完成了springsecurity和jwt的整合。
//     */
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        String header = request.getHeader(SecurityToken.AUTHORIZATION);
//        log.info("用户登录：获取token值：{}", header);
//        if ((header == null) || !header.startsWith(SecurityToken.BEARER)) {
//            chain.doFilter(request, response);
//            return;
//        }
//        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        chain.doFilter(request, response);
//    }
//
//    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
//        String token = request.getHeader(SecurityToken.AUTHORIZATION);
//        Claims claims = Jwts.parser().setSigningKey(SecurityToken.JWT_SECRET).parseClaimsJws(token.replace(SecurityToken.BEARER, ""))
//                .getBody();
//        String user = claims.getSubject();
//        @SuppressWarnings("unchecked")
//        List<String> roles = claims.get(SecurityToken.USER_ROLE, List.class);
//        List<SimpleGrantedAuthority> auth = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//        if (null != user) {
//            return new UsernamePasswordAuthenticationToken(user, null, auth);
//        }
//        throw new CustomerException(80001, "请求权限不足");
//    }
//}
