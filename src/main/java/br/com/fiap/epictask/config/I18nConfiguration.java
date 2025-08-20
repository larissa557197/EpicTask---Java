package br.com.fiap.epictask.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

// @Configuration = torna a classe uma fonte de beans de configuração
// implements WebMvcConfigurer = permite registrar interceptors e ajustar o MVCA
@Configuration
public class I18nConfiguration implements WebMvcConfigurer {


    @Bean
    MessageSource messageSource() {
        var messageSource = new ResourceBundleMessageSource();
        // indica que os bundles estarão em:
        messageSource.setBasename("messages");
        // é o provedor de mensagens para validações
        return messageSource;
    }


    @Bean
    LocaleChangeInterceptor localeChangeInterceptor() {
        var localeChangeInterceptor = new LocaleChangeInterceptor();
        // define que o locale pode ser trocado via query param
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }


    // registra o locale no fluxo do Spring MVC
    // toda requisiçã que tiver lang troca o locale da sessão antes de chegar ao controller
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }


    // usa session para armazenar o locale na sessão HTTP
    // se n tiver lang na URL, o sistema exibe mensagens em pt-BR
    @Bean
    LocaleResolver localeResolver (){
        var localeResolver = new SessionLocaleResolver();
                      // locale padrão é portugues - br
        localeResolver.setDefaultLocale(new Locale("pt", "BR"));
        return localeResolver;
    }
}
