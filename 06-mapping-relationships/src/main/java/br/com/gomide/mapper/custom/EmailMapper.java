package br.com.gomide.mapper.custom;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gomide.data.vo.v1.EmailVO;
import br.com.gomide.mapper.DozerMapper;
import br.com.gomide.model.Email;

@Service
public class EmailMapper {

  public static List<EmailVO> convertEntityToVO(List<Email> emails) {
    List<EmailVO> vos = new ArrayList<EmailVO>();

    for (Email email : emails) {
      vos.add(convertEntityToVO(email));
    }

    return vos;
  }

  public static EmailVO convertEntityToVO(Email email) {
    EmailVO vo = DozerMapper.parseObject(email, EmailVO.class);
    vo.setPersonId(email.getPersonId());
    return vo;
  }

  public static Email convertVOToEntity(EmailVO vo) {
    return DozerMapper.parseObject(vo, Email.class);
  }

}
