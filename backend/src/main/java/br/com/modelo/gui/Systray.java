package br.com.modelo.gui;

import br.com.modelo.util.Constantes;
import br.com.modelo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

public class Systray implements Serializable {
    private static final long serialVersionUID = -4969680024232053573L;
    private TrayIcon trayIcon;
    @Autowired private Environment env;



    public void init() throws IOException {
        if (!SystemTray.isSupported()) {
            return;
        }

        SystemTray tray = SystemTray.getSystemTray();

        ActionListener exitListener = e -> {
            int answer = JOptionPane.showConfirmDialog(null, "Deseja realmente sair do sistema? ", "Saindo...", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        };

        ActionListener mostramsglistener = e -> testarConexao();

        ActionListener sysListener = e -> {
            mostrarMenssagem();
            testarConexao();
        };

        PopupMenu popup = new PopupMenu("Menu de Opções");

        MenuItem mostramsg = new MenuItem("Testar Conexão");
        mostramsg.addActionListener(mostramsglistener);
        popup.add(mostramsg);

        popup.addSeparator();

        MenuItem defaultItem = new MenuItem("Sair");
        defaultItem.addActionListener(exitListener);
        popup.add(defaultItem);

        try {
            Image image = Toolkit.getDefaultToolkit().getImage("");
            trayIcon = new TrayIcon(image, "Versão: " + Util.getVersion(), popup);
            trayIcon.addActionListener(sysListener);
            trayIcon.setImageAutoSize(true);
            tray.add(trayIcon);
            mostrarMenssagem();
            testarConexao();
        } catch (AWTException e) {
            e.printStackTrace();
            System.err.println("Erro, TrayIcon não sera adicionado.");
        }
    }


    private void mostrarMenssagem() {
        trayIcon.displayMessage("Atenção","O Service está rodando na porta " + getPorta(), TrayIcon.MessageType.INFO);
    }


    private void testarConexao() {
        if (!Desktop.isDesktopSupported()) {
            return;
        }
        try {
            Desktop.getDesktop().browse(new URI(Util.getURL(getPorta())));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


    private String getPorta(){
        return env.getProperty(Constantes.PORT_PROP, Constantes.DEFAULT_PORT);
    }


}
