package com.example.service.impl;

import com.example.converter.MemberConverter;
import com.example.converter.OrderConverter;
import com.example.handler.exception.CustomException;
import com.example.model.entity.MemberEntity;
import com.example.model.entity.OrderEntity;
import com.example.model.vo.MemberVo;
import com.example.model.vo.OrderVo;
import com.example.resp.RespCode;
import com.example.service.MemberService;
import com.example.service.OrderService;
import com.example.service.ProductService;
import com.example.utils.PageVo;
import com.example.utils.UuidGenerator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final MemberService memberService;
    private final ProductService productService;

    private final Lock lock = new ReentrantLock();
    private final Map<String, OrderEntity> orderMap = new ConcurrentHashMap<>();

    public OrderServiceImpl(MemberService memberService, ProductService productService) {
        this.memberService = memberService;
        this.productService = productService;
    }

    @Override
    public void createOrder(Long memberId, String productName, Long quantity) {
        lock.lock();
        try {
            this.processOrder(memberId, productName, quantity);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public PageVo<OrderVo> getOrders(String orderId, String productName, LocalDateTime fromDate, LocalDateTime toDate, int page, int size) {
        List<OrderEntity> filteredOrders = orderMap.values().stream()
            .filter(order -> (orderId == null || order.getOrderId().equals(orderId)) &&
                (productName == null || order.getProductName().contains(productName)) &&
                (fromDate == null || !order.getCreateTime().isBefore(fromDate)) &&
                (toDate == null || !order.getCreateTime().isAfter(toDate)))
            .collect(Collectors.toList());

        long totalRecords = filteredOrders.size();

        List<OrderVo> paginatedOrders = filteredOrders.stream()
            .skip((long) (page - 1) * size)
            .limit(size)
            .map(OrderConverter.INSTANCES::toVo)
            .collect(Collectors.toList());

        return new PageVo<>(page, size, totalRecords, paginatedOrders);
    }

    @Override
    public List<MemberVo> getMemberStatistics(int minOrderCount) {
        Map<Long, MemberEntity> memberMap = memberService.getAllmemberMap();

        Map<Long, Long> orderCountMap = orderMap.values().stream()
            .collect(Collectors.groupingBy(OrderEntity::getMemberId, Collectors.counting()));

        return orderCountMap.entrySet().stream()
            .filter(entry -> entry.getValue() > minOrderCount)
            .map(entry -> memberMap.get(entry.getKey()))
            .filter(Objects::nonNull)
            .map(MemberConverter.INSTANCES::toVo)
            .collect(Collectors.toList());
    }

    private void processOrder(Long memberId, String productName, Long quantity) {
        Map<String, Long> productMap = productService.getAllProductMap();
        Long productQuantity = productMap.get(productName);

        if (null == productQuantity) {
            throw new CustomException(RespCode.PRODUCT_NOT_EXIST.getCode(), RespCode.PRODUCT_NOT_EXIST.getMessage());
        }

        if (productQuantity < quantity) {
            throw new CustomException(RespCode.QUANTITY_NOT_ENOUGH.getCode(), RespCode.QUANTITY_NOT_ENOUGH.getMessage());
        }

        String orderId = UuidGenerator.getUuid();

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setMemberId(memberId);
        orderEntity.setOrderId(orderId);
        orderEntity.setProductName(productName);
        orderEntity.setQuantity(quantity);
        orderEntity.setCreateTime(LocalDateTime.now());
        orderMap.put(orderId, orderEntity);

        productService.reduceQuantity(productName, quantity);
    }

}
