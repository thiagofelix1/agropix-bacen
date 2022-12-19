package com.agropix.bacen.adapter.in.web.controller.handler;

import com.agropix.bacen.adapter.in.web.dto.response.DomainErrorResponse;
import com.agropix.bacen.adapter.in.web.dto.response.InternalServerErrorResponse;
import com.agropix.bacen.adapter.in.web.dto.response.UnprossableEntityResponse;
import com.agropix.bacen.application.exceptions.ItemNaoEncontradoException;
import com.agropix.bacen.application.exceptions.PedidoTransacaoInvalidoException;
import com.agropix.bacen.domain.exceptions.DomainExceptionBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ControllerExceptionHandlers {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandlers.class);

    @ExceptionHandler({ ItemNaoEncontradoException.class })
    public ResponseEntity<UnprossableEntityResponse> handleItemNaoEncontradoException(ItemNaoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(new UnprossableEntityResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value()));
    }

    @ExceptionHandler({ DomainExceptionBase.class })
    public ResponseEntity<DomainErrorResponse> handleDomainException(DomainExceptionBase e) {
        return ResponseEntity.badRequest().body(new DomainErrorResponse(e.getErrors(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler({ PedidoTransacaoInvalidoException.class })
    public ResponseEntity<?> handlePedidoTransacaoInvalidoException(PedidoTransacaoInvalidoException e) {
        return ResponseEntity.badRequest().body(new DomainErrorResponse(e.getErros(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class })
    public ResponseEntity<UnprossableEntityResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, WebRequest request) {
        long errorCode = ZonedDateTime.now().toEpochSecond();

        logger.error(request.toString());
        logger.error("Erro inesperado ao processar requisicao. Dados da exception -> type: {} | mensagem: {} | causa: {} | errorCode: {}",
                e.getClass().getName(), e.getMessage(), e.getCause(), errorCode);

        return ResponseEntity.badRequest()
            .body(new UnprossableEntityResponse("Requisicao enviada com parametros invalidos", HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<InternalServerErrorResponse> handleGenericException(Exception e) {
        long errorCode = ZonedDateTime.now().toEpochSecond();

        logger.error("Erro inesperado ao processar requisicao. Dados da exception -> type: {} | mensagem: {} | causa: {} | errorCode: {}",
            e.getClass().getName(), e.getMessage(), e.getCause(), errorCode);

        return ResponseEntity.internalServerError()
            .body(new InternalServerErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), errorCode));
    }
}
