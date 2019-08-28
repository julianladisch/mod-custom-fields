package org.folio.spring;

import static org.folio.rest.exc.RestExceptionHandlers.baseBadRequestHandler;
import static org.folio.rest.exc.RestExceptionHandlers.baseNotFoundHandler;
import static org.folio.rest.exc.RestExceptionHandlers.baseUnauthorizedHandler;
import static org.folio.rest.exc.RestExceptionHandlers.baseUnprocessableHandler;
import static org.folio.rest.exc.RestExceptionHandlers.completionCause;
import static org.folio.rest.exc.RestExceptionHandlers.generalHandler;
import static org.folio.rest.exc.RestExceptionHandlers.logged;

import javax.ws.rs.core.Response;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.folio.common.pf.PartialFunction;

@Configuration
@ComponentScan(basePackages = {
  "org.folio.repository",
  "org.folio.service"})
public class ApplicationConfig {

  @Bean
  public PartialFunction<Throwable, Response> customFieldsExcHandler() {
    return logged(baseBadRequestHandler()
      .orElse(baseNotFoundHandler())
      .orElse(baseUnauthorizedHandler())
      .orElse(baseUnprocessableHandler())
      .orElse(generalHandler())
      .compose(completionCause()));
  }
}