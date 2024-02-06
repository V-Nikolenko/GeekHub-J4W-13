package org.geekhub.crypto;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationStarter {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.geekhub.crypto");
        context.refresh();

        UserInteractionService interactionService = context.getBean(UserInteractionService.class);
        interactionService.interactWithApplication();
    }
}
