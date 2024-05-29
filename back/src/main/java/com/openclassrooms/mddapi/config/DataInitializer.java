package com.openclassrooms.mddapi.config;

import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.DBUserRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Configuration class for initializing the database with default data.
 * <p>
 * This class initializes the database with a set of predefined themes, articles, comments, and a user if the respective repositories are empty.
 */
@Configuration
public class DataInitializer {

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private DBUserRepository userRepository;

    /**
     * Initializes the database with default themes, articles, comments, and a user.
     * <p>
     * This method checks if the respective repositories are empty. If they are, predefined data is created and saved to the repositories.
     */
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

        if (userRepository.count() == 0) {
            DBUser user = new DBUser();
            user.setName("John Doe");
            user.setEmail("john.doe@example.com");
            user.setPassword("$2a$10$Dow1QPOxFzU1H93/N6kK8OXjewJqwrA8Q3.C5qYpF/07j/mfqv1Wu"); // Password: password
            userRepository.save(user);
        }

        if (articleRepository.count() == 0) {
            DBUser user = userRepository.findByEmail("john.doe@example.com").orElse(null);
            List<Theme> themes = themeRepository.findAll();
            if (user != null && !themes.isEmpty()) {
                Theme technology = themes.stream().filter(theme -> "Technology".equals(theme.getName())).findFirst().orElse(null);
                Theme science = themes.stream().filter(theme -> "Science".equals(theme.getName())).findFirst().orElse(null);

                if (technology != null && science != null) {
                    List<Article> articles = Arrays.asList(
                            new Article("Latest Tech Trends", "Content about technology.", LocalDateTime.now(), user, technology, null),
                            new Article("Scientific Discoveries", "Content about science.", LocalDateTime.now(), user, science, null)
                    );
                    articleRepository.saveAll(articles);
                }
            }
        }

        if (commentRepository.count() == 0) {
            List<Article> articles = articleRepository.findAll();
            if (!articles.isEmpty()) {
                Article techArticle = articles.stream().filter(article -> "Latest Tech Trends".equals(article.getTitle())).findFirst().orElse(null);
                Article scienceArticle = articles.stream().filter(article -> "Scientific Discoveries".equals(article.getTitle())).findFirst().orElse(null);

                if (techArticle != null && scienceArticle != null) {
                    List<Comment> comments = Arrays.asList(
                            new Comment("Great article on tech!", LocalDateTime.now(), techArticle.getAuthor(), techArticle),
                            new Comment("Very informative science content.", LocalDateTime.now(), scienceArticle.getAuthor(), scienceArticle)
                    );
                    commentRepository.saveAll(comments);
                }
            }
        }
    }
}
