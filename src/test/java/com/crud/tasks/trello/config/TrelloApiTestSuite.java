package com.crud.tasks.trello.config;

import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TrelloApiTestSuite {

    @Autowired
    TrelloConfig trelloConfig;

    @Test
    public void testTrelloConfig() {
        assertThat(trelloConfig.getTrelloUser()).isEqualTo("609196d8f728db73039cd912");
        assertThat(trelloConfig.getTrelloApiEndpoint()).isEqualTo("https://api.trello.com/1");
        assertThat(trelloConfig.getTrelloAppKey()).isEqualTo("d3ff2753c9bd7d6c2abe885a10431f34");
        assertThat(trelloConfig.getTrelloToken()).isEqualTo("878839db9efa9f0e4d11f0b3749c06e7240178543635ce823e8a567f85090fd4");
    }
}
