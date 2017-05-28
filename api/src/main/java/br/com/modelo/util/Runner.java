package br.com.modelo.util;

import br.com.modelo.gui.Systray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Launches the application GUI.
 *
 * @author Paul Campbell
 */
@Component
public class Runner implements CommandLineRunner {
    private final Systray frame;

    @Autowired
    public Runner(Systray frame) {
        this.frame = frame;
    }


    @Override
    public void run(String... args) throws Exception {
        java.awt.EventQueue.invokeLater(() -> {
              try {
                frame.init();
              } catch (IOException e) {
                e.printStackTrace();
              }
        });
    }

}
