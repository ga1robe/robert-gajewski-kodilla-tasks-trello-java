package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatedTrelloCard {
    private String id;
    private String name;
    private String shortUrl;
    private TrelloBadgesDto badges;
}
