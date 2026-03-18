
import Customer.Customer;
import Order.Order;
import Product.Product;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


class Main {


    public static void main(String[] args) {

        List<String> listStatus = new ArrayList<>();


        listStatus.add("DELIVERED");
        listStatus.add("PENDING");
        listStatus.add("AWAITING");


        Faker faker = new Faker();
        List<Product> listProduct = new ArrayList<>();
        List<Customer> listCustomer = new ArrayList<>();
        List<Order> listOrder = new ArrayList<>();

        Random random = new Random();

        List<Product> prodottiFiltrati;
        List<Order> ordiniProdottiCatBaby;
        List<Order> ordiniProdottiCatBoys;
        List<Customer> clientiT2;


        for (long i = 0; i <= 250; i++) {
            String title = faker.book().title();
            String category = faker.commerce().department();
            Double price = Double.parseDouble(faker.commerce().price());
            Product prodotto = new Product(i, title, category, price);
            listProduct.add(prodotto);

        }
        for (long i = 0; i <= 50; i++) {
            Customer customer = new Customer();
            customer.setId(i);
            customer.setName(faker.name().fullName());
            customer.setTier(random.nextInt(1, 5));
            listCustomer.add(customer);
        }
        for (long i = 0; i <= 50; i++) {
            Order order = new Order();
            order.setId(i);
            order.setStatus(listStatus.get(random.nextInt(listStatus.size())));
            order.setCustomer(listCustomer.get(random.nextInt(listCustomer.size())));
            Date data = faker.date().between(new Date(121, 0, 1), new Date(122, 0, 1));
            LocalDate localData = LocalDate.from(data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            order.setOrderDate(localData);


            List<Product> orderProduct = new ArrayList<>();

            for (long j = 0; j <= 5; j++) {
                orderProduct.add(listProduct.get(random.nextInt(listProduct.size())));
            }

            order.setProducts(orderProduct);
            listOrder.add(order);
        }

        ordiniProdottiCatBaby = listOrder.stream()
                .filter(ordine -> {
                    Optional<Product> product = ordine.getProducts().stream()
                            .filter(prodotto -> prodotto.getCategory().equals("Baby"))
                            .findFirst();
                    return product.isPresent();
                }).toList();


        ordiniProdottiCatBaby.stream()
                .forEach(ordine -> System.out.println(ordine.toString()));

        ordiniProdottiCatBoys = listOrder.stream()
                .filter(ordine -> {
                    Optional<Product> product = ordine.getProducts().stream()
                            .filter(prodotto -> prodotto.getCategory().equals("Health"))
                            .findFirst();
                    return product.isPresent();
                }).map(ordine -> {
                    ordine.getProducts().stream().forEach(product -> {
                        System.out.println(product.getPrice());
                        product.setPrice(product.getPrice() * 0.9);
                        System.out.println(product.getPrice());
                    });
                    return ordine;
                }).toList();

        List<Order> orderClientiT2 = listOrder.stream()
                .filter(order -> order.getCustomer().getTier() == 2)
                .filter(order -> order.getOrderDate().isAfter(LocalDate.of(2021, 2, 1))
                        && order.getOrderDate().isBefore(LocalDate.of(2021, 4, 1))).toList();

        System.out.println("----------------DATA----------------");
        orderClientiT2.stream()
                .forEach(ordine -> System.out.println(ordine.toString()));

        System.out.println("----------------BOYS----------------");
        ordiniProdottiCatBoys.stream()
                .forEach(ordine -> System.out.println(ordine.toString()));


        prodottiFiltrati = listProduct.stream()
                .filter(prodotto -> prodotto.getCategory().equals("Books")).filter(prodotto -> prodotto.getPrice() <= 100.0).toList();

        System.out.println("Esercizio 1. I prodotti con la categoria book e sotto il prezzo di 100.0 sono': ");

        for (Product prodotti : prodottiFiltrati) {
            System.out.println("" + prodotti.getId() + " " + prodotti.getName() + " " + prodotti.getCategory() + " E." + prodotti.getPrice());

        }


    }
}