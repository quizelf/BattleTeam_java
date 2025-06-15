package Player;

import Weapon.Weapon;
import javax.swing.*;

public class Player {
    private String name;
    private int hp;
    private int power;
    private Weapon weapon;
    protected int damageReceivedCount = 0;
    protected String 전용무기;
    private JProgressBar hpBar;

    
    public String getImagePath() {
        return "/image/default.png"; // 기본값
    }
    
    public Player(String name, int hp, int power, String 전용무기) {
        this.name = name;
        this.hp = hp;
        this.power = power;
        this.전용무기 = 전용무기;
        
        hpBar = new JProgressBar(0, hp);
        hpBar.setValue(hp);
        hpBar.setStringPainted(true);
    } 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
        updateHpBar();
    }

    private void updateHpBar() {
		// TODO Auto-generated method stub
		hpBar.setValue(hp);
	}

	public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void attack(Player target) {
        if (weapon != null) {
            // 전용 무기일 때 (attackPower * 2)
            if (weapon.getName().equals(this.getDefaultWeaponName())) {
                target.setHp(Math.max(0, target.getHp() - (this.power * 2)));
                System.out.println("전용 무기로 공격! 공격력 * 2");
                Main.BattleCraft.logMessage("전용 무기로 공격! 공격력 * 2");
            } else {
                target.setHp(Math.max(0, target.getHp() - (this.power + weapon.getPower())));
                weapon.use();  // 내구도 감소
                System.out.println(weapon.getName() + " 내구도: " + weapon.getDurability());
                System.out.println("무기로 공격! " + weapon.getName() + " 공격력: " + (this.power + weapon.getPower()));
            }

            // 내구도 체크
            if (weapon.getDurability() == 0) {
                System.out.println(weapon.getName() + "의 내구도가 0이 되어 사용 불가");
                
            }
            weapon.attackMotion();  // 공격 모션 실행
        } else {
            target.setHp(Math.max(0, target.getHp() - this.power));
            System.out.println("일반 공격을 했다!");
        }
        
        showStatus();
        System.out.println(target.name + ": " + target.hp + " HP, " + target.power + " Power");

        target.receiveDamage();  // 피해를 받은 횟수 증가
    }

    public void receiveDamage() {
        damageReceivedCount++;
    }

    public void showStatus() {
        System.out.println(this.name + ": " + this.hp + " HP, " + this.power + " Power");
    }

    public String getDefaultWeaponName() {
        return 전용무기;
    } 
}
