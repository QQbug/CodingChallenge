package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WebSecurityConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .anonymous().
//                .authorizeRequests()
//                    .antMatchers("/**", "/sendMessage", "/getMessages").permitAll();
//                    .anyRequest().authenticated()
//                    .and()
//                .authorizeRequests()
//                    .antMatchers("/check", "/createUser").permitAll()
//                    .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                    .and()
//                .logout()
//                    .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl userDao = new JdbcDaoImpl();
        userDao.setJdbcTemplate(jdbcTemplate);
        userDao.setAuthoritiesByUsernameQuery("select user_name, 'ROLE_USER' from user where user_name = ?");
        userDao.setEnableGroups(false);
        userDao.setUsersByUsernameQuery("select user_name, password, 'true' from user where user_name = ?");
        return userDao;
    }
}