package com.example.oauth.config;

import com.example.oauth.granter.PasswordTokenGranter;
import com.example.oauth.service.MyClientDetailsService;
import com.example.oauth.store.DefaultOauthTokenStoreImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
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

    private DefaultOauthTokenStoreImpl tokenStore = new DefaultOauthTokenStoreImpl();

    protected OAuth2RequestFactory requestFactory;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
//                .userDetailsService(userDetailsService)
//                .tokenStore(tokenStore)
//                .reuseRefreshTokens(true)
//                .userDetailsService(myUserDetailsService)
                .tokenServices(tokenServices());
//                .authenticationManager(this.authenticationManager);
 //               .setClientDetailsService(new MyClientDetailsService());
//        List<TokenGranter> tokenGranters = getTokenGranters(endpoints); // implementation up to you
//        TokenGranter tokenGranter = new CompositeTokenGranter(tokenGranters);
//        endpoints.tokenGranter(tokenGranter);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter:off
        clients.withClientDetails(clientDetailsService());
        // @formatter:on
    }

    private List<TokenGranter> getTokenGranters(AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenGranter> tokenGranters = new ArrayList<>();
        tokenGranters.add(new PasswordTokenGranter(
                endpoints.getTokenServices(),
                endpoints.getClientDetailsService(),
                requestFactory(),
                PasswordTokenGranter.GRANT_TYPE,
                this.authenticationManager
        ));
        tokenGranters.add(new RefreshTokenGranter(
                endpoints.getTokenServices(),
                endpoints.getClientDetailsService(),
                requestFactory()
        ));
        return tokenGranters;
    }

    private OAuth2RequestFactory requestFactory() {
        if (requestFactory != null) {
            return requestFactory;
        }
        requestFactory = new DefaultOAuth2RequestFactory(clientDetailsService());
        return requestFactory;
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
        tokenServices.setAccessTokenValiditySeconds(60*60*2);
        tokenServices.setRefreshTokenValiditySeconds(60*60*24*7);
        tokenServices.setClientDetailsService(clientDetailsService());
        return tokenServices;
    }

}
