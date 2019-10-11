package com.github.ojacquemart.api.restaurant.menu

import com.github.ojacquemart.api.restaurant.util.jsoup.JsoupDocument
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate

@ActiveProfiles("test")
// use dirty context to force a fresh context with a new mock LocalDate at each test execution
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner::class)
@SpringBootTest
class MenuServiceTest {

    @Autowired
    lateinit var menuService: MenuService

    @MockkBean
    lateinit var menuParser: MenuParser

    @Before
    fun setUp() {
        mockkObject(JsoupDocument)
        every { JsoupDocument.url(any()) } returns JsoupDocument.file("/20160429-api-restauration.html")

        every { menuParser.parse(any()) } returns Menu(listOf())
    }

    @After
    fun tearDown() {
        verify { LocalDate.now() }
        verify { JsoupDocument.url(any()) }
        verify { menuParser.parse(any()) }
    }

    @Test
    fun getMenu() {
        // force a friday so we should get a yesterday menu
        mockkStatic("java.time.LocalDate")
        every { LocalDate.now() } returns LocalDate.of(2019, 10, 11)

        val menu = menuService.getMenu()
        assertNotNull(menu)
        assertNotNull(menu.yesterdayMenu)
    }

    @Test
    fun getMenuWithNotYesterdayMenu() {
        // force a monday so we should not get a yesterday menu
        mockkStatic("java.time.LocalDate")
        every { LocalDate.now() } returns LocalDate.of(2019, 10, 7)

        val menu = menuService.getMenu()
        assertNotNull(menu)
        assertNull(menu.yesterdayMenu)
    }

}
