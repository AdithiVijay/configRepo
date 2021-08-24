package com.eatza.deliveryservice.config;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
public class KafkaConfig {
	
	@InjectMocks
	KafkaConsumerConfig kafkaConfiguration;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void userConsumerFactory() {
		kafkaConfiguration.consumerConfig();
		assertNotNull(kafkaConfiguration.consumerConfig());
	}
	
	@Test
	public void userKafkaListenerFactory() {
		kafkaConfiguration.kafkaListenerContainerFactory();
		assertNotNull(kafkaConfiguration.kafkaListenerContainerFactory());
	}

}
