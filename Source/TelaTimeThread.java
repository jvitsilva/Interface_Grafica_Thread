package Thread;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaTimeThread extends JDialog {


    private JPanel jpanel = new JPanel(new GridBagLayout());
    
    private JLabel horas = new JLabel("Time Thread 1");
    private JTextField mostraTempo = new JTextField();

    private JLabel horas2 = new JLabel("Time Thread 2");
    private JTextField mostraTempo2 = new JTextField();
    
    private JButton botao = new JButton("Start");
    private JButton botao2 = new JButton("Stop");


    private Runnable thread1 = new Runnable() {

        @Override
        public void run() {
            while(true){ // fica sempre rodando
                mostraTempo.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm.ss").
                    format(Calendar.getInstance().getTime()));
                    
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
    };

    
    private Runnable thread2 = new Runnable() {

        @Override
        public void run() {
            while(true){ // fica sempre rodando
                mostraTempo2.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").
                    format(Calendar.getInstance().getTime()));
                    
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
    };
    

    

    private Thread thread1time;
    private Thread thread2time; 

    public TelaTimeThread(){ //executa o q tiver dentro no momento da abertura ou execução
        setTitle("Tela de time com thread");
        setSize(240, 240);
        setLocationRelativeTo(null);
        setResizable(false);
       
        GridBagConstraints g = new GridBagConstraints();
       g.gridx = 0;
       g.gridy = 0;
       g.gridwidth = 2; // vai fazer os 2 componentes ocuparem 2 espaços no eixo x;
       g.anchor = g.CENTER;
       g.insets = new Insets(5, 10, 5, 5); 

       //1° componente, linha 1 
       horas.setPreferredSize(new Dimension(200, 25));
       jpanel.add(horas, g);

       //2° componente, linha 2 
       mostraTempo.setPreferredSize(new Dimension(200, 25));
       g.gridy++; 
       mostraTempo.setEditable(false);
       jpanel.add(mostraTempo, g);

       //3° componente, linha 3  
       horas2.setPreferredSize(new Dimension(200, 25));
       g.gridy++; 
       jpanel.add(horas2, g);

       //4° componente, linha 4 
       mostraTempo2.setPreferredSize(new Dimension(200, 25));
       g.gridy++; 
       mostraTempo2.setEditable(false);
       jpanel.add(mostraTempo2, g);



       //************** Botões **********************/

       g.gridwidth = 1; // vai fazer os 2 botões ocuparem 1 espaços no eixo x;

       botao.setPreferredSize(new Dimension(92, 25));
       g.gridy++;
       jpanel.add(botao, g);

       botao2.setPreferredSize(new Dimension(92, 25));
       g.gridx++;
       jpanel.add(botao2, g);


       
        botao.addActionListener( new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) { // executa o click no botão
                thread1time = new Thread(thread1);
                thread1time.start();

                thread2time = new Thread(thread2);
                thread2time.start();

                botao.setEnabled(false);
                botao2.setEnabled(true);
            }

        });

        botao2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                thread1time.stop();
                thread2time.stop();

                botao.setEnabled(true);
                botao2.setEnabled(false);
            }
            
        });


        
       botao2.setEnabled(false);
       add(jpanel, BorderLayout.WEST);
        //sempre será o último comando
        setVisible(true); //torna a tela visível para o usuário
    } 

}
