package com.qxk.springall.springcloudsleuth.controller.request;

import com.qxk.springall.springcloudsleuth.model.OrderState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderStateRequest {
    private OrderState state;
}
