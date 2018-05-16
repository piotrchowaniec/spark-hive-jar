package com.example;

import org.datanucleus.plugin.PluginManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //just to be able to attach with a debugger
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PluginManager pluginManager = PluginManager.createPluginManager(Collections.emptyMap(), Application.class.getClassLoader());
        System.out.println(pluginManager.getRegistryClassName());
        new SpringApplication(Application.class).run(args);
    }
}
