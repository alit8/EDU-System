package edusystem;

public class course {
	
    private String id;
    private String name;
    private String examDate;
    private String classTime;
    private int credits;
    private String grade;
    private String condition;

    public course(String id, String name, String examDate, String classTime, int credits) {
        this.id = id;
        this.name = name;
        this.examDate = examDate;
        this.classTime = classTime;
        this.credits = credits;
    }
    
    public course(String id, String name, String examDate, String classTime, int credits, String grade, String condition) {
        this.id = id;
        this.name = name;
        this.examDate = examDate;
        this.classTime = classTime;
        this.credits = credits;
        this.grade = grade;
        this.condition = condition;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExamDate() {
        return examDate;
    }

    public String getClassTime() {
        return classTime;
    }

    public int getCredits() {
        return credits;
    }

    public String getGrade() {
        return grade;
    }

    public String getCondition() {
        return condition;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

}