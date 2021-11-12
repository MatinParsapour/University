package ir.maktab.University.service.impl;

import ir.maktab.University.entities.MultipleChoices;
import ir.maktab.University.repository.MultipleChoicesRepository;
import ir.maktab.University.service.MultipleChoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultipleChoicesServiceImpl extends BaseServiceImpl<MultipleChoices,Long, MultipleChoicesRepository>
        implements MultipleChoicesService {

    private final MultipleChoicesRepository multipleChoicesRepository;

    @Autowired
    public MultipleChoicesServiceImpl(MultipleChoicesRepository multipleChoicesRepository) {
        super(multipleChoicesRepository);
        this.multipleChoicesRepository = multipleChoicesRepository;
    }
}
