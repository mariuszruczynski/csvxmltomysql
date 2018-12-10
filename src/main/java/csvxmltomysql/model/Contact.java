package csvxmltomysql.model;

public class Contact {

    private int id;
    private int customerId;
    private int type;
    private String contact;

    public Contact(int id, int customerId, int type, String contact) {
        this.id = id;
        this.customerId = customerId;
        this.type = type;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
