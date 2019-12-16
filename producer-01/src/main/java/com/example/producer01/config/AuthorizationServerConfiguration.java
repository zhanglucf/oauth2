package com.example.producer01.config;

import com.example.oauth.service.MyClientDetailsService;
import com.example.oauth.store.DefaultOauthTokenStoreImpl;
import com.example.producer01.service.PasswordTokenGranter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenhua zhang
 * @data 2019/12/12
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    private DefaultOauthTokenStoreImpl tokenStore = new DefaultOauthTokenStoreImpl();

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
//                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore)
                .reuseRefreshTokens(true)
                .authenticationManager(this.authenticationManager);
 //               .setClientDetailsService(new MyClientDetailsService());
        List<TokenGranter> tokenGranters = getTokenGranters(endpoints); // implementation up to you
        TokenGranter tokenGranter = new CompositeTokenGranter(tokenGranters);
        endpoints.tokenGranter(tokenGranter);
    }

    private List<TokenGranter> getTokenGranters(AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenGranter> tokenGranters = new ArrayList<>();
        tokenGranters.add(new PasswordTokenGranter(
                this.authenticationManager,
                endpoints.getTokenServices(),
                endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory(),
                PasswordTokenGranter.GRANT_TYPE
        ));
        tokenGranters.add(new RefreshTokenGranter(
                endpoints.getTokenServices(),
                endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory()
        ));
        return tokenGranters;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients();
    }

    @Bean
    public TokenStore tokenStore() {
        return tokenStore;
    }

    @Bean
    MyClientDetailsService clientDetailsService() {
        return new MyClientDetailsService();
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setAuthenticationManager(authenticationManager);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setClientDetailsService(clientDetailsService());
        return tokenServices;
    }

}
