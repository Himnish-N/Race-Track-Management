package db_objs;

public class Admin {
    private final int admin_id;
    private final String admin_name;
    private final String admin_password;

    public Admin(int admin_id, String admin_name, String admin_password){
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.admin_password = admin_password;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public String getAdmin_password() {
        return admin_password;
    }
}
