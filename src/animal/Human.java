package animal;

public class Human extends Animal implements Thinkable {
    // フィールド（趣味）
    private String hobby;

    // コンストラクタ
    public Human() {
    }

    public Human(String name, int age, String hobby) {
        super.setName(name);
        super.setAge(age);
        this.hobby = hobby;
    }

    public void think() {
        System.out.println("私は" + this.hobby + "について考えています。");
    }

}
