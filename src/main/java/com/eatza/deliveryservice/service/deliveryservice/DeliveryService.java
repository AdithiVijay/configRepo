package com.eatza.deliveryservice.service.deliveryservice;



import com.eatza.deliveryservice.dto.DeliveryDto;
import com.eatza.deliveryservice.exception.DeliveryException;
import com.eatza.deliveryservice.model.Delivery;

public interface DeliveryService {

	public String saveDeliveryDetails(Delivery delivery) throws DeliveryException;
	public Delivery findById(Long deliveryId) throws DeliveryException;
	
}
