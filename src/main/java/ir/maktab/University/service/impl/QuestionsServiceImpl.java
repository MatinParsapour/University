package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Options;
import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.entities.QuestionStatus;
import ir.maktab.University.entities.Questions;
import ir.maktab.University.entities.dto.extra.QuestionHeaderDTO;
import ir.maktab.University.repository.QuestionsRepository;
import ir.maktab.University.service.QuestionStatusService;
import ir.maktab.University.service.QuestionsService;
import ir.maktab.University.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuestionsServiceImpl extends BaseServiceImpl<Questions,Long, QuestionsRepository>
        implements QuestionsService {

    private final QuestionsRepository questionsRepository;

    private final QuestionStatusService questionStatusService;

    private final QuizService quizService;

    @Autowired
    public QuestionsServiceImpl(QuestionsRepository questionsRepository, QuestionStatusService questionStatusService, QuizService quizService) {
        super(questionsRepository);
        this.questionsRepository = questionsRepository;
        this.questionStatusService = questionStatusService;
        this.quizService = quizService;
    }

    @Override
    public void addToQuestions(QuestionHeader questionHeader, double grade) {
        Questions questions = new Questions();
        questions.setQuestionHeader(questionHeader);
        QuestionStatus newQuestionStatus = questionStatusService.createNewQuestionStatus(questionHeader, grade);
        questions.setQuestionStatus(newQuestionStatus);
        Questions savedQuestions = save(questions);
        quizService.addQuestions(savedQuestions);
    }

    @Override
    public Set<QuestionHeaderDTO> questionHeaders(Set<Questions> questions) {
        Set<QuestionHeaderDTO> questionHeaderDTOS = new HashSet<>();
        for(Questions question : questions){
            QuestionHeaderDTO questionHeaderDTO = new QuestionHeaderDTO();
            if(question.getQuestionHeader().getDescriptive() != null){
                questionHeaderDTO.setId(question.getQuestionHeader().getId());
                questionHeaderDTO.setHeader(question.getQuestionHeader().getDescriptive().getHeader());
                questionHeaderDTO.setType("Descriptive");
            }else{
                questionHeaderDTO.setId(question.getQuestionHeader().getId());
                questionHeaderDTO.setHeader(question.getQuestionHeader().getMultipleChoices().getHeader());
                for (Options option : question.getQuestionHeader().getMultipleChoices().getOptions()){
                    questionHeaderDTO.getOptions().add(option);
                }
                questionHeaderDTO.setType("MultipleChoices");
            }
            questionHeaderDTOS.add(questionHeaderDTO);
        }
        return questionHeaderDTOS;
    }
}
