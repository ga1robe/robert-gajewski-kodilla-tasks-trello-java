package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

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
        Context context = new Context();
        context.setVariable("message", message);
//        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("tasks_url", "https://damp-bayou-80913.herokuapp.com/v1/task/");
//        context.setVariable("tasks_url", "http://localhost:8080/v1/task/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());

        context.setVariable("application_name", "Kodilla CRUD App");
        context.setVariable("application_function", "Application that allows to send tasks to Trello Board ");

        context.setVariable("company_config", companyConfig);
        context.setVariable("is_friend", false);
//        context.setVariable("application_functionality", functionality);


        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
