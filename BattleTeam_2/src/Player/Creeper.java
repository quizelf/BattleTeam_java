package Player;

public class Creeper extends Player {
    public Creeper() {
        super("Creeper", 100, 5, "폭탄");  // HP 75, 공격력 5
    }

    @Override
    public void attack(Player target) {
        // HP가 30이하일 경우 자폭
        if (this.getHp() <= 30) {
            this.setHp(this.getHp()-20);  // 자신도 HP에서 -20
            target.setHp(target.getHp()-20);  // 상대방도 HP에서 -20
            System.out.println("💥 크리퍼 자폭!");
            Main.BattleCraft.logMessage("💥 크리퍼 자폭!");
        } else {
            super.attack(target);  // 기본 공격
        }
    }  
    
    @Override
    public String getImagePath() {
        return "/image/Creeper.png";
    }
    
}