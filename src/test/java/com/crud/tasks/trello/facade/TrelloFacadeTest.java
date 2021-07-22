package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    void shouldFetchEmptyList() {
        /* Given */
        List<TrelloListDto> trelloLists = List.of(new TrelloListDto("1", "test_list", false));
        List<TrelloBoardDto> trelloBoards = List.of(new TrelloBoardDto("1", "test", trelloLists));
        List<TrelloList> mappedTrelloLists = List.of(new TrelloList("1", "test_list", false));
        List<TrelloBoard> mappedTrelloBoards = List.of(new TrelloBoard("1", "test", mappedTrelloLists));

        lenient().when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        lenient().when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        lenient().when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(List.of());
        lenient().when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(List.of());

        /* When */
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        /* Then */
        assertThat(trelloBoardDtos).isNotNull();
        assertThat(trelloBoardDtos.size()).isEqualTo(0);
    }

    @Test
    public void shouldFetchTrelloBoards() {
        /* Given */
        List<TrelloListDto> trelloLists = List.of(new TrelloListDto("1", "test_list", false));
        List<TrelloBoardDto> trelloBoards = List.of(new TrelloBoardDto("1", "test", trelloLists));
        List<TrelloList> mappedTrelloLists = List.of(new TrelloList("1", "test_list", false));
        List<TrelloBoard> mappedTrelloBoards = List.of(new TrelloBoard("1", "test", mappedTrelloLists));

        lenient().when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        lenient().when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        lenient().when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        lenient().when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        /* When */
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        /* Then */
        assertThat(trelloBoardDtos).isNotNull();
        assertThat(trelloBoardDtos.size()).isEqualTo(1);

        trelloBoardDtos.forEach(trelloBoardDto ->{
            assertThat(trelloBoardDto.getId()).isEqualTo("1");
            assertThat(trelloBoardDto.getName()).isEqualTo("test");

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertThat(trelloListDto.getId()).isEqualTo("1");
                assertThat(trelloListDto.getName()).isEqualTo("test_list");
                assertThat(trelloListDto.isClosed()).isFalse();
            });
        });
    }

    @Test
    public void shouldCreateCardTest() {
        /* Given */
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card1", "Test card", "pos1", "List1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "Card01", "http://Test.com");
        lenient().when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        /* When */
        CreatedTrelloCardDto newCard = trelloService.createTrelloCard(trelloCardDto);

        /* Then */
        assertNotNull(newCard);
        assertEquals("1", newCard.getId());
        assertEquals("Card01", newCard.getName());
        assertEquals("http://Test.com", newCard.getShortUrl());
    }
}