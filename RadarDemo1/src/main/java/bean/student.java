package bean;

public class student {
    private int No;
    private String Name;
    private int MarkChinese;
    private int MarkMath;
    private int MarkEnglish;
    private int MarkPhysics;
    private int MarkChemistry;
    private int MarkBiology;

    @Override
    public String toString() {
        return "student{" +
                "No=" + No +
                ", Name='" + Name + '\'' +
                ", MarkChinese=" + MarkChinese +
                ", MarkMath=" + MarkMath +
                ", MarkEnglish=" + MarkEnglish +
                ", MarkPhysics=" + MarkPhysics +
                ", MarkChemistry=" + MarkChemistry +
                ", MarkBiology=" + MarkBiology +
                '}';
    }

    public int getNo() {
        return No;
    }

    public void setNo(int no) {
        No = no;
    }

    public int getMarkChinese() {
        return MarkChinese;
    }

    public void setMarkChinese(int markChinese) {
        MarkChinese = markChinese;
    }

    public int getMarkMath() {
        return MarkMath;
    }

    public void setMarkMath(int markMath) {
        MarkMath = markMath;
    }

    public int getMarkEnglish() {
        return MarkEnglish;
    }

    public void setMarkEnglish(int markEnglish) {
        MarkEnglish = markEnglish;
    }

    public int getMarkPhysics() {
        return MarkPhysics;
    }

    public void setMarkPhysics(int markPhysics) {
        MarkPhysics = markPhysics;
    }

    public int getMarkChemistry() {
        return MarkChemistry;
    }

    public void setMarkChemistry(int markChemistry) {
        MarkChemistry = markChemistry;
    }

    public int getMarkBiology() {
        return MarkBiology;
    }

    public void setMarkBiology(int markBiology) {
        MarkBiology = markBiology;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public student(int no, String name, int markChinese, int markMath, int markEnglish, int markPhysics, int markChemistry, int markBiology) {
        No = no;
        Name = name;
        MarkChinese = markChinese;
        MarkMath = markMath;
        MarkEnglish = markEnglish;
        MarkPhysics = markPhysics;
        MarkChemistry = markChemistry;
        MarkBiology = markBiology;
    }

    public student() {
    }
}
