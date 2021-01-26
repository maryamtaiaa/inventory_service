package org.sid.inventorysevice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SpringBootApplication
public class InventorySeviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorySeviceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository,
			RepositoryRestConfiguration restConfiguration) {
		return args -> {
			restConfiguration.exposeIdsFor(Product.class);
			productRepository.save(new Product(null,"Ordinateur",1000,23));
			productRepository.save(new Product(null,"Imprimante",500,12));
			productRepository.save(new Product(null,"Smartphone",1500,10));
			productRepository.findAll().forEach(p->{
				System.out.println(p.getName());
			});
		};
	}
}

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
class Product{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
	private double quantity;
}

@RepositoryRestResource
interface ProductRepository extends JpaRepository<Product,Long>{
	
}