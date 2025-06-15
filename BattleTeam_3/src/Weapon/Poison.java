package Weapon;

public class Poison extends Weapon {
    public Poison() {
        super("독", 5, 10);  // 공격력 5, 내구도 10
    }

    @Override
    public void attackMotion() {
        System.out.println("독을 쏘았다!");
    } 
}