package Weapon;

public abstract class Weapon {
    private String name;
    private int power;
    private int durability;  // 내구도

    public Weapon(String name, int power, int durability) {
        this.name = name;
        this.power = power;
        this.durability = durability;
    } 

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public int getDurability() {
        return durability;
    }

    public void use() {
        if (durability > 0) {
            durability--;  // 사용 시 내구도 1 감소
        }
    }

    

    public void attackMotion() {
        // 기본 공격 문구
    }
}