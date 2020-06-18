package ru.itis.univer.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final DataSource dataSource;

    public SecurityConfig(PasswordEncoder passwordEncoder,
                          @Qualifier(value = "customUserDetailsService") UserDetailsService userDetailsService,
                          DataSource dataSource) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/signUp").permitAll()
                    .antMatchers("/signIn").permitAll()
                    .antMatchers("/users").hasAuthority("ADMIN")
                    .antMatchers("/changeUsers").hasAuthority("ADMIN")
                    .antMatchers("/profile").authenticated()
                    .antMatchers("/subjects").authenticated()
                    .antMatchers("/lessons").authenticated()
                    .antMatchers("/my").authenticated()
                    .antMatchers("/createNewSubject").hasAuthority("TEACHER")
                    .antMatchers("/createNewLesson").hasAuthority("TEACHER")
                    .antMatchers("/newSubject").hasAuthority("TEACHER")
                    .antMatchers("/newLesson").hasAuthority("TEACHER")
                    .antMatchers("/deleteSubject").hasAuthority("TEACHER")
                    .antMatchers("/deleteLesson").hasAuthority("TEACHER")
                    .antMatchers("/").authenticated()
                    .antMatchers("/files").authenticated()
                    .antMatchers("/storage").authenticated()
                .and()
                    .rememberMe()
                    .rememberMeParameter("remember-me")
                    .tokenRepository(persistentTokenRepository());

        http.csrf()
                .ignoringAntMatchers("/profile")
                .ignoringAntMatchers("/lessons")
                .ignoringAntMatchers("/subjects");

        http.formLogin()
                .loginPage("/signIn")
                .defaultSuccessUrl("/profile")
                .failureUrl("/signIn?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/signIn")
                .deleteCookies("JSESSIONID", "remember-me")
                .invalidateHttpSession(true);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

}
