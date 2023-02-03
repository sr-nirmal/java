import javax.swing.border.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;  
import java.net.*; 
import java.awt.Graphics.*;
import java.awt.geom.*;



class P{
   
    static void print_(String pmt){
        System.out.print(pmt);
    }
    static void print(String pmt[]){
        for(int i=0;i<pmt.length;i++){
            System.out.print(pmt[i]+"|");
        }
        System.out.println("\n");
    }
    static void print(String pmt){
        System.out.println(pmt);
    }
    static void print(Exception pmt){
        System.out.println(pmt);
    }
    static void print(int pmt){
        System.out.println(pmt);
    }
    static String get(String pmt){
        Scanner scan=new Scanner(System.in);
        String temp;
        System.out.print(pmt);
        temp=scan.nextLine();
        return temp;

    }
    static void print(double pmt){
        System.out.println(pmt);
    }
    static int get_int(String pmt){
        Scanner scan=new Scanner(System.in);
        int temp;
        System.out.print(pmt);
        temp=scan.nextInt();
        return temp;
    }
    static int dist(double x1,double y1,double x2,double y2){
        Point2D point1 = new Point2D.Double(x1, y1);
        Point2D point2 = new Point2D.Double(x2, y2);
        int distance = (int)point1.distance(point2);
        return distance;
    }
}

class House extends JPanel implements ActionListener{
    int x;
    int y;
    int loc_x,loc_y;
    JLabel name_label; 
    JButton button;
    int id;
    Font font_1;
    int mouseX,mouseY;
    String name,subst_name;
    boolean is_conn=false;


    House(String lab,int id) {
     
        ImageIcon icon = new ImageIcon("house_1.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(25, 25, Image.SCALE_FAST);
        icon = new ImageIcon(newImage);
        this.name_label=new JLabel(icon);
        
        this.button=new JButton(lab);
        //button.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD,15));
        button.setBorder(null);
        this.id=id;
        this.name=lab;
        
        
        this.setLayout(new BorderLayout());
        this.setSize(40,40);
        this.add(button,BorderLayout.SOUTH);
        this.add(name_label,BorderLayout.NORTH);
        button.setBackground(null);
        this.setBackground(null);
        button.addActionListener(this);

        this.setBorder(new EtchedBorder());
        this.loc_x=this.getX();
        this.loc_y=this.getY();
        this.setVisible(true);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                x += e.getX() - mouseX;
                y += e.getY() - mouseY;
                setLocation(x, y);
                if(x+20<0 || y+20<0){
                    delete();
                }
                
                P.print(x+" "+y);
            }
        });
    }
    House(String lab,int id,int dx,int dy) {
        setBounds(dx,dy,40,40);
        ImageIcon icon = new ImageIcon("house_1.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(25, 25, Image.SCALE_FAST);
        icon = new ImageIcon(newImage);
        this.name_label=new JLabel(icon);
        
        this.button=new JButton(lab);
        //button.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD,15));
        button.setBorder(null);
        this.id=id;
        this.name=lab;
        
        
        this.setLayout(new BorderLayout());
        this.setSize(40,40);
        this.add(button,BorderLayout.SOUTH);
        this.add(name_label,BorderLayout.NORTH);
        button.setBackground(null);
        this.setBackground(null);
        button.addActionListener(this);

        this.setBorder(new EtchedBorder());
        this.loc_x=this.getX();
        this.loc_y=this.getY();
        this.setVisible(true);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                x += e.getX() - mouseX;
                y += e.getY() - mouseY;
                setLocation(x, y);
                if(x+20<0 || y+20<0){
                    delete();
                }
                
                P.print(x+" "+y);
            }
        });
        


    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==this.button)
        {
            disp_details(start.side);
        }

    }
    public void disp_details(JPanel side){

        start.side_info=new JPanel();
        start.side_info.setBounds(20,390,150,100);
        JLabel subs_name,house_name,loc;
        subs_name =new JLabel("Substation : "+subst_name);
        house_name=new JLabel("Name       : "+name);
        loc=       new JLabel("Location   : ("+this.getX()+","+this.getY()+")");

        subs_name.setBounds(5,5,150,20);
        house_name.setBounds(5,30,150,20);
        loc.setBounds(5,55,150,20);
        start.side_info.add(subs_name);
        start.side_info.add(house_name);
        start.side_info.add(loc);
        side.add(start.side_info);
        start.side_info.setLayout(null);
        start.side_info.setVisible(true);
        start.side_info.setBackground(Color.white);
        side.setLayout(null);
        side.setVisible(true);
    }
    public void delete(){
        start.houses.remove(this);
        start.mid.remove(this);
    }

}
class SubStat extends JPanel implements ActionListener{
    int x;
    int y;
    int loc_x,loc_y;
    JLabel name_label; 
    JButton button;
    int id;
    Font font_1;
    int mouseX,mouseY;
    String name;
    ArrayList<House> conn_houses = new ArrayList<House>();


