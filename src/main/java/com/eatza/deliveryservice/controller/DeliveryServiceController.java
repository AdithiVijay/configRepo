package com.eatza.deliveryservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eatza.deliveryservice.dto.DeliveryDto;
import com.eatza.deliveryservice.exception.DeliveryException;
import com.eatza.deliveryservice.model.Delivery;
import com.eatza.deliveryservice.service.deliveryservice.DeliveryService;
import com.eatza.deliveryservice.service.deliveryservice.DeliveryServiceImpl;

@RestController

public class DeliveryServiceController {
	
	@Autowired
	DeliveryService deliveryservice;
	private static final Logger logger = LoggerFactory.getLogger( DeliveryServiceController .class);


@PostMapping("/delivery")
@KafkaListener(topics="${kafka.topic.name}",groupId="${kafka.consumer.group.id}")
public ResponseEntity<String> saveDeliveryDetails( DeliveryDto delivery) throws DeliveryException
{
	Delivery deliveryDetails=new Delivery(delivery.getOrderId(),delivery.getStatus(),delivery.getCustomerId(),delivery.getRestaurantId());
	String message=null;
		System.out.println("From kafka" +delivery.toString());
		message=deliveryservice.saveDeliveryDetails(deliveryDetails);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(message);
	
}
@GetMapping("getById")
public ResponseEntity<Delivery> findById(Long deliveryId) throws DeliveryException
{
	Delivery delivery=deliveryservice.findById(deliveryId);
	return ResponseEntity
			.status(HttpStatus.OK)
			.body(delivery); 
}
}
