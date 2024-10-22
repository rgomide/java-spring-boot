package br.com.gomide.mapper.custom;

import java.util.ArrayList;
import java.util.List;

import br.com.gomide.data.vo.v1.PhoneVO;
import br.com.gomide.mapper.DozerMapper;
import br.com.gomide.model.PersonPhone;

public class PhoneMapper {

  public static List<PhoneVO> convertEntityToVO(
      List<PersonPhone> entities) {
    List<PhoneVO> vos = new ArrayList<>();

    for (PersonPhone entity : entities) {
      vos.add(convertEntityToVO(entity));
    }

    return vos;
  }

  public static PhoneVO convertEntityToVO(
      PersonPhone entity) {
    PhoneVO vo = DozerMapper.parseObject(entity.getPhone(),
        PhoneVO.class);
    vo.setPrimary(entity.isPrimary());

    return vo;
  }

}