    SubStat(String lab,int id) {

        ImageIcon icon = new ImageIcon("subs.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(25, 25, Image.SCALE_FAST);
        icon = new ImageIcon(newImage);
        this.name_label=new JLabel(icon);
        
        this.button=new JButton(lab);
        //button.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD,15));
        button.setBorder(null);
        this.id=id;
        this.name=lab;
        
        
        this.setLayout(new BorderLayout());
        this.setSize(40,40);
        this.add(button,BorderLayout.SOUTH);
        this.add(name_label,BorderLayout.NORTH);
        button.setBackground(null);
        this.setBackground(null);
        button.addActionListener(this);

        this.setBorder(new EtchedBorder());
        this.loc_x=this.getX();
        this.loc_y=this.getY();
        this.setVisible(true);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                x += e.getX() - mouseX;
                y += e.getY() - mouseY;
                setLocation(x, y);
                if(x+20<0 || y+20<0){
                    delete();
                }
                
                //P.print(x+" "+y);
            }
        });
        


    }
    SubStat(String lab,int id,int dx,int dy) {
        setBounds(dx,dy,40,40);
        ImageIcon icon = new ImageIcon("subs.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(25, 25, Image.SCALE_FAST);
        icon = new ImageIcon(newImage);
        this.name_label=new JLabel(icon);
        
        this.button=new JButton(lab);
        //button.setFont(new Font(Font.DIALOG_INPUT,  Font.BOLD,15));
        button.setBorder(null);
        this.id=id;
        this.name=lab;
        
        
        this.setLayout(new BorderLayout());
        this.setSize(40,40);
        this.add(button,BorderLayout.SOUTH);
        this.add(name_label,BorderLayout.NORTH);
        button.setBackground(null);
        this.setBackground(null);
        button.addActionListener(this);

        this.setBorder(new EtchedBorder());
        this.loc_x=this.getX();
        this.loc_y=this.getY();
        this.setVisible(true);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                x += e.getX() - mouseX;
                y += e.getY() - mouseY;
                setLocation(x, y);
                if(x+20<0 || y+20<0){
                    delete();
                }
                
                //P.print(x+" "+y);
            }
        });
        


    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==this.button)
        {
            disp_details(start.side);
        }

    }
    public void disp_details(JPanel side){

        start.side_info=new JPanel();
        start.side_info.setBounds(20,390,150,120);
        JLabel house_name,loc;
        house_name=new JLabel("Name      : "+name);
        loc       =new JLabel("Location  : ("+this.getX()+","+this.getY()+")");

        house_name.setBounds(5,5,150,20);
        loc.setBounds(5,30,150,20);
    
        start.side_info.add(house_name);
        start.side_info.add(loc);
        side.add(start.side_info);
        start.side_info.setLayout(null);
        start.side_info.setVisible(true);
        start.side_info.setBackground(Color.white);
        side.setLayout(null);
        side.setVisible(true);
    }
    public void delete(){
        start.subs.remove(this);
        start.mid.remove(this);
    }

}
class mid_panel extends JPanel{
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.gray);
        Graphics2D g2d =  (Graphics2D)g;
        g2d.setStroke(new BasicStroke(0.1f));
        
        for(int i=0;i<=600;i+=20){
            g2d.drawLine(0, i, 600, i);
            g2d.drawLine(i, 0, i, 600);
        

        }    
    }
}
class line{
    int x1,x2,y1,y2;
    int dist;
    String name1,name2;
    SubStat s1;
    House h1,h2;
    boolean is_draw=false;

