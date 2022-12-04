package com.fastcampus.board.service;

import com.fastcampus.board.domain.type.SearchType;
import com.fastcampus.board.dto.ArticleDto;
import com.fastcampus.board.dto.ArticleUpdateDto;
import com.fastcampus.board.dto.ArticleWithCommentsDto;
import com.fastcampus.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        return Page.empty();
    }

    public ArticleWithCommentsDto getArticle(Long articleId) {
        return null;
    }

    @Transactional
    public void saveArticle(ArticleDto dto) {
    }

    @Transactional
    public void updateArticle(ArticleDto dto) {
    }

    @Transactional
    public void deleteArticle(Long articleId) {
    }

}
