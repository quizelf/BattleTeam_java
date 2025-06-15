package Weapon;

public class Bow extends Weapon {
    public Bow() {
        super("활", 15, 10);  // 공격력 15, 내구도 10
    }

    @Override
    public void attackMotion() {
        System.out.println("활로 쏘았다!");
    } 
}