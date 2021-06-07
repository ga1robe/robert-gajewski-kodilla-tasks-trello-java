package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreatedTrelloCard {
    private String id;
    private String name;
    private String shortUrl;
    private Badges badges;
}