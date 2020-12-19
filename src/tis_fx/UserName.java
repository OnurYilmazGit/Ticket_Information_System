package tis_fx;

public final class UserName {
    private String username;
    private static UserName INSTANCE = null;
   
    public static UserName getInstance(){
        if(INSTANCE == null){
            INSTANCE = new UserName();
        }
        return INSTANCE;
    }
    private UserName(){
    }
    
    public void setUser(String u) {
    this.username = u;
  }
  
  public String getUser() {
    return this.username;
  }
    
}
