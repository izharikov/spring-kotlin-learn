package com.example.demo.services

import com.example.demo.aop.aspects.introductions.SampleA
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author Ihar Zharykau
 */

@RunWith(SpringRunner::class)
@SpringBootTest
class ServiceTest {
    @Autowired
    lateinit var service: Service

    @Test
    fun checkDoubleAspectWorks(){
        var input = "1234";
        var res = service.doSomeWithString(input);
        Assert.assertNotEquals(input, res)
        Assert.assertEquals(input + input, res)
    }
}