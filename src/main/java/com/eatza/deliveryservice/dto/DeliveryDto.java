package com.eatza.deliveryservice.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryDto {
	
	private Long orderId;
	private String status;
	private Long customerId;
	private Long restaurantId;
	
	

	public DeliveryDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getOrderId() {
		return orderId;
	}



	



	public String getStatus() {
		return status;
	}






	public Long getCustomerId() {
		return customerId;
	}



	



	public Long getRestaurantId() {
		return restaurantId;
	}




	

}
