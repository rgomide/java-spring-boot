package br.com.gomide;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

  @RequestMapping(value = "/somar/{valorUm}/{valorDois}", method = RequestMethod.GET)
  public Double somar(
      @PathVariable(value = "valorUm") String strValorUm,
      @PathVariable(value = "valorDois") String strValorDois) throws Exception {

    Double valorUm = converterParaDouble(strValorUm);
    Double valorDois = converterParaDouble(strValorDois);

    return valorUm + valorDois;
  }

  private Double converterParaDouble(String strValor) throws Exception {
    if (strValor == null) {
      return 0.;
    }

    strValor = strValor.replace(",", ".");

    if (strValor.matches("[-+]?[0-9]*\\.?[0-9]+")) {
      return Double.parseDouble(strValor);
    } else {
      throw new Exception("Valor inválido: " + strValor);
    }
  }

}
