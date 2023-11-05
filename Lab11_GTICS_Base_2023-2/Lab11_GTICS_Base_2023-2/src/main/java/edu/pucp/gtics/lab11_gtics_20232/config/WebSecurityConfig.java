package edu.pucp.gtics.lab11_gtics_20232.config;

import edu.pucp.gtics.lab11_gtics_20232.entity.User;
import edu.pucp.gtics.lab11_gtics_20232.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig  {
    final DataSource dataSource;
    final UserRepository userRepository;
    public WebSecurityConfig(DataSource dataSource, UserRepository userRepository){
        this.dataSource=dataSource;
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.formLogin()
                .loginPage("/openLoginWindow")
                .loginProcessingUrl("/submitLoginForm")
                .successHandler((request, response, authentication) -> {
                    DefaultSavedRequest defaultSavedRequest =
                            (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");

                    HttpSession session = request.getSession();
                    System.out.println(authentication.getName());

                    session.setAttribute("usuario", userRepository.findByCorreo(authentication.getName()));
                    User usuario = userRepository.findByCorreo(authentication.getName());
                    System.out.println(usuario.getNombres());


                    //si vengo por url -> defaultSR existe
                    if (defaultSavedRequest != null) {
                        String targetURl = defaultSavedRequest.getRequestURL();
                        new DefaultRedirectStrategy().sendRedirect(request, response, targetURl);
                    } else { //estoy viniendo del botón de login
                        String rol = "";
                        for (GrantedAuthority role : authentication.getAuthorities()) {
                            rol = role.getAuthority();
                            System.out.println("ESte es el rol: " + rol);
                            break;
                        }
                        switch (rol) {
                            case "ADMIN" -> response.sendRedirect("/juegos/lista");
                            case "USER" -> response.sendRedirect("/juegos/lista");
                        }
                    }
                })

        ;

        http.logout().logoutSuccessUrl("/openLoginWindow");

        http.authorizeHttpRequests()
                .anyRequest().permitAll();


            return http.build();
    }
    @Bean
    public UserDetailsManager users(DataSource dataSource){
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        String sql1 = "SELECT correo, password, enabled FROM usuarios WHERE correo = ?";
        String sql2 = "SELECT correo, autorizacion FROM usuarios u " +
                "                WHERE correo = ? and enabled = 1";
        users.setUsersByUsernameQuery(sql1);
        users.setAuthoritiesByUsernameQuery(sql2);
        return users;
    }

}