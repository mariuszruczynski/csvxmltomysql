package csvxmltomysql;

public class Customer {

    private Integer id;
    private String name;
    private String surname;
    private String age;
    private String city;

    public Customer() {
    }

    public Customer(Integer id, String name, String surname, String age, String city) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.city = city;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }
}
