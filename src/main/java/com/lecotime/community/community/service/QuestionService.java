package com.lecotime.community.community.service;

import com.lecotime.community.community.dto.QuestionDTO;
import com.lecotime.community.community.mapper.QuestionMapper;
import com.lecotime.community.community.mapper.UserMapper;
import com.lecotime.community.community.model.Question;
import com.lecotime.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        ArrayList<QuestionDTO> questionDTOs = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }
        return questionDTOs;
    }
}
