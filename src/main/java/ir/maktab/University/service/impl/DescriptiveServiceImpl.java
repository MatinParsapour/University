package ir.maktab.University.service.impl;

import ir.maktab.University.entities.Descriptive;
import ir.maktab.University.repository.DescriptiveRepository;
import ir.maktab.University.service.DescriptiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DescriptiveServiceImpl extends BaseServiceImpl<Descriptive,Long, DescriptiveRepository>
        implements DescriptiveService {

    private final DescriptiveRepository descriptiveRepository;

    @Autowired
    public DescriptiveServiceImpl(DescriptiveRepository descriptiveRepository) {
        super(descriptiveRepository);
        this.descriptiveRepository = descriptiveRepository;
    }
}
