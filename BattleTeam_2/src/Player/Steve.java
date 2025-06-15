package Player;

public class Steve extends Player {
    private boolean usedSleep = false;
    private boolean angelCardActive = false;
    private boolean usedAngelCard = false;

    public Steve() {
        super("Steve", 100, 20, "검");  // HP 150, 공격력 20
    }  
    

    public void useSleep() {
        if (!usedSleep) {
            this.setHp(Math.min(this.getHp() + 100, 250));  // HP 100 회복 (최대 HP는 250)
            usedSleep = true;
            System.out.println("💤 스티브가 침대에서 숙면! HP 100 회복");
        } else {
            System.out.println("❌ 이미 숙면을 사용했습니다.");
        }
    }

    public void useAngelCard() {
        if (!usedAngelCard) {
            usedAngelCard = true;
            angelCardActive = true;
            System.out.println("🛡️ 천사카드 발동! 다음 공격 무효화!");
        } else {
            System.out.println("❌ 이미 천사카드를 사용했습니다.");
        }
    }
    
    public boolean isAngelCardActive() {
        return angelCardActive;
    }

    public void deactivateAngelCard() {
        angelCardActive = false;
    }
}
