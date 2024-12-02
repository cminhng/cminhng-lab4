import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class EnigmaFrame extends JFrame{
    private JComboBox<Integer> inner;
    private JComboBox<Integer> middle;
    private JComboBox<Integer> outer; 
    private JTextField start;
    private JButton encrypt;
    private JButton decrypt;
    private JTextArea input;
    private JTextArea output;

    private final Integer[] rotors = {
        1, 2, 3, 4, 5
    };

    public EnigmaFrame(){        
        inner = new JComboBox<Integer>(rotors);
        middle = new JComboBox<Integer>(rotors);
        outer = new JComboBox<Integer>(rotors);
        start = new JTextField("###");
        input = new JTextArea(10, 60);
        output = new JTextArea(10, 60);
        output.setEditable(false);
        encrypt = new JButton("encrypt");
        decrypt = new JButton("decrypt");

        JPanel top = new JPanel(new FlowLayout()); 
        top.add(new JLabel("inner: "));
        top.add(inner);
        top.add(new JLabel("middle: "));
        top.add(middle);
        top.add(new JLabel("outer: "));
        top.add(outer);
        top.add(new JLabel("initial positions: "));
        top.add(start);
        top.add(encrypt);
        top.add(decrypt);
        
        encrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crypt(true);
            }
        });
        decrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crypt(false);
            }
        });

        JPanel wrap = new JPanel(new FlowLayout());
        
        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        
        // box layout resource: 
        // https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html

        JPanel in = new JPanel(new FlowLayout());
        JPanel out = new JPanel(new FlowLayout());

        in.add(new JLabel("input"));
        out.add(new JLabel("output"));

        bottom.add(in);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(input);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(out);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(output);
        bottom.add(Box.createVerticalStrut(10));

        wrap.add(bottom);

        this.add(top, BorderLayout.NORTH);
        this.add(wrap, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    private void crypt(boolean encrypt){
        int r1 = (int)inner.getSelectedItem();
        int r2 = (int)middle.getSelectedItem();
        int r3 = (int)outer.getSelectedItem();
        String pos = start.getText();
        String inputTxt = input.getText();
        Enigma enigma = new Enigma(r1, r2, r3, pos);

        String result = encrypt ? enigma.encrypt(inputTxt) : enigma.decrypt(inputTxt);
        output.setText(result);
    }
}
