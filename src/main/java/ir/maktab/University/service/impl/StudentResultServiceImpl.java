package ir.maktab.University.service.impl;

import ir.maktab.University.entities.QuestionStatus;
import ir.maktab.University.entities.StudentResult;
import ir.maktab.University.entities.dto.extra.StudentAnswersDTO;
import ir.maktab.University.repository.StudentResultRepository;
import ir.maktab.University.service.StudentResultService;
import ir.maktab.University.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StudentResultServiceImpl extends BaseServiceImpl<StudentResult,Long, StudentResultRepository>
        implements StudentResultService {

    private final StudentResultRepository studentResultRepository;


    @Autowired
    public StudentResultServiceImpl(StudentResultRepository repository, StudentResultRepository studentResultRepository) {
        super(repository);
        this.studentResultRepository = studentResultRepository;
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

    @Override
    public String checkStudent() {
        Set<StudentResult> studentResult = studentResultRepository.findByStudentId(Security.getStudent().getId());
        if(studentResult != null){
            return "شما یکبار آزمون داده اید";
        }
        return null;
    }

    @Override
    public void changeStudentResultGrade(long studentResultId, double grade) {
        StudentResult studentResult = findById(studentResultId).get();
        studentResult.setStudentPoint(grade);
        save(studentResult);
    }
}
