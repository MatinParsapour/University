package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Questions;
import ir.maktab.University.entities.StudentsGrade;
import ir.maktab.University.repository.QuestionsRepository;
import ir.maktab.University.repository.StudentsGradeRepository;
import ir.maktab.University.service.StudentsGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentsGradeServiceImpl extends BaseServiceImpl<StudentsGrade,Long, StudentsGradeRepository>
        implements StudentsGradeService {

    private final StudentsGradeRepository studentsGradeRepository;

    @Autowired
    public StudentsGradeServiceImpl(StudentsGradeRepository studentsGradeRepository) {
        super(studentsGradeRepository);
        this.studentsGradeRepository = studentsGradeRepository;
    }
}
