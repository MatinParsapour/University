package ir.maktab.University.service.impl;

import ir.maktab.University.entities.QuestionHeader;
import ir.maktab.University.entities.QuestionStatus;
import ir.maktab.University.entities.StudentResult;
import ir.maktab.University.entities.dto.extra.StudentAnswersDTO;
import ir.maktab.University.repository.QuestionStatusRepository;
import ir.maktab.University.service.QuestionStatusService;
import ir.maktab.University.service.StudentResultService;
import ir.maktab.University.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class QuestionStatusServiceImpl extends BaseServiceImpl<QuestionStatus, Long, QuestionStatusRepository>
        implements QuestionStatusService {

    private final QuestionStatusRepository questionStatusRepository;

    private final StudentResultService studentResultService;

    @Autowired
    public QuestionStatusServiceImpl(QuestionStatusRepository questionStatusRepository, StudentResultService studentResultService) {
        super(questionStatusRepository);
        this.questionStatusRepository = questionStatusRepository;
        this.studentResultService = studentResultService;
    }

    @Override
    public QuestionStatus createNewQuestionStatus(QuestionHeader questionHeader, double grade) {
        QuestionStatus questionStatus = new QuestionStatus();
        questionStatus.setQuestionHeader(questionHeader);
        questionStatus.setQuestionPoint(grade);
        return save(questionStatus);
    }

    @Override
    public void setResult(List<StudentAnswersDTO> studentAnswers) {
        for (StudentAnswersDTO studentAnswersDTO : studentAnswers) {
            QuestionStatus questionStatus = questionStatusRepository.findQuestionStatusByQuestionHeaderId(studentAnswersDTO.getId());
            StudentResult studentResult = studentResultService.saveStudentAnswers(questionStatus, studentAnswersDTO);
            questionStatus.getStudentResultList().add(studentResult);
            save(questionStatus);
        }
    }

    @Override
    public QuestionStatus getQuestionStatusByStudentResult(long studentResult) {
        return findById(studentResult).get();
    }
}
