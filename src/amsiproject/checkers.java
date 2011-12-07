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
    	Plansza plansza;
        Button bNowa;
        Button bZasad;
		
	public okno(String Nazwa, int szer, int wys)
	{
                super(Nazwa);
		setLayout(null);
		
                plansza = new Plansza();

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
                bNowa.addActionListener(this);	

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
                   repaint();
		}
                
	}
        
              public void paint(Graphics g)
	{
		RysujPlansze(g);
	}	
	
	public void RysujPlansze(Graphics g)
	{	
		Image img = createImage(getSize().width,getSize().height);
		
		Graphics2D g2 = (Graphics2D) img.getGraphics();
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
                
		g2.setColor(Color.black);
		g2.fillRect(18,38,322,322);	
				
		for (int j = 0; j < 8; j++)
		{
			for (int i = 0; i < 8; i++)	
			{
                            //WyĹ›wietlenie szachownicy
				if (plansza.pole[i][j] == 0) 
					g2.setColor(new Color(0,0,0)); 
				else 
					g2.setColor(new Color(255,255,255)); 			
				g2.fillRect(20 + 40*i, 40 + 40*j,38,38);	
                          
			    //wyĹ›wietlenie pionkĂłw
				if (plansza.pole[i][j] > 1) 
				{
					g2.setColor(Color.black);
					g2.fillOval(21 + 40*i, 41 + 40*j,36,36);
					
					if (plansza.pole[i][j] == 2 || plansza.pole[i][j] == 4) 
						g2.setColor(new Color(255,0,0)); //245, 240, 240
					if (plansza.pole[i][j] == 3 || plansza.pole[i][j] == 5) 
						g2.setColor(new Color(0,255,0)); //215, 95, 95
						
					g2.fillOval(23 + 40*i, 43 + 40*j,32,32);
					
					if (plansza.pole[i][j] == 4 || plansza.pole[i][j] == 5) 
					{
						g2.setColor(Color.black);
						g2.fillOval(26 + 40*i, 46 + 40*j,26,26);	
					}
					
				}
			     			
			}
		}


		g.drawImage(img,0,0,this);
		
	
		
	}
	
        
class Tablica
{
	public int pole[][];
	
	public Tablica()
	{
		pole = new int[8][8];
		this.zerowanie();
	}
	
	public void zerowanie()
	{		
		for (int j = 0; j < 8; j++)
		{
			for (int i = 0; i < 8; i++)			
			{
				pole[i][j] = 0;	
			}
		}
	}
}

class Plansza extends Tablica
{
	public Plansza(){}	
//k=1 jasne, k=0 ciemne (pola szachownicy)
	public void zerowanie()
	{
		int k = 1;
		for (int j = 0; j < 8; j++)	
		{
			k = (k + 1) % 2;
			for (int i = 0; i < 8; i++)
			{	
				pole[i][j] = k;
				k = (k + 1) % 2;
			}
		}		
	}
        
	//ustawienie pionkĂłw na szachownicy
	public void rozpoczecie()
	{
		zerowanie();
	//	gracz 2 - gora planszy
		for (int j = 0; j < 3; j++)	
			for (int i = 0; i < 8; i++)
				if (pole[i][j] == 1) pole[i][j] = 3;
	
	//	gracz 1 - dol planszy		
		for (int j = 5; j < 8; j++)	
			for (int i = 0; i < 8; i++)
				if (pole[i][j] == 1) pole[i][j] = 2;
	}
}
  
}