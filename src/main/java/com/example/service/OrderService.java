package com.example.service;

import com.example.model.vo.MemberVo;
import com.example.model.vo.OrderVo;
import com.example.utils.PageVo;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    void createOrder(Long memberId, String productName, Long quantity);

    PageVo<OrderVo> getOrders(String orderId, String productName, LocalDateTime fromDate, LocalDateTime toDate, int page, int size);

    List<MemberVo> getMemberStatistics(int minOrderCount);
}
