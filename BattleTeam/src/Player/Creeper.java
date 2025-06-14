package Player;

public class Creeper extends Player {
    public Creeper() {
        super("Creeper", 100, 5, "폭탄");  // HP 75, 공격력 5
    }

    @Override
    public void attack(Player target) {
        // HP가 50% 이하일 경우 자폭
        if (this.getHp() <= this.getHp() / 2) {
            this.setHp(0);  // 자신도 HP 0으로 설정
            target.setHp(0);  // 상대방도 HP 0으로 설정
            System.out.println("💥 크리퍼 자폭!");
        } else {
            super.attack(target);  // 기본 공격
        }
    }  
    
    @Override
    public String getImagePath() {
        return "/image/Creeper.png";
    }
}