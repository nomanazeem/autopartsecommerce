package nazeem.autoparts.admin.config;

import nazeem.autoparts.library.service.*;
import nazeem.autoparts.library.service.impl.CustomerServiceImpl;
import nazeem.autoparts.library.service.impl.UserServiceImpl;
import nazeem.autoparts.library.util.Utility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by noman.
 */
@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Bean
    public CustomerService customerService(){
        return new CustomerServiceImpl();
    }

    @Bean
    public ProductService productService(){
        return new ProductService();
    }

    @Bean
    public CategoryService categoryService(){
        return new CategoryService();
    }

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MakeService makeService(){
        return new MakeService();
    }

    @Bean
    public ModelService modelService(){
        return new ModelService();
    }

    @Bean
    public Utility utility(){
        return new Utility();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService());
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(
                "/register**",
                "/dist/**"
                , "/plugins/**"
                , "/bootstrap/**"
                , "/extra/**"
                , "/upload/**"
                , "/favicon.ico")
                .permitAll()
        .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();

    }
}
