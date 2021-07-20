package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TrelloMapperTestSuite {
    @Autowired
    TrelloMapper trelloMapper;
    @Test
    public void testMapToBoards() {
        /* Given */
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("index01", "List01", false));
        trelloBoardDto.add(new TrelloBoardDto("index01", "Board01", trelloListDto));
        /*When */
        List<TrelloBoard> trelloBoard = trelloMapper.mapToBoards(trelloBoardDto);
        /* Then */
        assertEquals("Board01", trelloBoard.get(0).getName());
        assertEquals(1, trelloBoard.get(0).getLists().size());
    }

    @Test
    public void testMapToBoardsDto() {
        /* Given */
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("index01", "List01", false));
        trelloBoard.add(new TrelloBoard("index01", "Board01", trelloList));
        /* When */
        List<TrelloBoardDto> trelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoard);
        /* Then */
        assertEquals("Board01", trelloBoardDto.get(0).getName());
        assertEquals(1, trelloBoardDto.get(0).getLists().size());
    }

    @Test
    public void testMapToList() {
        /* Given */
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("index1", "List01", false));
        /* When */
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListDto);
        /* Then */
        assertEquals("List01", trelloList.get(0).getName());
        assertEquals(1, trelloList.size());
    }
    @Test
    public void testMapToListDto() {
        /* Given */
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("index1", "List01", false));
        /* When */
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloList);
        /* Then */
        assertEquals("List01", trelloListDto.get(0).getName());
        assertEquals(1, trelloList.size());
    }

    @Test
    public void testMapToCardDto() {
        /* Given */
        TrelloCard trelloCard = new TrelloCard("Card01", "New card01", "pos01", "List01");
        /* When */
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        /* Then */
        assertEquals("Card01", trelloCardDto.getName());
        assertEquals("New card01", trelloCardDto.getDescription());
        assertEquals("pos01", trelloCardDto.getPos());
        assertEquals("List01", trelloCardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        /* Given */
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card01", "New card01", "pos01", "List01");
        /* When */
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        /* Then */
        assertEquals("Card01", trelloCard.getName());
        assertEquals("New card01", trelloCard.getDescription());
        assertEquals("pos01", trelloCard.getPos());
        assertEquals("List01", trelloCard.getListId());
    }
}