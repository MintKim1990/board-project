package com.fastcampus.board.repository;

import com.fastcampus.board.config.JpaConfig;
import com.fastcampus.board.domain.Article;
import com.fastcampus.board.domain.ArticleComment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class) // DataJpaTest 로는 Auditing 기능이 동작하지않아 해당 기능이 설정된 Config 클래스를 Import
@DataJpaTest
class JpaRepositoryTest {

    private final EntityManager entityManager;
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired EntityManager entityManager,
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.entityManager = entityManager;
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        // Given
        Article article = Article.of(null, "title", "content", "spring");
        articleRepository.save(article);

        // when
        List<Article> articles = articleRepository.findAll();

        // then
        Assertions.assertThat(articles).isNotNull().hasSize(1);
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        // Given
        Article article = Article.of(null, "title", "content", "spring");
        articleRepository.save(article);

        // when
        article.setHashtag("springboot");
        articleRepository.flush();

        // then
        Assertions.assertThat(article.getHashtag()).isEqualTo("springboot");
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        // Given

        // when

        // then
        Assertions.assertThat(articleRepository.findAll()).isNotNull().hasSize(0);
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        // Given
        Article article = Article.of(null, "title", "content", "spring");
        articleRepository.save(article);

        ArticleComment articleComment = ArticleComment.of(article, null, "content");
        articleCommentRepository.save(articleComment);

        entityManager.flush();
        entityManager.clear();

        Article findArticle = articleRepository.findArticleByTitle(article.getTitle());
        articleRepository.delete(findArticle);
        articleRepository.flush();

        Assertions.assertThat(articleRepository.findAll()).hasSize(0);
    }
}