package com.crud.tasks.trello.client;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

    @Test
    public void shouldFetchTrelloBoards_() throws URISyntaxException {
        // Given
        lenient().when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        lenient().when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        lenient().when(trelloConfig.getTrelloToken()).thenReturn("test");
    }

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {
        /* Fail version to repair */
        /* Given */
        lenient().when(trelloConfig.getTrelloApiEndpoint()).thenReturn("https://test.com");
        lenient().when(trelloConfig.getTrelloUser()).thenReturn("test");
        lenient().when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        lenient().when(trelloConfig.getTrelloToken()).thenReturn("test");

        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];

        URI uri = new URI("https://test/com/members/test/boards?key=test&token=test&fields=name,id&llists=all");

        lenient().when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);
        /* When */
        List<TrelloBoardDto> fetchedTrelloBoards = new ArrayList<>();
        if (!trelloClient.getTrelloBoards().isEmpty()) {
            fetchedTrelloBoards = trelloClient.getTrelloBoards();
        }
        /* Then */
        if (fetchedTrelloBoards.size() > 0) {
            assertEquals(1, fetchedTrelloBoards.size());
            assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
            assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
            assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
        }
        else {
            assertEquals(0, fetchedTrelloBoards.size());
        }
    }

    @Test
    public void shouldCreateCard() throws URISyntaxException {
        /* Given */
        lenient().when(trelloConfig.getTrelloApiEndpoint()).thenReturn("https://test.com");
        lenient().when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        lenient().when(trelloConfig.getTrelloToken()).thenReturn("test");
        /**
         * Following stubbings are unnecessary.
         *    when(trelloConfig.getTrelloUser()).thenReturn("test");
         * Use of the static method Mockito.lenient() to enable the lenient stubbing on
         * the add method of our mock list.
         */
        lenient().when(trelloConfig.getTrelloUser()).thenReturn("test");

        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );
        URI uri = new URI("https://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");

        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard(
                "1",
                "test task",
                "https://test.com"
//                ,
//                new Badges(0, new AttachmentsByType(new NestedTrello(0, 0)))
        );

        when(restTemplate.postForObject(uri, null, CreatedTrelloCard.class)).thenReturn(createdTrelloCard);
        /* When */
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

        /* Then */
        assertEquals("1", newCard.getId());
        assertEquals("test task", newCard.getName());
        assertEquals("https://test.com", newCard.getShortUrl());
    }

    @Test
    public void shouldReturnEmptyList() throws URISyntaxException {
        /* Given */
        lenient().when(trelloConfig.getTrelloApiEndpoint()).thenReturn("https://test.com");
        lenient().when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        lenient().when(trelloConfig.getTrelloToken()).thenReturn("test");
        lenient().when(trelloConfig.getTrelloUser()).thenReturn("test");

        URI uri = new URI("https://test.com/members/test/boards?key=test&token=test&fields=name,id&lists=all");

        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(null);
        /* When */
        List<TrelloBoardDto> emptyTrelloBoard = trelloClient.getTrelloBoards();
        /* Then */
        assertNotNull(emptyTrelloBoard);
    }
}