package br.com.techtaste.microsservice.utils;

import br.com.techtaste.microsservice.dto.AutorizacaoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("ms-pagamentos")
public interface AutorizacaoPagamentoClient {
    @RequestMapping(method = RequestMethod.GET, value = "/pagamentos/autorizacao/{id}")
    AutorizacaoDto obterAutorizacao(@PathVariable String id);
}
