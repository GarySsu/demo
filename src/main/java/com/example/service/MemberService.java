package com.example.service;

import com.example.model.entity.MemberEntity;
import com.example.model.vo.MemberVo;
import com.example.utils.PageVo;
import java.util.Map;

public interface MemberService {

    void addMember(String username);

    MemberVo getMember(Long id);

    void deleteMember(Long id);

    PageVo<MemberVo> getMemberPage(int page, int size);

    void updateMember(Long id, String username);

    Map<Long, MemberEntity> getAllmemberMap();

}
