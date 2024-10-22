package br.com.gomide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gomide.data.vo.v1.PhoneVO;
import br.com.gomide.mapper.DozerMapper;
import br.com.gomide.model.Phone;
import br.com.gomide.services.PhoneService;

@RestController
@RequestMapping("/api/v1/phones")
public class PhoneController {

  @Autowired
  private PhoneService phoneService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<PhoneVO> findAll() {

    return DozerMapper.parseListObjects(
        phoneService.findAll(), PhoneVO.class);
  }

  @GetMapping(value = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public PhoneVO findById(
      @PathVariable(value = "id") Long id) {
    return DozerMapper.parseObject(
        phoneService.findById(id), PhoneVO.class);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public PhoneVO create(@RequestBody PhoneVO phone) {
    return DozerMapper.parseObject(
        phoneService.create(
            DozerMapper.parseObject(phone, Phone.class)),
        PhoneVO.class);
  }

  @PutMapping(value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public PhoneVO update(@PathVariable(value = "id") Long id,
      @RequestBody PhoneVO phone) {
    return DozerMapper.parseObject(
        phoneService.update(id,
            DozerMapper.parseObject(phone, Phone.class)),
        PhoneVO.class);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> delete(
      @PathVariable(value = "id") Long id) {
    phoneService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
