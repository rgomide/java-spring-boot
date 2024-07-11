package br.com.gomide;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gomide.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {

  @RequestMapping(value = "/sum/{firstValue}/{secondValue}", method = RequestMethod.GET)
  public Double somar(
      @PathVariable(value = "firstValue") String strFirstValue,
      @PathVariable(value = "secondValue") String strSecondValue) {

    Double firstValue = parseToDouble(strFirstValue);
    Double secondValue = parseToDouble(strSecondValue);

    return firstValue + secondValue;
  }

  private Double parseToDouble(String strValue) {
    if (strValue == null) {
      return 0.;
    }

    strValue = strValue.replace(",", ".");

    if (strValue.matches("[-+]?[0-9]*\\.?[0-9]+")) {
      return Double.parseDouble(strValue);
    } else {
      throw new UnsupportedMathOperationException("Valor inválido: " + strValue);
    }
  }

}
