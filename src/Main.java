import Customer.Customer;
import Order.Order;
import Product.Product;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


class Main{




     public static void main(String[] args) {

         long i = 0;
         Faker faker = new Faker();
         HashSet <Product> elencoProdotti  = new HashSet <>();
         List<Product> prodottiFiltrati;


        while (i <= 250){
            String title = faker.book().title();
            String category = faker.commerce().department();
            Double price = Double.parseDouble(faker.commerce().price());
            Product prodotto = new Product(i,title,category,price);
            elencoProdotti.add(prodotto);
            i++;
        }

         while (i <= 250){
             String title = faker.book().title();
             String category = faker.commerce().department();
             Double price = Double.parseDouble(faker.commerce().price());
             Product prodotto = new Product(i,title,category,price);
             elencoProdotti.add(prodotto);
             i++;
         }


        List<Order> babyOrdini = new ArrayList<>();

         Long id = (long) i;
         String status = faker.options().option("Delivered", "Pending", "Shipped");
         LocalDate orderDate = LocalDate.now().minusDays(faker.number().numberBetween(1, 30));
         LocalDate deliveryDate = orderDate.plusDays(faker.number().numberBetween(1, 7));


         List<Product> prodottiOrdine = List.of(elencoProdotti.get(faker.number().numberBetween(0, elencoProdotti.size())));


         Customer cliente = elencoClienti.get(faker.number().numberBetween(0, elencoClienti.size()));

         Order ordine = new Order(id, status, orderDate, deliveryDate, prodottiOrdine, cliente);
         babyOrdini.add(ordine);
         i++;

         prodottiFiltrati = elencoProdotti.stream().filter(prodotto -> prodotto.getCategory().equals("Books")).filter(prodotto -> prodotto.getPrice() <= 100.0 ).toList();

        System.out.println("Esercizio 1. I prodotti con la categoria book e sotto il prezzo di 100.0 sono': ");

        for (Product prodotti : prodottiFiltrati){
            System.out.println("" + prodotti.getId() + " " + prodotti.getName() + " " + prodotti.getCategory() + " E." + prodotti.getPrice() );

        }




}
}