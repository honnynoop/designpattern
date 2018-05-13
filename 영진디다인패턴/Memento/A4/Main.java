import game.Memento;
import game.Gamer;
import java.io.*;

public class Main {
    public static final String SAVEFILENAME = "game.dat";       
    public static void main(String[] args) {
        Gamer gamer = new Gamer(100);               // 최초의 소지금은 100
        Memento memento = loadMemento();          // 파일에서 로드
        if (memento != null) {
            System.out.println("지난번 저장한 결과에서 시작합니다.");
            gamer.restoreMemento(memento);
        } else {
            System.out.println("새로 시작합니다.");
            memento = gamer.createMemento();
        }
        for (int i = 0; i < 100; i++) {
            System.out.println("==== " + i);          // 횟수표시
            System.out.println("현 상태:" + gamer);    // 현재 주인공의 상태

            gamer.bet();                             // 게임을 진행한다

            System.out.println("소지금은" + gamer.getMoney() + "원이 되었습니다.");

            // Memento 취급의 결정
            if (gamer.getMoney() > memento.getMoney()) {
                System.out.println("    (많이 증가했으므로 현재의 상태를 저장하자.) ");
                memento = gamer.createMemento();
                saveMemento(memento);   // 파일로 저장
            } else if (gamer.getMoney() < memento.getMoney() / 2) {
                System.out.println("    (많이 감소했으므로 이전의 상태로 복원하자.) ");
                gamer.restoreMemento(memento);
            }

            // 시간 기다림
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("");
        }
    }
    public static void saveMemento(Memento memento) {   
        try {
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(SAVEFILENAME));
            out.writeObject(memento);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Memento loadMemento() {               
        Memento memento = null;
        try {
            ObjectInput in = new ObjectInputStream(new FileInputStream(SAVEFILENAME));
            memento = (Memento)in.readObject();
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return memento;
    }
}
