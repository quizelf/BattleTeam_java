package Weapon;

public class Bomb extends Weapon {
    public Bomb() {
        super("폭탄", 30, 10);  // 공격력 30, 내구도 10
    } 

    @Override
    public void attackMotion() {
        System.out.println("폭탄을 던졌다!");
    }
}