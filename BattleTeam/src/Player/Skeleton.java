package Player;

import java.util.Random;

public class Skeleton extends Player {
    public Skeleton() {
        super("Skeleton", 100, 15, "활");  // HP 100, 공격력 15
    }

    @Override 
    public void attack(Player target) {
        if (damageReceivedCount >= 3) {  // 피해를 3번 입을 때마다 공격력 증가
            Random rand = new Random();
            if (rand.nextInt(100) < 20) {  // 20% 확률로 공격력 증가
                this.setPower(this.getPower() * 3 / 2);  // 공격력 1.5배 증가
                System.out.println("🏹 스켈레톤의 공격력 증가! 공격력이 1.5배가 되었습니다.");
            }
            damageReceivedCount = 0;  // 피해 횟수 초기화
        }

        super.attack(target);  // 기본 공격
    }
    
    @Override
    public String getImagePath() {
        return "/image/Skeleton.png";
    }
}