package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Grade;
import ir.maktab.University.entities.Questions;
import ir.maktab.University.repository.GradeRepository;
import ir.maktab.University.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl extends BaseServiceImpl<Grade,Long, GradeRepository>
        implements GradeService {

    private final GradeRepository gradeRepository;

    @Autowired
    public GradeServiceImpl(GradeRepository gradeRepository) {
        super(gradeRepository);
        this.gradeRepository = gradeRepository;
    }

    @Override
    public Grade createNewGrade(double point) {
        Grade grade = new Grade();
        grade.setQuestionPoint(point);
        Grade savedGrade = save(grade);
        return savedGrade;
    }
}
