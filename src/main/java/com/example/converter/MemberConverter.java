package com.example.converter;

import com.example.model.entity.MemberEntity;
import com.example.model.vo.MemberVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberConverter {

    MemberConverter INSTANCES = Mappers.getMapper(MemberConverter.class);

    MemberVo toVo(MemberEntity memberEntity);

}
