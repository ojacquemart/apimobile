package com.github.ojacquemart.api.restaurant.menu.announcer

import com.github.ojacquemart.api.restaurant.menu.Dish
import com.github.ojacquemart.api.restaurant.menu.DishGroup
import com.github.ojacquemart.api.restaurant.menu.Menu
import com.github.ojacquemart.api.restaurant.menu.MenuService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.client.ExpectedCount
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.*
import org.springframework.test.web.client.response.MockRestResponseCreators.withStatus
import org.springframework.web.client.RestTemplate
import java.net.URI

@RunWith(SpringRunner::class)
@ActiveProfiles("test")
@SpringBootTest
class AnnouncerServiceTest {

    companion object {
        private val MOCK_MENU =  Menu(
                listOf(DishGroup("foo", listOf(Dish("foo", "1€"))))
        )
        private val MOCK_MENU_CONTENT = MOCK_MENU.write(emptyList())
    }

    lateinit var mockServer: MockRestServiceServer

    @Autowired
    lateinit var announcerService: AnnouncerService

    @Autowired
    lateinit var restTemplate: RestTemplate

    @MockkBean
    lateinit var menuService: MenuService

    @Before
    fun setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate)

        // web hook tts
        mockServer.expect(ExpectedCount.times(2), requestTo(URI("http://localhost:8080/hello")))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content()
                        .json("""{"text": "$MOCK_MENU_CONTENT", "volume": "100", "lang": "fr"}""")
                )
                .andRespond(withStatus(HttpStatus.NO_CONTENT)
                        .contentType(MediaType.APPLICATION_JSON)
                )
        // simple tts
        mockServer.expect(requestTo(URI("http://localhost:8081/hello")))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content()
                        .json("""{"msg": "$MOCK_MENU_CONTENT"}""")
                )
                .andRespond(withStatus(HttpStatus.NO_CONTENT)
                        .contentType(MediaType.APPLICATION_JSON)
                )
    }

    @Test
    fun announce() {
        every { menuService.getMenu() } returns MOCK_MENU

        announcerService.announce()

        mockServer.verify()
        verify { menuService.getMenu() }
    }

}
