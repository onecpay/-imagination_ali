//package com.example.wechat.filter;
//
//import com.example.wechat.content.SecurityToken;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.stream.Collectors;
//
///**
// * jwt : 配置token 登录验证：
// * 适合前后端分离
// * @author ONEC
// */
//@Slf4j
//public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
//
//    private AuthenticationManager authenticationManager;
//
//    public JwtLoginFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//            throws AuthenticationException {
//        String phone = request.getParameter("phone");
//        String password = request.getParameter("password");
//        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phone, password, new ArrayList<>()));
//
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//                                            Authentication auth) {
//        log.info("登陆成功：设置token值：{}", auth.isAuthenticated());
//        Claims claims = Jwts.claims();
//        claims.put(SecurityToken.USER_ROLE, auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
//        String token = Jwts.builder()
//                .setClaims(claims)
//                .setSubject(auth.getName())
//                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
//                .signWith(SignatureAlgorithm.HS512, SecurityToken.JWT_SECRET).compact();
//        request.setAttribute(SecurityToken.AUTHORIZATION, token);
////        response.setCharacterEncoding("UTF-8");
////        response.setContentType("application/json; charset=utf-8");
////        String str = "{\"token\":\"" + token + "\"}";
////        PrintWriter out;
////        try {
////            out = response.getWriter();
////            out.print(str);
////            out.flush();
////            out.close();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//    }
//}
