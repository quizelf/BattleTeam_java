package Main;

import java.util.Random;

import javax.swing.SwingUtilities;

import Player.*;
import Weapon.*;
import Player.Player;
import Main.BattleCraft;

public class Main { 
    public static void main(String[] args) {
        // 플레이어 객체 생성
        Steve steve = new Steve();
        // 무기 객체 생성
        Weapon[] weapons = { new Sword(), new Bow(), new Bomb(), new Poison() };
        // 몬스터 배열 생성 (랜덤 순서)
        Player[] monsters = { new Zombie(), new Skeleton(), new Creeper() };
        shuffle(monsters); // 랜덤 순서
        // 몬스터와 순차적으로 전투
        for (Player monster : monsters) {
            System.out.println("새로운 적 등장: " + monster.getName());

            // 무작위로 무기 선택
            Random rand = new Random();
            Weapon steveWeapon = weapons[rand.nextInt(weapons.length)];
            Weapon monsterWeapon = weapons[rand.nextInt(weapons.length)];

            steve.setWeapon(steveWeapon);
            monster.setWeapon(monsterWeapon);

            System.out.println("스티브는 " + steveWeapon.getName() + "을(를) 선택!");
            System.out.println( monster.getName() + "는(은) " + monsterWeapon.getName() + "을(를) 들고 등장!");
            // 전투 시작
            
            windowGame(steve, monster);
            while (steve.getHp() > 0 && monster.getHp() > 0) {
                steve.attack(monster);
                if (monster.getHp() <= 0) break;
                monster.attack(steve);
            }
            if (steve.getHp() <= 0) {
                System.out.println("스티브가 쓰러졌습니다. 게임 오버!");
                return;
            } else {
                System.out.println( monster.getName() + "을(를) 쓰러뜨렸습니다!\n");
            }
        }
        System.out.println(" 모든 몬스터를 물리쳤습니다. 게임 승리!");
    }
    // 몬스터 랜덤 순서 섞기
    private static void shuffle(Player[] array) {
        Random r = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            Player temp = array[i];
            array[i] = array[j];
            array[j] = temp;  
        }
    }
    private static void windowGame(Player p1, Player p2) {
		// TODO Auto-generated method stub
    	SwingUtilities.invokeLater(() -> {
            new BattleCraft().setVisible(true);
        });

	}
}
