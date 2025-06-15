package Weapon;

public class Sword extends Weapon {
    public Sword() {
        super("검", 20, 10);  // 공격력 20, 내구도 10
    }

    @Override
    public void attackMotion() {
        System.out.println("검으로 베었다!");
    } 
}