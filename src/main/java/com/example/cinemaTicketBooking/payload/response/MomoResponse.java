package com.example.cinemaTicketBooking.payload.response;

import lombok.Data;

@Data
public class MomoResponse {
    private String partnerCode;
    private String orderId;
    private String requestId;
    private long amount;
    private String orderInfo;
    private String orderType;
    private long transId;
    private int resultCode;
    private String message;
    private String payType;
    private long responseTime;
    private String extraData;
    private String signature;
    private String requestType;
    private String redirectUrl;
    private String ipnUrl;
}