    line(int x1,int y1,int x2,int y2,SubStat s1,House h2){
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        this.s1=s1;
        this.h2=h2;
        int xDistance = x2 - x1;
        int yDistance = y2 - y1;
        this.dist= (int)Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }
    line(int x1,int y1,int x2,int y2,House h1,House h2){
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        this.h1=h1;
        this.h2=h2;
        int xDistance = x2 - x1;
        int yDistance = y2 - y1;
        this.dist= (int)Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));

    }
    public void draw(JPanel mid){
        Graphics g = mid.getGraphics();
        //g.drawLine(10, 10, 50, 50);
        g.setColor(Color.blue);
        Graphics2D g2d =  (Graphics2D)g;
        //g2d.setXORMode(true);
        g2d.setStroke(new BasicStroke(3));
        if(h2.is_conn==false){
            g2d.drawLine(x1, y1, x2, y2);
            start.total_cost+=dist;
            if(s1==null){
                h1.is_conn=true;
                h2.is_conn=true;
                P.print(h1.name+"--"+h2.name+" dist -> "+dist);
            }
            else{
                //s1.conn_houses.add(conn_houses.size()+1,h2);
                h2.is_conn=true;
                P.print(s1.name+"--"+h2.name+" dist -> "+dist);
                //P.print(s1.name+" "+h2.name+" is_conn"+h2.is_conn);
            }
            int mid_x,mid_y;

            mid_x =(x1+x2)/2;
            mid_y =(y1+y2)/2;
            JLabel temp=new JLabel(""+dist);
            temp.setBounds(mid_x,mid_y,5,5);
            mid.add(temp);
            mid.setVisible(true);
            mid.setLayout(null);
        }   

    }
}
class start implements  ActionListener{
    JButton new_house,new_subs,write,connect_1,connect,load;
    JFrame main_window;
    static JPanel side,side_info;
    static mid_panel mid;
    static int total_cost=0;
    JLabel total_c;
   
    static ArrayList<House> houses =new ArrayList<House>();
    static ArrayList<SubStat> subs =new ArrayList<SubStat>();
    int i=0,j=0;
    int hn=0,sn=0;

    start(){
        main_window=new JFrame("Main Window");
        side = new JPanel();
        mid = new mid_panel();

        new_house =new JButton("New house");
        new_house.addActionListener(this);
        new_house.setBounds(20,10,150,40);
        new_house.setBackground(Color.white);

        new_subs =new JButton("New Substation");
        new_subs.addActionListener(this);
        new_subs.setBounds(20,70,150,40);
        new_subs.setBackground(Color.white);

        connect =new JButton("Connect");
        connect.addActionListener(this);
        connect.setBounds(20,130,150,40);
        connect.setBackground(Color.white);

        connect_1 =new JButton("Update");
        connect_1.addActionListener(this);
        connect_1.setBounds(20,210,150,40);
        connect_1.setBackground(Color.white);

        write = new JButton("Record Data");
        write.addActionListener(this);
        write.setBounds(20,270,150,40);
        write.setBackground(Color.white);

        load = new JButton("Load");
        load.addActionListener(this);
        load.setBounds(20,330,150,40);
        load.setBackground(Color.white);


        total_c =new JLabel("Total Cost : --");     

        side.add(new_house);
        side.add(new_subs);
        side.add(write);
        side.add(connect);
        side.add(connect_1);
        side.add(load);
        total_c.setBounds(0,540,100,20);
        side.add(total_c);
        total_c.setBackground(Color.white);
        mid.setLayout(null);
        mid.setVisible(true);

        side.setBounds(0,0,200,600);
        mid.setBounds(200,0,600,600);
        mid.setBackground(Color.white);
        side.setBackground(Color.gray);
        //House obj=new House("H1");
        //mid.add(obj);
        
        main_window.add(side);
        main_window.add(mid);
        
        
        side.setLayout(null);
        side.setVisible(true);

        main_window.setLayout(null);  
        main_window.setResizable(false);
        main_window.setVisible(true); 
        main_window.setSize(800,600);
        main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // main_window.getContentPane().setBackground(Color.white);

    }
    public void actionPerformed(ActionEvent e)
    {
        try{
            if(e.getSource()==this.new_house)
            {
                create_house();
            }
            if(e.getSource()==this.new_subs){
                create_subs();
            }
            if(e.getSource()==this.write)
            {
                record_data(0);
            }
            if(e.getSource()==connect_1){
                update();
                //mid.repaint();
            }
            if(e.getSource()==connect){
                //record_data(-1);
                //load_from_file(-1);
                refresh();
                get_min();
            }
            if(e.getSource()==load){
                load_from_file(0);
            }

        }
        catch(Exception er){
            P.print(er+" ");
        }
        

    }
    public void refresh(){
        for(House temp:houses){
            temp.is_conn=false;
        }
    }
    
