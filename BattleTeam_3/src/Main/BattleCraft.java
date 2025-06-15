package Main;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;
import Player.*;
import Weapon.*;
import Main.BattleCraft;
import javax.swing.JTextArea;

public class BattleCraft extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Steve steve;
    private Player[] monsters;
    private Player currentMonster;
    private int monsterIndex = 0;  
    private Weapon[] weapons;
    private JLabel lblSteveHp;
    private JLabel lblMonsterHp;
    private JLabel lblMonsterName;
    private JLabel lblWeapon;

    private JProgressBar barSteveHp;
    private JProgressBar barMonsterHp;
    private JScrollPane scrollPane;

    private JButton btnSword;
    private JButton btnBow;
    private JButton btnBomb;
    private JButton btnPoison;
    private JButton btnSleep;
    private JButton btnAngelCard;
    private boolean isSteveTurn = true;  // 스티브의 턴을 체크하는 변수
    private JLabel lblSteveName;
    private JLabel lblCreeperName;
    private static JTextArea logArea;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BattleCraft frame = new BattleCraft();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // 
    public static void logMessage(String message) {
        if (logArea != null) {
            logArea.setText(logArea.getText() + message + "\n");
        }
    }
    
    public BattleCraft() {
        // 기본 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1094, 709);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        //컴포넌트 초기화 코드
        
        lblSteveHp = new JLabel("HP");
        lblSteveHp.setBounds(95, 110, 50, 15);
        contentPane.add(lblSteveHp);

        lblWeapon = new JLabel("무기 ");
        lblWeapon.setBounds(12, 497, 50, 15);
        contentPane.add(lblWeapon);

        lblMonsterName = new JLabel("몬스터");
        lblMonsterName.setBounds(782, 591, 50, 15);
        contentPane.add(lblMonsterName);

        lblMonsterHp = new JLabel("HP");
        lblMonsterHp.setBounds(708, 50, 166, 132);
        contentPane.add(lblMonsterHp);

        barSteveHp = new JProgressBar();
        barSteveHp.setBounds(133, 111, 146, 14);
        contentPane.add(barSteveHp);

        barMonsterHp = new JProgressBar();
        barMonsterHp.setBounds(746, 110, 146, 14);
        contentPane.add(barMonsterHp);

        JLabel lblNewLabel_2_1 = new JLabel("능력");
        lblNewLabel_2_1.setBounds(12, 591, 50, 15);
        contentPane.add(lblNewLabel_2_1);

        btnSword = new JButton("검");
        btnSword.setBounds(45, 514, 110, 23);
        btnSword.addActionListener(e -> { steve.setWeapon(new Sword()); steveAttack(); });
        contentPane.add(btnSword);

        btnBow = new JButton("활");
        btnBow.setBounds(169, 514, 110, 23);
        btnBow.addActionListener(e -> { steve.setWeapon(new Bow()); steveAttack(); });
        contentPane.add(btnBow);

        btnBomb = new JButton("폭탄");
        btnBomb.setBounds(45, 558, 110, 23);
        btnBomb.addActionListener(e -> { steve.setWeapon(new Bomb()); steveAttack(); });
        contentPane.add(btnBomb);

        btnPoison = new JButton("독");
        btnPoison.setBounds(169, 558, 110, 23);
        btnPoison.addActionListener(e -> { steve.setWeapon(new Poison()); steveAttack(); });
        contentPane.add(btnPoison);

        btnSleep = new JButton("침대에서 숙면");
        btnSleep.setBounds(45, 603, 110, 23);
        btnSleep.addActionListener(e -> {
            steve.useSleep();
            updateStatusLabels();
            logArea.setText(logArea.getText() + "💤 스티브가 숙면을 취해 HP 회복!\n");
            btnSleep.setEnabled(false); // 한 번만 사용 가능
        });
        contentPane.add(btnSleep);

        btnAngelCard = new JButton("천사카드");
        btnAngelCard.setBounds(169, 603, 110, 23);
        btnAngelCard.addActionListener(e -> {
            steve.useAngelCard();
            logArea.setText(logArea.getText() + "🛡️ 천사카드 사용! 다음 공격 무효화!\n");
            btnAngelCard.setEnabled(false); // 한 번만 사용 가능
        });
        contentPane.add(btnAngelCard);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(291, 110, 368, 323);
        contentPane.add(scrollPane);
        
        logArea = new JTextArea();
        
        logArea.setLineWrap(true);           // 자동 줄바꿈 (길면 줄바꿈)
        logArea.setEditable(false);          // 사용자 편집 막기
        scrollPane.setViewportView(logArea); 
        
        
        lblSteveName = new JLabel("");
        lblSteveName.setBounds(45, 135, 211, 341);
        lblSteveName.setIcon(new ImageIcon(BattleCraft.class.getResource("/image/Steveimage.jpg")));
        ImageIcon d = new ImageIcon(BattleCraft.class.getResource("/image/Steveimage.jpg"));
		Image e = d.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);// a를 Image로 변환하여 스케일 조정
		ImageIcon f = new ImageIcon(e); // 스케일이 조정된 이미지를 다시 Icon으로 변환
        contentPane.add(lblSteveName);
        
        lblSteveName.setIcon(f);
        contentPane.add(lblSteveName);
        
        lblCreeperName = new JLabel("");
        lblCreeperName.setBounds(697, 135, 262, 377);
        lblCreeperName.setIcon(new ImageIcon(BattleCraft.class.getResource("/image/Creeper.png")));
        ImageIcon d1 = new ImageIcon(BattleCraft.class.getResource("/image/Creeper.png"));
		Image e1 = d1.getImage().getScaledInstance(200, 350, Image.SCALE_SMOOTH);// a를 Image로 변환하여 스케일 조정
		ImageIcon f1 = new ImageIcon(e1);
		lblCreeperName.setIcon(f1);

        contentPane.add(lblCreeperName);
        

        // 게임 로직 초기화
        steve = new Steve();
        monsters = new Player[] { new Zombie(), new Skeleton(), new Creeper() };
        Arrays.sort(monsters, (a, b) -> Integer.compare(a.getHp(), b.getHp()));  // HP 낮은 순으로 정렬 
        weapons = new Weapon[] { new Sword(), new Bow(), new Bomb(), new Poison() };

        loadNextMonster();
        updateStatusLabels();
    }

    private void loadNextMonster() {
        if (monsterIndex < monsters.length) { 
            currentMonster = monsters[monsterIndex++];
            lblMonsterName.setText(currentMonster.getName());
            lblMonsterHp.setText("HP: " + currentMonster.getHp()); 
            barMonsterHp.setMaximum(currentMonster.getHp());
            barMonsterHp.setValue(currentMonster.getHp());

            Weapon monsterWeapon = weapons[new Random().nextInt(weapons.length)];
            currentMonster.setWeapon(monsterWeapon);
            
            
            String imagePath = currentMonster.getImagePath(); 
            ImageIcon rawIcon = new ImageIcon(BattleCraft.class.getResource(imagePath));
            Image scaled = rawIcon.getImage().getScaledInstance(250, 350, Image.SCALE_SMOOTH);
            lblCreeperName.setIcon(new ImageIcon(scaled)); // 이미지 교체됨!
            
            logArea.setText(logArea.getText() + "👾 " + currentMonster.getName() + " 등장! 무기: " + monsterWeapon.getName() + "\n");
            logArea.append(monsterWeapon.getName() + "의 내구도: " + monsterWeapon.getDurability()+"\n");
        } else { 
        	ImageIcon clearIcon = new ImageIcon(BattleCraft.class.getResource("/image/DeadCreeper.png"));
            Image clearImg = clearIcon.getImage().getScaledInstance(250, 350, Image.SCALE_SMOOTH);
            lblCreeperName.setIcon(new ImageIcon(clearImg));
            
            logArea.setText(logArea.getText() + "🎉 모든 몬스터 처치 완료!\n");
            disableAllButtons();
        }
    }

    private void steveAttack() {
        if (!isSteveTurn) return;
            steve.attack(currentMonster);
            logArea.setText(logArea.getText() + "⚔ 스티브가 공격! 사용한 무기: "+steve.getWeapon().getName()+"\n");
            logArea.append(steve.getWeapon().getName() + "의 내구도: " + steve.getWeapon().getDurability()+"\n");

            if (currentMonster.getHp() <= 0) {
                logArea.setText(logArea.getText() + currentMonster.getName() + " 격파!\n\n");
                loadNextMonster();
                updateStatusLabels();
                return;
            }

            // 공격 후 몬스터 반격 예약
            isSteveTurn = false;
            new javax.swing.Timer(500, e -> {
                ((javax.swing.Timer)e.getSource()).stop();  // 타이머 멈추고
                monsterAttack();
            }).start();
    }

        private void monsterAttack() {
        	
        	if (steve.isAngelCardActive()) {
                logArea.setText(logArea.getText() + "🛡️ 천사카드 발동! " + currentMonster.getName() + "의 공격이 무효화됨!\n");
                steve.deactivateAngelCard(); // 상태 해제
            } else {
                currentMonster.attack(steve);
                logArea.setText(logArea.getText() + currentMonster.getName() + "의 반격!\n ");
            }
        	
            updateStatusLabels(); 

            if (steve.getHp() <= 0) {
                logArea.setText(logArea.getText() + "\n 💀 스티브 사망. 게임 오버\n");
                
                ImageIcon deadIcon = new ImageIcon(BattleCraft.class.getResource("/image/DeadSteve.png"));
                Image deadImg = deadIcon.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
                lblSteveName.setIcon(new ImageIcon(deadImg));
                disableAllButtons();
                return;
            }

            updateStatusLabels();
            isSteveTurn = true;
        }

    private void updateStatusLabels() {    	
    	if (steve.getWeapon() != null) {
            lblWeapon.setText("무기: " + steve.getWeapon().getName());
    	}
        else
            lblWeapon.setText("무기: 없음"); 
    	
        lblSteveHp.setText("HP: " + Math.max(0, steve.getHp()));
        lblMonsterHp.setText("HP: " + Math.max(0, currentMonster.getHp()));
        barSteveHp.setValue(steve.getHp());
        barMonsterHp.setValue(currentMonster.getHp());
    }

    private void disableAllButtons() {
        btnSword.setEnabled(false);
        btnBow.setEnabled(false);
        btnBomb.setEnabled(false);
        btnPoison.setEnabled(false);
        btnSleep.setEnabled(false);
        btnAngelCard.setEnabled(false);
    }
}