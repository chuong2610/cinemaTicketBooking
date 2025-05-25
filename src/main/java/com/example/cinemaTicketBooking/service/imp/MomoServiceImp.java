package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.dto.CheckoutDTO;
import com.example.cinemaTicketBooking.entity.Bill;
import com.example.cinemaTicketBooking.entity.TicketOrder;
import com.example.cinemaTicketBooking.payload.request.CheckoutRequest;
import com.example.cinemaTicketBooking.payload.request.MomoRequest;
import com.example.cinemaTicketBooking.payload.response.CheckoutRessponse;
import com.example.cinemaTicketBooking.payload.response.MomoResponse;
import com.example.cinemaTicketBooking.repository.BillRepository;
import com.example.cinemaTicketBooking.repository.SeatRepository;
import com.example.cinemaTicketBooking.repository.TicketOrderRepository;
import com.example.cinemaTicketBooking.service.MomoService;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.UUID;

@Service
public class MomoServiceImp implements MomoService {
    @Value("${momo.partnerCode}")
    private String partnerCode;

    @Value("${momo.accessKey}")
    private String accessKey;

    @Value("${momo.secretKey}")
    private String secretKey;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private TicketOrderRepository ticketOrderRepository;

    @Autowired
    private SeatRepository seatRepository;
    public MomoRequest getMomoRequest(CheckoutDTO checkoutDTO, int billId) {
        String orderId = String.valueOf(billId)+"-"+ System.currentTimeMillis();
        String requestId = UUID.randomUUID().toString();
        long amount = checkoutDTO.getTotalPrice();
        String orderInfo = checkoutDTO.getOrderInfoForMomo();
        String redirectUrl = "http://127.0.0.1:5503/themes.themewild.com/ticket/index.html";
        String notifyUrl = "http://localhost:8080/api/momo/notify";
        String extraData = "khong co khuyen mai gì ca";

        String rawSignature = "accessKey=" + accessKey +
                "&amount=" + amount +
                "&extraData=" + extraData +
                "&ipnUrl=" + notifyUrl +
                "&orderId=" + orderId +
                "&orderInfo=" + orderInfo +
                "&partnerCode=" + partnerCode +
                "&redirectUrl=" + redirectUrl +
                "&requestId=" + requestId +
                "&requestType=captureWallet";

        try{
            String signature = signSHA256(rawSignature, secretKey);
            MomoRequest request = new MomoRequest();
            request.setPartnerCode(partnerCode);
            request.setRequestId(requestId);
            request.setAmount(amount);
            request.setOrderId(orderId);
            request.setRedirectUrl(redirectUrl);
            request.setOrderInfo(orderInfo);

            request.setIpnUrl(notifyUrl);
            request.setRequestType("captureWallet");
            request.setExtraData(extraData);
            request.setLang("vi");
            request.setSignature(signature);
            return request;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public void processMomoIpn(MomoResponse response) {
        if (response.getResultCode() == 0) {
            // Tách billId từ orderId (dạng: billId-timestamp)
            String orderId = response.getOrderId();
            String[] parts = orderId.split("-");
            int billId = Integer.parseInt(parts[0]);

            // Lấy bill và cập nhật trạng thái
            Bill bill = billRepository.findById(billId).orElse(null);
            if (bill != null && "PENDING".equals(bill.getStatus())) {
                bill.setStatus("PAID");
                billRepository.save(bill);
                seatRepository.markSeatsPaidByBillId(billId);

            }
        } else {
            // Giao dịch thất bại → bạn có thể cập nhật trạng thái là FAILED
            String orderId = response.getOrderId();
            String[] parts = orderId.split("-");
            int billId = Integer.parseInt(parts[0]);

            Bill bill = billRepository.findById(billId).orElse(null);
            if (bill != null && "PENDING".equals(bill.getStatus())) {
                bill.setStatus("FAILED");
                billRepository.save(bill);
                ticketOrderRepository.deleteByBillId(billId);

            }
        }
    }


    public  String signSHA256(String rawData, String secretKey) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] bytes = sha256_HMAC.doFinal(rawData.getBytes("UTF-8"));
        return Hex.encodeHexString(bytes);
    }
}
