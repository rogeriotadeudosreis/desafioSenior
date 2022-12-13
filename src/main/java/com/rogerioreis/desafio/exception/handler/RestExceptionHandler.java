package com.rogerioreis.desafio.exception.handler;

import com.rogerioreis.desafio.exception.RecursoExistenteException;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RequisicaoComErroException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(RequisicaoComErroException.class)
    public ResponseEntity<Object> handlerBadRequestException(RequisicaoComErroException ex) {

        String mensagemUsuario = "Problema no envio dos dados.";
        String mensageDesenvolvedor = ex.getMessage();

        return handleExceptionInternal(
                ex,
                new Erro(mensagemUsuario, mensageDesenvolvedor),
                new HttpHeaders(), HttpStatus.BAD_REQUEST,
                null);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Object> hanlderRecursoNaoEncontradoException(RecursoNaoEncontradoException ex) {
        String mensagemUsuario = "Náo encontrado resultados para a busca.";
        String mensageDesenvolvedor = ex.getMessage();

        return handleExceptionInternal(
                ex,
                new Erro(mensagemUsuario, mensageDesenvolvedor),
                new HttpHeaders(), HttpStatus.NOT_FOUND,
                null);
    }

    @ExceptionHandler(RecursoExistenteException.class)
    public ResponseEntity<Object> hanlderRecursoJaExisteException(RecursoExistenteException ex) {

        String mensagemUsuario = "Já existe esse cadastro.";
        String mensageDesenvolvedor = ex.getMessage();

        return handleExceptionInternal(
                ex,
                new Erro(mensagemUsuario, mensageDesenvolvedor),
                new HttpHeaders(), HttpStatus.CONFLICT,
                null);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.getCause().toString();

        return handleExceptionInternal(ex, new Erro(mensagemUsuario, mensagemDesenvolvedor), headers, status, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        List<Erro> erros = criarListaErros(ex);
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<Erro> erros = this.criarListaErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatus status,
                                                                         WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }

    public static class Erro {
        private String mensagemUsuario;
        private String mensagemDesenvolvedor;

        public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }

        public String getMensagemUsuario() {
            return mensagemUsuario;
        }

        public String getMensagemDesenvolvedor() {
            return mensagemDesenvolvedor;
        }
    }

    private List<Erro> criarListaErros(BindingResult bindingResult) {
        List<Erro> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String mensagemDesenvolvedor = fieldError.toString();

            erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        }

        for (ObjectError error : bindingResult.getGlobalErrors()) {
            String mensagemUsuario = "";
            String mensagemDesenvolvedor = "\n** Erro Global ** " + error.getObjectName() + ": " + error.getDefaultMessage();
            erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        }

        return erros;
    }

    private List<Erro> criarListaErros(ConstraintViolationException ex) {
        List<Erro> erros = new ArrayList<>();

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String mensagemUsuario = messageSource.getMessage(violation.getMessage(), null, LocaleContextHolder.getLocale());
            String mensagemDesenvoledor = violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage();

            erros.add(new Erro(mensagemUsuario, mensagemDesenvoledor));
        }
        return erros;
    }
}
