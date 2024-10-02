package com.example.controller;

import com.example.model.dto.OrderDto;
import com.example.resp.Resp;
import com.example.resp.RespCode;
import com.example.service.OrderService;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author garyssu
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Resp createOrder(@RequestBody OrderDto orderDto) {
        orderService.createOrder(orderDto.getMemberId(), orderDto.getProductName(), orderDto.getQuantity());
        return new Resp(RespCode.SUCCESS);
    }

    @GetMapping
    public Resp getOrders(
        @RequestParam(value = "orderId", required = false) String orderId,
        @RequestParam(value = "productName", required = false) String productName,
        @RequestParam(value = "fromDate", required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
        @RequestParam(value = "toDate", required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "size", defaultValue = "10") int size) {

        return new Resp(RespCode.SUCCESS, orderService.getOrders(orderId, productName, fromDate, toDate, page, size));
    }

    @GetMapping("/statistics")
    public Resp getMemberStatistics(@RequestParam(value = "minOrderCount", defaultValue = "1") int minOrderCount) {
        return new Resp(RespCode.SUCCESS, orderService.getMemberStatistics(minOrderCount));
    }

}
