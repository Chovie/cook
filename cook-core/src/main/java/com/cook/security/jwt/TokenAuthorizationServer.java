package com.cook.security.jwt;

import com.cook.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: token认证服务
 * @author: ziHeng
 * @create: 2018-05-24 15:13
 **/
//@Configuration
//@EnableAuthorizationServer
public class TokenAuthorizationServer extends AuthorizationServerConfigurerAdapter{

    //用户信息
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    //认证管理
    @Autowired
    private AuthenticationManager authenticationManager;

    //token存储
    @Autowired
    private TokenStore jwtTokenStore;

    //swt->jwt转换器
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    //增强器,给jwt写入额外信息
    @Autowired
    private TokenEnhancer jwtTokenEnhancer;

    //Token配置
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //配置存储
        endpoints
                .tokenStore(jwtTokenStore)
                //认证管理器
                .authenticationManager(authenticationManager)
                //用户身份
                .userDetailsService(myUserDetailsService);


        //写入信息
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancers = new ArrayList<TokenEnhancer>();
        enhancers.add(jwtTokenEnhancer);
        enhancers.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancers);

        endpoints
                .tokenEnhancer(enhancerChain)
                //默认token转为jwt
                .accessTokenConverter(jwtAccessTokenConverter);
    }

    //token配置
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("cook")
                .secret("cookSecret")
                //token有效时间
                //一天
                .accessTokenValiditySeconds(86400)
                //一个月
                .refreshTokenValiditySeconds(2592000)
                .authorizedGrantTypes("refresh_token","password")
                .scopes("all","read","write");
    }
}
