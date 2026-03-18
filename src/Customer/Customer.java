package Customer;

public class Customer {

    Long id;
    String name;
    int tier;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTier() {
        return tier;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public Customer(Long id, String name, int tier) {
        this.id = id;
        this.name = name;
        this.tier = tier;

    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }

    public Customer() {

    }
}
