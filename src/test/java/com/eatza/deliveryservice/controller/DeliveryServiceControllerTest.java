package com.eatza.deliveryservice.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.eatza.deliveryservice.exception.DeliveryException;
import com.eatza.deliveryservice.model.Delivery;
import com.eatza.deliveryservice.service.deliveryservice.DeliveryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value= DeliveryServiceController .class)
public class DeliveryServiceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DeliveryServiceImpl deliveryService;

	@Autowired
	private ObjectMapper objectMapper;

	 
	@Test
	public void saveDeliveryDetails() throws Exception {
		Delivery delivery=new Delivery();
		delivery.setDeliveryId(1L);
		delivery.setStatus("DELIVERED");
		delivery.setRestaurantId(1L);
		delivery.setCustomerId(1L);
		delivery.setCustomerId(1L);
		when(deliveryService.saveDeliveryDetails(any(Delivery.class))).thenReturn("Details Saved");
		RequestBuilder request = MockMvcRequestBuilders.post(
				"/delivery")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((delivery))
				);
		
		mockMvc.perform(request)
		.andExpect(status().is(200))
		.andReturn();

}
	@Test
	public void badrequestsaveDetails() throws Exception
	{
		Delivery delivery=new Delivery();
		delivery.setDeliveryId(1L);
		delivery.setStatus("DELIVERED");
		delivery.setRestaurantId(1L);
		delivery.setCustomerId(1L);
		delivery.setCustomerId(1L);
		when(deliveryService.saveDeliveryDetails(any(Delivery.class))).thenThrow(DeliveryException.class);
		RequestBuilder request = MockMvcRequestBuilders.post(
				"/deliveryservice")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((delivery))
				);
		
		mockMvc.perform(request)
		.andExpect(status().is(404))
		.andReturn();
		
	}
	@Test
	public void handlebadrequest() throws Exception
	{
		Delivery delivery=new Delivery();
		delivery.setDeliveryId(1L);
		delivery.setStatus("DELIVERED");
		delivery.setRestaurantId(1L);
		delivery.setCustomerId(1L);
		delivery.setCustomerId(1L);
		when(deliveryService.saveDeliveryDetails(any(Delivery.class))).thenThrow(DeliveryException.class);
		RequestBuilder request = MockMvcRequestBuilders.post(
				"/delivery")
				.contentType(MediaType.APPLICATION_XML)
				.content(objectMapper.writeValueAsString((delivery))
				);
		
		mockMvc.perform(request)
		.andExpect(status().is(400))
		.andReturn();
	}
	@Test
	public void negativeException() throws Exception
	{
		Delivery delivery=null;
		when(deliveryService.saveDeliveryDetails(delivery)).thenThrow(DeliveryException.class);
		RequestBuilder request = MockMvcRequestBuilders.post(
				"/delivery")
				.contentType(MediaType.APPLICATION_XML)
				.content(objectMapper.writeValueAsString((delivery))
				);
		mockMvc.perform(request)
		.andExpect(status().is(200))
		.andReturn();

	}
	@Test
	public void positiveFindByid() throws Exception
	{
		Delivery delivery=new Delivery();
		delivery.setDeliveryId(1L);
		delivery.setStatus("DELIVERED");
		delivery.setRestaurantId(1L);
		delivery.setCustomerId(1L);
		delivery.setCustomerId(1L);
		when(deliveryService.findById(1L)).thenReturn(delivery);
		RequestBuilder request = MockMvcRequestBuilders.get(
				"/getById")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((delivery))
				);
			mockMvc.perform(request)
		.andExpect(status().is(200))
		.andReturn();
	}
	@Test
	public void badrequest() throws Exception
	{
		Delivery delivery=new Delivery();
		delivery.setDeliveryId(1L);
		delivery.setStatus("DELIVERED");
		delivery.setRestaurantId(1L);
		delivery.setCustomerId(1L);
		delivery.setCustomerId(1L);
		when(deliveryService.findById(null)).thenThrow(DeliveryException.class);
		RequestBuilder request = MockMvcRequestBuilders.get(
				"/getById")
				.contentType(MediaType.APPLICATION_XML)
				.content(objectMapper.writeValueAsString((delivery))
				);
			mockMvc.perform(request)
		.andExpect(status().is(400))
		.andReturn();
	}
	@Test
	public void negativeFindById() throws Exception
	{
		Delivery delivery=new Delivery();
		delivery.setDeliveryId(1L);
		delivery.setStatus("DELIVERED");
		delivery.setRestaurantId(1L);
		delivery.setCustomerId(1L);
		delivery.setCustomerId(1L);
		when(deliveryService.findById(Mockito.anyLong())).thenThrow(DeliveryException.class);
		RequestBuilder request = MockMvcRequestBuilders.get(
				"/getById")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((delivery))
				);
			mockMvc.perform(request)
		.andExpect(status().is(200))
		.andReturn();
	}
	@Test
	public void findbyIdBadRequest() throws Exception
	{
		Delivery delivery=new Delivery();
		delivery.setDeliveryId(1L);
		delivery.setStatus("DELIVERED");
		delivery.setRestaurantId(1L);
		delivery.setCustomerId(1L);
		delivery.setCustomerId(1L);
		when(deliveryService.findById(Mockito.anyLong())).thenThrow(DeliveryException.class);
		RequestBuilder request = MockMvcRequestBuilders.get(
				"/getByIdSave")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((delivery))
				);
			mockMvc.perform(request)
		.andExpect(status().is(404))
		.andReturn();
	
	}
	@Test
	public void MethodNotAllowed() throws Exception
	{
		Delivery delivery=new Delivery();
		delivery.setDeliveryId(1L);
		delivery.setStatus("DELIVERED");
		delivery.setRestaurantId(1L);
		delivery.setCustomerId(1L);
		delivery.setCustomerId(1L);
		when(deliveryService.findById(Mockito.anyLong())).thenThrow(DeliveryException.class);
		RequestBuilder request = MockMvcRequestBuilders.post(
				"/getById")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((delivery))
				);
			mockMvc.perform(request)
		.andExpect(status().is(405))
		.andReturn();	
	}
	@Test
	public void MethodnotAllowedSaveDetails() throws Exception
	{
		Delivery delivery=new Delivery();
		delivery.setDeliveryId(1L);
		delivery.setStatus("DELIVERED");
		delivery.setRestaurantId(1L);
		delivery.setCustomerId(1L);
		delivery.setCustomerId(1L);
		when(deliveryService.saveDeliveryDetails(any(Delivery.class))).thenThrow(DeliveryException.class);
		RequestBuilder request = MockMvcRequestBuilders.get(
				"/delivery")
				.contentType(MediaType.APPLICATION_XML)
				.content(objectMapper.writeValueAsString((delivery))
				);
		
		mockMvc.perform(request)
		.andExpect(status().is(405))
		.andReturn();
	}
}