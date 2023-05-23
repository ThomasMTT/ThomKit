package es.thom.thomkit;

import es.thom.thomkit.enums.LogType;
import es.thom.thomkit.tools.ConsoleLogger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(Main.class);
        springApplicationBuilder.headless(false);
        ConfigurableApplicationContext context = springApplicationBuilder.run(args);
        System.out.println();
        ConsoleLogger.log("Starting ThomKit...", LogType.LogicInfo);
    }
}
