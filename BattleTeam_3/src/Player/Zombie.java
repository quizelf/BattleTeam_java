package Player;

public class Zombie extends Player {
    public Zombie() {
        super("Zombie", 100, 10, "독");  // HP 100, 공격력 10
    }  

    @Override
    public void attack(Player target) {
        super.attack(target);  // 기본 공격

        // 좀비는 매 3번 공격받을 때마다 HP 회복
        if (damageReceivedCount % 3 == 0) {
            this.setHp(Math.min(this.getHp() + 5, 100));  // HP가 100을 넘지 않도록
            System.out.println("🧟‍♂️ 좀비가 5HP 회복!");
            Main.BattleCraft.logMessage("🧟 좀비의 재생 능력! HP +5 회복");
        }
    }
    
    // Zombie.java
    @Override
    public String getImagePath() {
        return "/image/Zombie.png";
    }
    
}