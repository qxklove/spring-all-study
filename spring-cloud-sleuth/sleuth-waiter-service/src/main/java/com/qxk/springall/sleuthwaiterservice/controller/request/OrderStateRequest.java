package com.qxk.springall.sleuthwaiterservice.controller.request;

import com.qxk.springall.sleuthwaiterservice.model.OrderState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderStateRequest {
    private OrderState state;
}
