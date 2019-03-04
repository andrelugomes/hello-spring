package springboot;

import com.example.configs.MyConfigs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMagicApplication {

	public static void main(String[] args) {
		final var run = SpringApplication.run(SpringBootMagicApplication.class, args);

		final var string = run.getBean("stringBeam", String.class);
		final var configs = run.getBean("myConfigs", MyConfigs.class);

		System.out.println(string);
		System.out.println(configs);
	}

}
