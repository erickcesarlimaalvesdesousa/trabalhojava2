package avaliacao.java;

import javax.swing.JScrollPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class BlocoDeNotas extends  JFrame  implements ActionListener {
    JTextArea areadeTexto;
    JMenuBar barraMenu;
    JMenu menuArquivo;
    JMenuItem abrirItem, salvarItem, sairItem;

    public BlocoDeNotas(){
        setTitle("Bloco de Notas");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        areadeTexto = new JTextArea();
        JScrollPane scroll = new JScrollPane(areadeTexto);
        add(scroll);

        barraMenu = new JMenuBar();
        menuArquivo = new JMenu("Arquivo");

        abrirItem = new JMenuItem("Abrir");
        salvarItem = new JMenuItem("Salvar");
        sairItem = new JMenuItem("Sair");


        menuArquivo.add(abrirItem);
        menuArquivo.add(salvarItem);
        menuArquivo.addSeparator();
        menuArquivo.add(sairItem);

        barraMenu.add(menuArquivo);


        setJMenuBar(barraMenu);

        abrirItem.addActionListener(this);
        salvarItem.addActionListener(this);
        sairItem.addActionListener(this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->  {
            new BlocoDeNotas().setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == abrirItem) {
            abrirArquivo();
        } else if (e.getSource() == salvarItem) {
            salvarArquivo();
        } else if (e.getSource() == sairItem) {
            System.exit(0);
        }

    }

    private void abrirArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        int escolha = fileChooser.showOpenDialog(this);

        if (escolha == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            try(BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
                areadeTexto.read(leitor, null);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao abrir o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void salvarArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        int escolha = fileChooser.showSaveDialog(this);

        if (escolha == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            try(BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo))) {
                areadeTexto.write(escritor);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar o arquivo ", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
