package com.example.converter;

import com.example.model.entity.OrderEntity;
import com.example.model.vo.OrderVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderConverter {

    OrderConverter INSTANCES = Mappers.getMapper(OrderConverter.class);

    OrderVo toVo(OrderEntity orderEntity);

}
