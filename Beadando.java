import java.util.*;
import java.io.*;


class Node
{
    public List<Node> children=new ArrayList<Node>();
    public Node parent;
    public int[][] Rejtveny = new int[3][3];
    public int Fcost;
    public int Gcost;
    public int Hcost;
    public int melyseg;

    public int getMelyseg() {
        return melyseg;
    }

    public Node(int [][] p)
    {
        setRejtveny(p);
    }

    public void setRejtveny(int[][] Rejtveny) {
        this.Rejtveny = Rejtveny;
    }
    public  int hamming(int[][] tomb) {

        int hamming = 0;
        int temp[] = new int[9];
        for (int i = 0; i < 9; i++) {
            temp[i] = tomb[i / 3][i % 3];
        }
        for (int i = 0; i <  9; i++) {
            if (temp[i] != i + 1) {
                hamming++;
            }
        }
        return hamming;
    }

    public  int zero(int[][] tomb) {
        int zero = 9;
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                if(tomb[0][0]==1)
                    zero--;
                if(tomb[0][1]==2)
                    zero--;
                if(tomb[0][2]==3)
                    zero--;
                if(tomb[1][0]==4)
                    zero--;
                if(tomb[1][1]==5)
                    zero--;
                if(tomb[1][2]==6)
                    zero--;
                if(tomb[2][0]==7)
                    zero--;
                if(tomb[2][1]==8)
                    zero--;
                if(tomb[2][2]==9)
                    zero--;
            }
        }
        return zero;
    }
    public  int manhattan(int[][] tomb) {
        int manhattan = 0;
        int[][] egyesek = {{0,1,1}, {1,2,2}, {1,2,2}};
        int[][] kettesek = {{1,0,1}, {2,1,2}, {2,1,2}};
        int[][] harmasok = {{1,1,0}, {2,2,1}, {2,2,1}};
        int[][] negyesek = {{1,2, 2}, {0, 1, 1}, {1,2,2}};
        int[][] otosok = {{2,1,2}, {1,0,1}, {2,1,2}};
        int[][] hatosok = {{2,2,1}, {1, 1, 0}, {2,2,1}};
        int[][] hetesek = {{1, 2, 2}, {1, 2, 2}, {0,1,1}};
        int[][] nyolcasok = {{2,1,2}, {2,1,2}, {1,0,1}};
        int[][] kilencesek = {{2, 2, 1}, {2, 2, 1}, {1,1,0}};
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                if(tomb[i][j]==1)
                {
                   manhattan+=egyesek[i][j];
                }
                if(tomb[i][j]==2)
                {
                    manhattan+=kettesek[i][j];
                }
                if(tomb[i][j]==3)
                {
                    manhattan+=harmasok[i][j];
                }
                if(tomb[i][j]==4)
                {
                    manhattan+=negyesek[i][j];
                }
                if(tomb[i][j]==5)
                {
                    manhattan+=otosok[i][j];
                }
                if(tomb[i][j]==6)
                {
                    manhattan+=hatosok[i][j];
                }
                if(tomb[i][j]==7)
                {
                    manhattan+=hetesek[i][j];
                }
                if(tomb[i][j]==8)
                {
                    manhattan+=nyolcasok[i][j];
                }
                if(tomb[i][j]==9)
                {
                    manhattan+=kilencesek[i][j];
                }
            }
        }
        return manhattan;
    }
    public  void Kiir()
    {
        System.out.println("   A   B   C  ");
        for(int i=0;i<3;i++)
        {
            System.out.print(i+1 + " ");
            for(int j=0;j<3;j++)
            {
                System.out.print("[" + Rejtveny[i][j]+ "] ");
            }
            System.out.print(i+4 + " ");
            System.out.println();
        }
        System.out.println("   D   E   F  ");

    }
    public void ExpandMove()
    {
            Mozgat(Rejtveny,'1');
            Mozgat(Rejtveny,'2');
            Mozgat(Rejtveny,'3');
            Mozgat(Rejtveny,'4');
            Mozgat(Rejtveny,'5');
            Mozgat(Rejtveny,'6');
            Mozgat(Rejtveny,'A');
            Mozgat(Rejtveny,'B');
            Mozgat(Rejtveny,'C');
            Mozgat(Rejtveny,'D');
            Mozgat(Rejtveny,'E');
            Mozgat(Rejtveny,'F');
    }
    public   void Mozgat(int[][] segedtomb , Character s)
    {
        int[] temp = new int[3];
        int [][] pc=new int[3][3];
        CopyRejtveny(pc,segedtomb);
        Node child ;
        switch(s)
        {
            case '1':
                for(int i = 0 ; i< 3 ; i++)
                {
                    temp[i] = segedtomb[0][i];
                }
                pc[0][0]= temp[1];
                pc[0][1]= temp[2];
                pc[0][2]= temp[0];
                child=new Node(pc);
                children.add(child);
                child.parent=this;
                child.melyseg=child.parent.melyseg+1;
                child.Gcost=zero(pc)+child.melyseg;
                child.Hcost=manhattan(pc);
                child.Fcost=child.Gcost+child.Hcost;
                break;
            case '2':
                for(int i = 0 ; i< 3 ; i++)
                {
                    temp[i] = segedtomb[1][i];
                }
                pc[1][0]= temp[1];
                pc[1][1]= temp[2];
                pc[1][2]= temp[0];
                child=new Node(pc);
                children.add(child);
                child.parent=this;
                child.melyseg=child.parent.melyseg+1;
                child.Gcost=zero(pc)+child.melyseg;
                child.Hcost=manhattan(pc);
                child.Fcost=child.Gcost+child.Hcost;
                break;
            case '3':
                for(int i = 0 ; i< 3 ; i++)
                {
                    temp[i] = segedtomb[2][i];
                }
                pc[2][0]= temp[1];
                pc[2][1]= temp[2];
                pc[2][2]= temp[0];
                child=new Node(pc);
                children.add(child);
                child.parent=this;
                child.melyseg=child.parent.melyseg+1;
                child.Gcost=zero(pc)+child.melyseg;
                child.Hcost=manhattan(pc);
                child.Fcost=child.Gcost+child.Hcost;
                break;
            case '4':
                for(int i = 0 ; i< 3 ; i++)
                {
                    temp[i] = segedtomb[0][i];
                }
                pc[0][0]= temp[2];
                pc[0][1]= temp[0];
                pc[0][2]= temp[1];
                child=new Node(pc);
                children.add(child);
                child.parent=this;
                child.melyseg=child.parent.melyseg+1;
                child.Gcost=zero(pc)+child.melyseg;
                child.Hcost=manhattan(pc);
                child.Fcost=child.Gcost+child.Hcost;
                break;
            case '5':
                for(int i = 0 ; i< 3 ; i++)
                {
                    temp[i] = segedtomb[1][i];
                }
                pc[1][0]= temp[2];
                pc[1][1]= temp[0];
                pc[1][2]= temp[1];
                child=new Node(pc);
                children.add(child);
                child.parent=this;
                child.melyseg=child.parent.melyseg+1;
                child.Gcost=zero(pc)+child.melyseg;
                child.Hcost=manhattan(pc);
                child.Fcost=child.Gcost+child.Hcost;
                break;
            case '6':
                for(int i = 0 ; i< 3 ; i++)
                {
                    temp[i] = segedtomb[2][i];
                }
                pc[2][0]= temp[2];
                pc[2][1]= temp[0];
                pc[2][2]= temp[1];
                child=new Node(pc);
                children.add(child);
                child.parent=this;
                child.melyseg=child.parent.melyseg+1;
                child.Gcost=zero(pc)+child.melyseg;
                child.Hcost=manhattan(pc);
                child.Fcost=child.Gcost+child.Hcost;
                break;
            case 'A':
                for(int i = 0 ; i< 3 ; i++)
                {
                    temp[i] = segedtomb[i][0];
                }
                pc[0][0]= temp[1];
                pc[1][0]= temp[2];
                pc[2][0]= temp[0];
                child=new Node(pc);
                children.add(child);
                child.parent=this;
                child.melyseg=child.parent.melyseg+1;
                child.Gcost=zero(pc)+child.melyseg;
                child.Hcost=manhattan(pc);
                child.Fcost=child.Gcost+child.Hcost;
                break;
            case 'B':
                for(int i = 0 ; i< 3 ; i++)
                {
                    temp[i] = segedtomb[i][1];
                }
                pc[0][1]= temp[1];
                pc[1][1]= temp[2];
                pc[2][1]= temp[0];
                child=new Node(pc);
                children.add(child);
                child.parent=this;
                child.melyseg=child.parent.melyseg+1;
                child.Gcost=zero(pc)+child.melyseg;
                child.Hcost=manhattan(pc);
                child.Fcost=child.Gcost+child.Hcost;
                break;
            case 'C':
                for(int i = 0 ; i< 3 ; i++)
                {
                    temp[i] = segedtomb[i][2];
                }
                pc[0][2]= temp[1];
                pc[1][2]= temp[2];
                pc[2][2]= temp[0];
                child=new Node(pc);
                children.add(child);
                child.parent=this;
                child.melyseg=child.parent.melyseg+1;
                child.Gcost=zero(pc)+child.melyseg;
                child.Hcost=manhattan(pc);
                child.Fcost=child.Gcost+child.Hcost;
                break;
            case 'D':
                for(int i = 0 ; i< 3 ; i++)
                {
                    temp[i] = segedtomb[i][0];
                }
                pc[0][0]= temp[2];
                pc[1][0]= temp[0];
                pc[2][0]= temp[1];
                child=new Node(pc);
                children.add(child);
                child.parent=this;
                child.melyseg=child.parent.melyseg+1;
                child.Gcost=zero(pc)+child.melyseg;
                child.Hcost=manhattan(pc);
                child.Fcost=child.Gcost+child.Hcost ;
                break;
            case 'E':
                for(int i = 0 ; i< 3 ; i++)
                {
                    temp[i] = segedtomb[i][1];
                }
                pc[0][1]= temp[2];
                pc[1][1]= temp[0];
                pc[2][1]= temp[1];
                child=new Node(pc);
                children.add(child);
                child.parent=this;
                child.melyseg=child.parent.melyseg+1;
                child.Gcost=zero(pc)+child.melyseg;
                child.Hcost=manhattan(pc);
                child.Fcost=child.Gcost+child.Hcost;
                break;
            case 'F':
                for(int i = 0 ; i< 3 ; i++)
                {
                    temp[i] = segedtomb[i][2];
                }
                pc[0][2]= temp[2];
                pc[1][2]= temp[0];
                pc[2][2]= temp[1];
                child=new Node(pc);
                children.add(child);
                child.parent=this;
                child.melyseg=child.parent.melyseg+1;
                child.Gcost=zero(pc)+child.melyseg;
                child.Hcost=manhattan(pc);
                child.Fcost=child.Gcost+child.Hcost;
                break;
        }
    }
    public boolean UgyanazaRejtveny(int[][] p)
    {
        boolean azonos=true;
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                if(Rejtveny[i][j]!=p[i][j])
                {
                    azonos=false;
                }
            }
        }
        return azonos;
    }
    public void CopyRejtveny(int[][] a,int[][] b)
    {
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                a[i][j]=b[i][j];
            }
        }
    }
    public boolean GoalTest()
    {
        if(hamming(Rejtveny)==0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
class Informaltkereses
{
    public  Informaltkereses()
    {

    }
    public  List<Node> Acsillagkereses(Node Gyoker)
    {
        List<Node> Utvonal=new ArrayList<Node>();
        List<Node> Nyiltlista =new ArrayList<Node>();
        List<Node> Zartlista=new ArrayList<Node>();

        Nyiltlista.add(Gyoker);
        Gyoker.Fcost=0;
        Gyoker.melyseg=0;
        boolean Goal=false;
        while(Nyiltlista.size()>0 && !Goal )
        {

            Node AktualisNode=Nyiltlista.get(0);
            for(int i=0;i<Nyiltlista.size();i++)
            {
                if(Nyiltlista.get(i).Fcost<AktualisNode.Fcost)
                {
                    AktualisNode=Nyiltlista.get(i);
                }
            }
            if(AktualisNode.GoalTest())
            {
                System.out.println("Megtaláltuk a célt");
                Goal=true;
                Utvonalkereses(Utvonal,AktualisNode);
                break;
            }

            Nyiltlista.remove(AktualisNode);
            Zartlista.add(AktualisNode);


            AktualisNode.ExpandMove();
            AktualisNode.Kiir();
       //    System.out.println("F: " + AktualisNode.Fcost + " G: " + AktualisNode.Gcost+ " " + "H: "+ AktualisNode.Hcost + " Melyseg:  " + AktualisNode.melyseg);

            for (int i = 0; i <AktualisNode.children.size() ; i++) {
                Node AktualisChild=AktualisNode.children.get(i);
                if(AktualisChild.GoalTest())
                {
                    System.out.println("Megtaláltuk a célt");
                    Goal=true;
                    Utvonalkereses(Utvonal,AktualisChild);
                }
                boolean megfelelo=true;
                for (int k = 0; k < Nyiltlista.size(); k++) {
                    if(Nyiltlista.get(k).melyseg==500)
                    {
                        return Utvonal;
                    }
                    if(Nyiltlista.get(k).melyseg==AktualisChild.melyseg)
                    {
                        if(Nyiltlista.get(k).Fcost<AktualisChild.Fcost)
                        {
                            megfelelo=false;
                            break;
                        }
                    }
                }
                for (int k = 0; k < Zartlista.size(); k++) {
                    if(Zartlista.get(k).melyseg==AktualisChild.melyseg)
                    {
                        if(Zartlista.get(k).Fcost<AktualisChild.Fcost)
                        {
                            megfelelo=false;
                            break;
                        }
                    }
                }
                if(megfelelo)
                {
                    if(!Contains(Nyiltlista,AktualisChild) && !Contains(Zartlista,AktualisChild))
                    {
                        Nyiltlista.add(AktualisChild);
                    }
                }
            }
        }
        return Utvonal;
    }
    public void Utvonalkereses(List<Node> path,Node n)
    {
        System.out.println("Útvonalkeresés...");
        Node Aktualis=n;
        path.add(Aktualis);
        while (Aktualis.parent !=null)
        {
            Aktualis=Aktualis.parent;
            path.add(Aktualis);
        }
    }
    public static boolean Contains(List<Node> list,Node c)
    {
        boolean contains=false;
        for (int i = 0; i <list.size(); i++) {
            if(list.get(i).UgyanazaRejtveny(c.Rejtveny))
            {
                contains=true;
            }
        }
        return contains;
    }

}
public class Beadando {


    public static boolean Bennevan(int segedtomb[][] ,int ertek)
    {
        for (int i = 0; i < segedtomb.length; i++) {
            for (int j = 0; j < segedtomb[i].length; j++) {
                if(segedtomb[i][j] == ertek)
                    return true;
            }
        }
        return false;
    }
    public static boolean Vege(int tomb[][])
    {
        int szamlalo = 1;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(tomb[i][j] != szamlalo)
                    return false;
                szamlalo++;
            }
        }
        return true;
    }
    public static void Kezdo(int[][] segedtomb)
    {
        int temp;
        Random rnd = new Random();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                do {
                    temp =  rnd.nextInt(9)+1;
                }while(Bennevan(segedtomb,temp));
                segedtomb[i][j] = temp;
            }
        }
    }
    public static void Kiir(int[][] segedtomb)
    {
        System.out.println("   A   B   C  ");
        for(int i=0;i<3;i++)
        {
            System.out.print(i+1 + " ");
            for(int j=0;j<3;j++)
            {
                System.out.print("[" + segedtomb[i][j]+ "] ");
            }
            System.out.print(i+4 + " ");
            System.out.println();
        }
        System.out.println("   D   E   F  ");

    }
    public static void main(String[] args) throws IOException{
        int [][] tomb=new int [3][3];
        int counter = 0;
        Kezdo(tomb);
        Kiir(tomb);
        Node Gyoker=new Node(tomb);
        Informaltkereses ui=new Informaltkereses();
        List<Node> solution=ui.Acsillagkereses(Gyoker);
            if(solution.size()>0)
            {
                for(int i=solution.size()-1;i>=0;i--)
                {
                    solution.get(i).Kiir();
                    counter++;
                }
            }
            else
            {
                System.out.println("Nem találtam megoldást");
            }
            System.out.println("Lépesek: " + counter);

    }
}
