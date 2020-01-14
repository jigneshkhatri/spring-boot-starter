package in.quallit.hrmanager.api.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import in.quallit.hrmanager.api.services.CustomUserDetailsService;

import javax.sql.DataSource;

/**
 * @author JIGS
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers("/oauth/token").permitAll();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    @Autowired
    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore) {
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        return handler;
    }

    @Bean
    @Autowired
    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }

}
