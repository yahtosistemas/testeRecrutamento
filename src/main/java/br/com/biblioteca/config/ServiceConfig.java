package br.com.biblioteca.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.biblioteca.service.CadastroPessoaService;

@Configuration
@ComponentScan(basePackageClasses = { CadastroPessoaService.class})
public class ServiceConfig {

}
