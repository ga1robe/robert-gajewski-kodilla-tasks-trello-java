package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
//        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("tasks_url", "https://damp-bayou-80913.herokuapp.com/v1/trello");
//        context.setVariable("tasks_url", "http://localhost:8080/v1/trello");
        context.setVariable("button", "Visit website");
//        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("admin_config", adminConfig);

        context.setVariable("company_config", companyConfig);
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);

        context.setVariable("application_functionality", functionality);

        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String tasksQuantityEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://damp-bayou-80913.herokuapp.com/v1/trello/");
//        context.setVariable("tasks_url", "http://localhost:8080/v1/trello/");
        context.setVariable("button", "Look at data!");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_config", companyConfig);

        return templateEngine.process("mail/tasks-quantity-trello-card-mail", context);
    }

}
