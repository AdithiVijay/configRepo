package com.eatza.deliveryservice.service.deliveryservice;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eatza.deliveryservice.dto.DeliveryDto;
import com.eatza.deliveryservice.exception.DeliveryException;
import com.eatza.deliveryservice.model.Delivery;
import com.eatza.deliveryservice.repository.DeliveryRepository;
@Service
public class DeliveryServiceImpl implements DeliveryService {
	
	@Autowired
	DeliveryRepository deliveryRepo;
	private ModelMapper mapper;
	

   private static final String TOPIC = "TestTopic";
	

	private static final Logger logger = LoggerFactory.getLogger( DeliveryServiceImpl .class);

	@Override
	public String saveDeliveryDetails(Delivery delivery) throws DeliveryException {
		if(delivery==null || delivery.getCustomerId()==0 || delivery.getOrderId()==0||delivery.getRestaurantId()==0)
		{
			throw new DeliveryException("Could not save Details");
		}
	
		logger.debug("setting status as delivered");
		delivery.setStatus("DELIVERED");
		logger.debug("saving order in db");
		
		deliveryRepo.save(delivery);
		return "Details Saved";
		
	
	}

	@Override
	public Delivery findById(Long deliveryId) throws DeliveryException {
		Delivery delivery =new Delivery();
		if(deliveryRepo.existsById(deliveryId))
		{
			delivery=deliveryRepo.findById(deliveryId).get();
			
		}
		else
		{
			throw new DeliveryException("Delivery ID not found");
		}
		return delivery;
	}
}
