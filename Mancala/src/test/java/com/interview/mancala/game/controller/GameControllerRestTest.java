package com.interview.mancala.game.controller;

import com.interview.mancala.game.gameplay.GameService;
import com.interview.mancala.game.gameplay.GameStateDTO;
import com.interview.mancala.main.MancalaApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MancalaApplication.class)
@WebAppConfiguration
public class GameControllerRestTest {

    @MockBean
    private GameService gameService;

    @Autowired
    private GameController gameController;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        // Setup Spring test in webapp-mode (same config as spring-boot)
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void homePage() throws Exception {
        GameStateDTO gameStateDTO = createDummyGameStateDTO();
        Mockito.when(gameService.getCurrentState()).thenReturn(gameStateDTO);

        this.mockMvc.perform(get("/")).andExpect(status().isOk());

        Mockito.verify(gameService).getCurrentState();
    }

    @Test
    public void play() throws Exception {
        GameStateDTO gameStateDTO = createDummyGameStateDTO();
        Mockito.when(gameService.play(3)).thenReturn(gameStateDTO);

        this.mockMvc.perform(post("/play/3")).andExpect(status().isOk());

        Mockito.verify(gameService).play(3);
    }


    @Test
    public void reset() throws Exception {
        GameStateDTO gameStateDTO = createDummyGameStateDTO();
        Mockito.when(gameService.reset()).thenReturn(gameStateDTO);

        this.mockMvc.perform(post("/reset")).andExpect(status().isOk());

        Mockito.verify(gameService).reset();
    }


    private GameStateDTO createDummyGameStateDTO() {
        GameStateDTO gameStateDTO = Mockito.mock(GameStateDTO.class);
        Mockito.when(gameStateDTO.getWinnerMessage()).thenReturn(null);
        return gameStateDTO;
    }
}