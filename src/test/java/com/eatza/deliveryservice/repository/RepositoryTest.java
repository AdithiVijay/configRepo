package com.eatza.deliveryservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.eatza.deliveryservice.DeliveryserviceApplication;
import com.eatza.deliveryservice.model.Delivery;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTest {
	@Autowired
	DeliveryRepository repository;
	private Delivery delivery;
	@Before
	public void setup()
	{
		Delivery saveDelivery=new Delivery(1L,"Delivered",1L,1L);
		delivery=repository.save(saveDelivery);
		System.out.println(saveDelivery.toString());
	}
	@Test
	public void testRepository()
	{
		Delivery deliveryDetails=repository.findById(delivery.getDeliveryId()).get();
		assertEquals(delivery.getDeliveryId(), deliveryDetails.getDeliveryId());
		assertEquals(delivery.getRestaurantId(),deliveryDetails.getRestaurantId() );
	}

}