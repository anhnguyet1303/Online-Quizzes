package com.swp.online_quizz.Service;

import com.swp.online_quizz.Entity.Quiz;
import com.swp.online_quizz.Entity.Subject;
import com.swp.online_quizz.Repository.QuizzesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor

public class QuizzesServiceElmpl implements QuizzesService{
    @Autowired
    private final QuizzesRepository quizzesRepository;
    @Override
    public List<Quiz> getAll() {
        return quizzesRepository.findAll();
    }

    @Override
    public boolean create(Quiz quizzes) {
        return false;
    }

    @Override
    public Subject find(Integer quizId) {
        return null;
    }

    @Override
    public Boolean update(Quiz quizzes) {
        return null;
    }

    @Override
    public Boolean delete(Integer quizId) {
        return null;
    }

    @Override
    public List<Quiz> searchQuizzes(String keyword) {
        return quizzesRepository.findByKeywordContainingIgnoreCase(keyword);
    }

    @Override
    public Page<Quiz> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1,3);
        return this.quizzesRepository.findAll(pageable);
    }

    @Override
    public Page<Quiz> searchQuizzes(String keyword, Integer pageNo) {
        List list = this.searchQuizzes(keyword);
        Pageable pageable = PageRequest.of(pageNo - 1,3);
        Integer start = (int)pageable.getOffset();
        Integer end =(int)((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size() : pageable.getOffset() + pageable.getPageSize());
        list = list.subList(start,end);
        return new PageImpl<Quiz>(list,pageable,this.searchQuizzes(keyword).size());
    }

}