    public void create_house(){
        
        i=houses.size();
        houses.add(i,new House("H"+i,i));
        mid.add(houses.get(i));
        hn++;
        mid.setLayout(null);
        mid.setVisible(true);
        main_window.setLayout(null);  
        main_window.setVisible(true);

    }
    public void create_subs(){
        
        j=subs.size();
        subs.add(j,new SubStat("SbSt"+j,j));
        mid.add(subs.get(j));
        sn++;
        mid.setLayout(null);
        mid.setVisible(true);
        main_window.setLayout(null);  
        main_window.setVisible(true);
    }
    
    public void record_data(int n) throws Exception{
        File fobj=null;
        if(n==-1){
            fobj =new File("temp_log.txt");
        }
        else{
            fobj= new File("log.txt");
        }
        if(fobj.exists()==false){
            fobj.createNewFile();
        }
        FileWriter fout = new FileWriter(fobj);
        fout.write("");
        //House temp=null;
        for(House temp:houses){
            fout.write(temp.name+" "+temp.getX()+" "+temp.getY()+"\n");
            P.print(temp.name+" "+temp.getY()+" "+temp.getY()+"\n");
        }
        for(SubStat temp1:subs){
            fout.write(temp1.name+" "+temp1.getX()+" "+temp1.getY()+"\n");
            P.print(temp1.name+" "+temp1.getX()+" "+temp1.getY()+"\n");
        }
        fout.close();


    }
    public boolean all_connected(ArrayList<House> houses){
        for(House temp:houses){
            //P.print(temp.name +" "+temp.is_conn);
            if(temp.is_conn==false){

                return false;
            }
        }
        return true;
    }
    public void update(){
        ArrayList<line> temp =new ArrayList<line>();
        for(House temp1:houses){
            if(temp1.is_conn==false){
                for(House temp2: houses){
                    temp.add(temp.size(),new line(temp1.getX()+20,temp1.getY()+20,temp2.getX()+20,temp2.getY()+20,temp2,temp1));
                }
                for(SubStat temp3: subs){
                    temp.add(temp.size(),new line(temp1.getX()+20,temp1.getY()+20,temp3.getX()+20,temp3.getY()+20,temp3,temp1));
                }
                line min=temp.get(0);
                for(line temp4 : temp){
                    if(temp4.dist<min.dist && temp4.dist!=0){
                        min=temp4;
                    }
                }
                min.draw(mid);
                total_c.setText("Total cost :"+start.total_cost);
                if (min.s1==null){
                    temp1.subst_name=min.h1.name;
                }
                else{
                    temp1.subst_name=min.s1.name;
                }
                temp.clear();
            }
        }
        //g2d.drawLine(10, 10, 50, 50);
    }
    public void load_from_file(int n) throws Exception{
        mid.removeAll();
        mid.repaint();
        start.total_cost=0;
        File fobj=null;
        if(n==-1){
            fobj =new File("temp_log.txt");
        }
        else{
            fobj= new File("log.txt");
        }

       
        Scanner fin = new Scanner(fobj);
        houses.clear();
        subs.clear();
        int x,y,i;
        P.print("------------------------------------------");
        while(fin.hasNextLine()){

            String arr[]=fin.nextLine().split(" ",3);
            P.print(arr);
           
            if(arr[0].startsWith("H")){
                i=houses.size();
                x=Integer.parseInt(arr[1]);
                y=Integer.parseInt(arr[2]);
                houses.add(i,new House(arr[0],i,x,y));
                mid.add(houses.get(i));
            }
            else{
                i=subs.size();
                x=Integer.parseInt(arr[1]);
                y=Integer.parseInt(arr[2]);
                subs.add(i,new SubStat(arr[0],i,x,y));
                mid.add(subs.get(i));
            }
        }
        
        mid.setLayout(null);
        mid.setVisible(true);
        main_window.setLayout(null);  
        main_window.setVisible(true);

    }
    public void get_min() throws Exception{
       
        /*record_data();
        File fobj =new File("log.txt");
        Scanner fout =new Scanner(fobj);
        */
        

        start.total_cost=0;
        int hlen= houses.size();
        int slen=subs.size();
        ArrayList<line> temp =new ArrayList<line>();
        int x1,x2,y1,y2,min_dist,dist;
        line temp_line;
        SubStat temp3=null;
        House cur_house;
        for(House temp1:houses){
            x1=temp1.getX()+20;
            y1=temp1.getY()+20;
            min_dist=10000;
            for(SubStat temp2:subs){
                x2=temp2.getX()+20;
                y2=temp2.getY()+20;
                dist=P.dist(x1,y1,x2,y2);
                P.print(temp1.name+" "+temp2.name+" "+dist);
                if(dist<min_dist && dist<=150){
                    min_dist=dist;
                    temp3=temp2;

                }
            }
            temp1.subst_name=temp3.name;
            temp3.conn_houses.add(temp3.conn_houses.size(),temp1);
        }
        for(SubStat temp4:subs){
            temp.clear();
            P.print("name-> "+temp4.name);
            x1=temp4.getX()+20;
            y1=temp4.getY()+20;
            for(House temp5 : temp4.conn_houses ){
                if(temp5.is_conn==false){
                    x2=temp5.getX()+20;
                    y2=temp5.getY()+20;
                    temp.add(temp.size(),new line(x1,y1,x2,y2,temp4,temp5));
                }
            }
            temp_line=temp.get(0);
            for(line temp_line1:temp){
                if(temp_line1.dist<temp_line.dist){
                    temp_line=temp_line1;
                }
            }
            temp_line.draw(mid);

            cur_house=temp_line.h2;
            P.print("stat => "+temp4.name+"\n"+"house => "+cur_house.name+"\n----------");
            temp.remove(temp.indexOf(temp_line));
            while(all_connected(temp4.conn_houses)==false){
                x1=cur_house.getX()+20;
                y1=cur_house.getY()+20;
                for(House temp6 : temp4.conn_houses){
                    if(temp6.is_conn==false){
                        x2=temp6.getX()+20;
                        y2=temp6.getY()+20;
                        temp.add(temp.size(),new line(x1,y1,x2,y2,cur_house,temp6));
                    }
                }
                temp_line=temp.get(0);
                for(line temp_line1:temp){
                    if(temp_line1.dist<temp_line.dist){
                        temp_line=temp_line1;
                    }
                }
                temp_line.draw(mid);
                cur_house=temp_line.h2;
                temp.remove(temp.indexOf(temp_line));

            }
        }
        
        total_c.setText("Total cost :"+start.total_cost);
        update();
        
        mid.setLayout(null);
        mid.setVisible(true);
        
        
        

    }

}
public class scratch_01{
    public static void main(String[] args) {
        start obj= new start();
        
    }
}