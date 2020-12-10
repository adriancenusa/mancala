package com.interview.mancala.game.controller;

import com.interview.mancala.game.gameplay.GameService;
import com.interview.mancala.game.gameplay.GameStateDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @Mock
    GameService gameService;

    @InjectMocks
    GameController gameController;


    @Test
    public void homePage() {
        //given
        GameStateDTO gameStateDTO = Mockito.mock(GameStateDTO.class);
        when(gameService.getCurrentState()).thenReturn(gameStateDTO);

        Model model = Mockito.mock(Model.class);
        ArgumentCaptor<String> attributeNameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<GameStateDTO> gameStateCaptor = ArgumentCaptor.forClass(GameStateDTO.class);
        when(model.addAttribute(any(), any())).thenReturn(null);
        //when
        String result = gameController.homePage(model);
        //then
        assertEquals("home", result);
        verify(model).addAttribute(attributeNameCaptor.capture(), gameStateCaptor.capture());
        assertEquals("currentState", attributeNameCaptor.getValue());
        assertEquals(gameStateDTO, gameStateCaptor.getValue());
    }

    @Test
    public void play() {
        //given
        GameStateDTO gameStateDTO = Mockito.mock(GameStateDTO.class);
        when(gameService.play(3)).thenReturn(gameStateDTO);

        Model model = Mockito.mock(Model.class);
        ArgumentCaptor<String> attributeNameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<GameStateDTO> gameStateCaptor = ArgumentCaptor.forClass(GameStateDTO.class);
        when(model.addAttribute(any(), any())).thenReturn(null);
        //when
        String result = gameController.play(model, 3);
        //then
        assertEquals("home :: board", result);
        verify(model).addAttribute(attributeNameCaptor.capture(), gameStateCaptor.capture());
        assertEquals("currentState", attributeNameCaptor.getValue());
        assertEquals(gameStateDTO, gameStateCaptor.getValue());
    }

    @Test
    public void reset() {
        //given
        GameStateDTO gameStateDTO = Mockito.mock(GameStateDTO.class);
        when(gameService.reset()).thenReturn(gameStateDTO);

        Model model = Mockito.mock(Model.class);
        ArgumentCaptor<String> attributeNameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<GameStateDTO> gameStateCaptor = ArgumentCaptor.forClass(GameStateDTO.class);
        when(model.addAttribute(any(), any())).thenReturn(null);
        //when
        String result = gameController.reset(model);
        //then
        assertEquals("home :: board", result);
        verify(model).addAttribute(attributeNameCaptor.capture(), gameStateCaptor.capture());
        assertEquals("currentState", attributeNameCaptor.getValue());
        assertEquals(gameStateDTO, gameStateCaptor.getValue());
    }
}