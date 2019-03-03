package com.example.demo.aop.aspects.introductions


import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


/**
 * @author Ihar Zharykau
 */

@RunWith(SpringRunner::class)
@SpringBootTest
class IntroductionTest {

    @Autowired
    private val sampleA: SampleA? = null

    @Autowired
    private val sampleB: SampleB? = null


    @Test
    fun shoutTest() {
        assertNotNull(sampleA)
    }
}