import java.io.Serializable;
import java.util.ArrayList;
abstract class User implements Serializable{
    private String uName;
    private String uPwd;
    private String fullName;
    private boolean isMember;
    private int fine = 0;

    public ArrayList<Product> boughtProducts = new ArrayList<Product>();
    public ArrayList<Product> temp = new ArrayList<Product>();

    public User(String uName,String uPwd,String fullName,boolean isMember){
        this.uName=uName;
        this.uPwd=uPwd;
        this.fullName=fullName;
        this.isMember=isMember;
    }

    public String getuName(){
        return uName;
    }

    public String getuPwd(){
        return uPwd;
    }

    public String getFullName(){
        return fullName;
    }

    public void setPassword(String pwd){
        uPwd=pwd;
    }

    public boolean getMember() {
        return isMember;
    }

    public void setFine(int fine) {
        this.fine += fine;
    }

    public int getFine() {
        return fine;
    }
}
