package Honeycloud.honey;

import Honeycloud.honey.entity.Honey;
import Honeycloud.honey.repository.HoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@SpringBootApplication
public class HoneyApplication implements WebMvcConfigurer {

	@Autowired
	private ThymeleafProperties properties;

	@Value("${spring.thymeleaf.templates_root:}")
	private String templatesRoot;

	public static void main(String[] args) {
		SpringApplication.run(HoneyApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/").setViewName("home");
	}

	@Bean
	public ITemplateResolver defaultTemplateResolver() {
		FileTemplateResolver resolver = new FileTemplateResolver();
		resolver.setSuffix(properties.getSuffix());
		resolver.setPrefix(templatesRoot);
		resolver.setTemplateMode(properties.getMode());
		resolver.setCacheable(properties.isCache());
		return resolver;
	}

	@Bean
	public CommandLineRunner dataLoader(HoneyRepository repo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				repo.save(new Honey("GRPP", "Гречичный мед", 300, Honey.Weights.KILO));
				repo.save(new Honey("LPVR", "Липовый мед", 450,Honey.Weights.KILO));
				repo.save(new Honey("GOPP", "Горный мед", 600, Honey.Weights.KILO));
				repo.save(new Honey("BLVR", "Белый мед", 500,Honey.Weights.KILO));
			}
		};
	}


}
