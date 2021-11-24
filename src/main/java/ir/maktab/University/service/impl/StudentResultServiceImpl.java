package ir.maktab.University.service.impl;

import ir.maktab.University.entities.QuestionStatus;
import ir.maktab.University.entities.StudentResult;
import ir.maktab.University.entities.dto.extra.StudentAnswersDTO;
import ir.maktab.University.repository.StudentResultRepository;
import ir.maktab.University.service.StudentResultService;
import ir.maktab.University.util.Security;
import org.springframework.stereotype.Service;

@Service
public class StudentResultServiceImpl extends BaseServiceImpl<StudentResult,Long, StudentResultRepository>
        implements StudentResultService {


    public StudentResultServiceImpl(StudentResultRepository repository) {
        super(repository);
    }

    @Override
    public StudentResult saveStudentAnswers(QuestionStatus questionStatus, StudentAnswersDTO studentAnswersDTO) {
        StudentResult studentResult = new StudentResult();
        studentResult.setStudent(Security.getStudent());
        studentResult.setStudentAnswer(studentAnswersDTO.getAnswer());
        if(questionStatus.getQuestionHeader().getMultipleChoices() != null){
            if(questionStatus.getQuestionHeader().getMultipleChoices().getCorrectAnswer().equals(studentAnswersDTO.getAnswer())){
                studentResult.setStudentPoint(questionStatus.getQuestionPoint());
            }else{
                studentResult.setStudentPoint(0);
            }
        }
        return save(studentResult);
    }
}
