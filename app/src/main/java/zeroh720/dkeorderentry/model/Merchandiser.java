package zeroh720.dkeorderentry.model;

public class Merchandiser{
    static Merchandiser instance;
    String name;
    String branch;

    public static Merchandiser getInstance(){
        if(instance == null)
            instance = new Merchandiser();
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
