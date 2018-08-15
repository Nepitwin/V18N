package com.web.asekulsk.vaadin.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

// https://www.concretepage.com/spring-4/spring-4-security-junit-test-with-withmockuser-and-withuserdetails-annotation-example-using-webappconfiguration
@ActiveProfiles("testing")
@RunWith(SpringRunner.class)
@SpringBootTest
public class V18NApplicationTests {

    @Test
    public void contextLoads() {
    }

}
