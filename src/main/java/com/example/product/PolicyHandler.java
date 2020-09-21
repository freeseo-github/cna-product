package com.example.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler {

//    @StreamListener(Processor.INPUT)
//    public void onEventByString(@Payload String productChanged){
//        System.out.println(productChanged);
//    }

//    @StreamListener(Processor.INPUT)
//    public void onEventByObject(@Payload ProductChanged productChanged){
//        if("ProductChanged".equals(productChanged.getEventType())) {
//            System.out.println("onEventByObjet getEnentType " + productChanged.getEventType());
//            System.out.println("onEventByObjet getEnentType " + productChanged.getProductName());
//        }
//    }

    @Autowired
    ProductRepository productRepository;

    @StreamListener(Processor.INPUT)
    public void onEventByObject(@Payload OrderPlaced orderPlaced){
        // orderPlaced 데이터를 json --> 객체로 파싱 --> 클래스를 복사해서 가져옴

        //if (주문이 생성됭었을때만)
        if("OrderPlaced".equals(orderPlaced.getEventType())){

            // 상품저장
            Product p = new Product();
            p.setId(orderPlaced.getProductId());
            p.setStock(orderPlaced.getQty());
            productRepository.save(p);

            //상품 ID 값의 재고 변경
            //Optional<Product> productById = productRepository.findById(orderPlaced.getProductId());



        }

        //상품저장

    }
}
