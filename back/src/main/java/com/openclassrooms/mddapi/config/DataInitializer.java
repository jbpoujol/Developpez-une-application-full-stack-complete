package com.openclassrooms.mddapi.config;

import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Autowired
    private ThemeRepository themeRepository;

    @PostConstruct
    public void init() {
        if (themeRepository.count() == 0) {
            List<Theme> themes = Arrays.asList(
                    new Theme("Technology", "All about the latest technology trends and innovations."),
                    new Theme("Science", "Discoveries and discussions about the world of science."),
                    new Theme("Health", "Tips and news about health and wellness."),
                    new Theme("Sports", "Updates and stories from the world of sports."),
                    new Theme("Entertainment", "Movies, music, and entertainment news."),
                    new Theme("Travel", "Guides, tips, and stories from around the world.")
            );
            themeRepository.saveAll(themes);
        }
    }
}
