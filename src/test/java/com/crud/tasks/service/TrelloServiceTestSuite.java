package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTestSuite {
    @InjectMocks
    TrelloService trelloService;
    @Mock
    AdminConfig adminConfig;
    @Mock
    TrelloClient trelloClient;

    @Test
    public void testFetchTrelloBoards() {
        /* Given */
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "test board", new ArrayList<>());
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);
        lenient().when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);
        /* When */
        List<TrelloBoardDto> fetchedTrelloBoardsList = trelloService.fetchTrelloBoards();
        /* Then */
        assertEquals(1, fetchedTrelloBoardsList.size());
    }
}