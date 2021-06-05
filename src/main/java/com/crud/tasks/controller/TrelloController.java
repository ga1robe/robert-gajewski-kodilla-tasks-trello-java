package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("getTrelloBoards")
    public void getTrelloBoards() {

        /** GET request **/
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

//        trelloBoards.stream()
//                .filter(t -> t.getId().contains("6091a82320ed4b01b9191a6a"))
//                .filter(t -> t.getName().contains("Kodilla Application"))
//                .map(t -> "fields: {  name: \"" + t.getName() + "\" ," + "\tid: " + t.getId() + "  }")
//                .collect(Collectors.toList()).forEach(System.out::println);

        trelloBoards.forEach(trelloBoardsDto -> {
            System.out.println("fields: " +
                    "id:" + trelloBoardsDto.getId() + ", " +
                    "name: \"" + trelloBoardsDto.getName() + "\""
            );

            System.out.println("This board contains lists: ");

            trelloBoardsDto.getLists().forEach(trelloList -> {
                System.out.println(
                        "\tname: \"" + trelloList.getName() + "\"" +
                                " - " +
                                "id: " + trelloList.getId() + " - " +
                                "isClosed: " + trelloList.isClosed()
                );
            });

        });
    }

    @PostMapping( "createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }
}
