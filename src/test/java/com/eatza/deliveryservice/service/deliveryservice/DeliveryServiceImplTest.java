package com.eatza.deliveryservice.service.deliveryservice;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import static org.junit.Assert.assertThat;
import com.eatza.deliveryservice.exception.DeliveryException;
import com.eatza.deliveryservice.model.Delivery;
import com.eatza.deliveryservice.repository.DeliveryRepository;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DeliveryServiceImplTest {
	@InjectMocks
	private DeliveryServiceImpl deliveryService;

	@Mock
	private DeliveryRepository deliveryRepository;
	
	@Mock
	private RestTemplate restTemplate;
	private Delivery delivery=new Delivery();
	
	@Before
	public void init()
	{
	delivery.setDeliveryId(1L);
	delivery.setStatus("DELIVERED");
	delivery.setRestaurantId(1L);
	delivery.setCustomerId(1L);
	delivery.setOrderId(1L);
	}

	@Test
	public void saveDeliveryDetails() throws DeliveryException
	{
		Delivery delivery=new Delivery();
		delivery.setDeliveryId(1L);
		delivery.setStatus("DELIVERED");
		delivery.setRestaurantId(1L);
		delivery.setCustomerId(1L);
		delivery.setOrderId(1L);
		assertEquals("Details Saved", deliveryService.saveDeliveryDetails(delivery));
	}
	@Test(expected=DeliveryException.class)
	public void negativeTestCase() throws DeliveryException
	{
			Delivery delivery=null;
			String message = deliveryService.saveDeliveryDetails(delivery);
	}
	@Test
	public void getDeliveryDetailsByID() throws DeliveryException
	{
		when(deliveryRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(delivery));
		when(deliveryRepository.existsById(Mockito.anyLong())).thenReturn(true);
        
        Delivery deliveryDetails = deliveryService.findById(1L);
       assertNotNull(deliveryDetails);
        assertEquals(deliveryDetails.getStatus(),delivery.getStatus());
	}
	@Test(expected=DeliveryException.class)
	public void negativeTestCaseFindById() throws DeliveryException
	{
		Delivery delivery=new Delivery();
		delivery.setDeliveryId(1L);
		delivery.setStatus("DELIVERED");
		delivery.setRestaurantId(1L);
		delivery.setCustomerId(1L);
		delivery.setOrderId(1L);
		when(deliveryRepository.existsById(Mockito.anyLong())).thenReturn(false);
		when(deliveryService.findById(Mockito.anyLong())).thenThrow(DeliveryException.class);
		deliveryService.findById(delivery.getDeliveryId());
	}
	
	@After
	public void cleanObject()
	{
		delivery=null;
	}
}
