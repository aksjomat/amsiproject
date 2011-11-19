package amsiproject;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class checkers
{
	public static void  main(String args[])
	{
		okno OknoGlowne = new okno("Warcaby",640,480);
	}	
} 

class okno extends Frame implements ActionListener
{
    	Button bNowa;
        Button bZasad;
		
	public okno(String Nazwa, int szer, int wys)
	{
                super(Nazwa);
		setLayout(null);
		
		setSize(szer,wys);
		setLocation(10,10);
		setFont(new Font("Arial",0,16));
		setResizable(false);
		setBackground(new Color(220,220,220));
                
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing (WindowEvent e)
			{
				System.exit(0);
			}
		});
   
                bNowa = new Button("Nowa gra");
		bNowa.setSize(100,25);
		bNowa.setLocation(450,90);	
                add(bNowa);
                
		bZasad = new Button("Zasady gry");
		bZasad.setSize(100,25);
		bZasad.setLocation(450,145);
                bZasad.addActionListener(this);	
	        add(bZasad);	         
        show();
	}
        
        public void actionPerformed(ActionEvent ev)
	{
		Object 	cel = ev.getSource();
		if (cel == bZasad)
		{                   
                   TextArea pole1 = new TextArea();
                   pole1.setBounds(10,30,400,440); 
                   String s = "ZASADY GRY\n"+
                              "1. Wykonywanie posunięć:\n"+
                              "# Pierwszy ruch wykonuje zawodnik grający białymi.\n"+
                              "# Gracze wykonują posunięcia po jednym ruchu, \nna przemian, własnymi warcabami.\n"+
                              "# Wykonanie ruchu jest obowiązkowe.\n"+
                              "# Kamień porusza się po przekątnej tylko do przodu\n na wolne pole następnej linii.\n"+
                              "# W sytuacji, gdy kamień staje na pole przemiany \n(dla białych to pola: b8, d8, f8, h8; dla czarnych: \na1, c1, e1, g1) staje się damką.\n"+
                              "# Damka porusza się po przekątnych we wszystkich \nkierunkach na dowolne wolne pole.\n"+
                              "\n"+
                              "2. Bicie:\n"+
                              "# Jeżeli kamień znajduje się w sąsiedztwie po \nprzekątnej przeciwnika, za którym jest wolne pole, \nto może on przeskoczyć ten kamień i zająć to wolne \npole. Kamień, który został przeskoczony, zostaje\n usunięty.\n"+
                              "# Bicie wykonuje się do przodu lub do tyłu.\n"+
                              "# Bicie jest obowiązkowe.\n"+
                              "# W wypadku, gdy istnieje wybór pomiędzy zbiciem \nróżnych ilości warcab przeciwnika, \nto obowiązkowe jest bicie większej ilości warcab.\n"+
                              "# Jeżeli zwykły kamień w czasie bicia przechodzi \nprzez jedno z pól przemiany i kontynuuje bicie,\n to nie zamienia się w damkę i nadal \npozostaje kamieniem.\n"+
                              "\n"+
                              "3. Cel i rezultat gry\n"+
                              "# Celem gry jest uniemożliwienie przeciwnikowi \nwykonania posunięcia poprzez zbicie lub\n zablokowanie jego warcab. \nW pozostałych przypadkach partię uznaje się \nza remisową.\n"+                              
                              "\n"+
                              "źródło: http://pl.wikipedia.org/wiki/Warcaby_klasyczne";
                              
                   pole1.setText(s);
                   add(pole1);
		}
	}
}