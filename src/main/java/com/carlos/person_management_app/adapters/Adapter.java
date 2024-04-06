package com.carlos.person_management_app.adapters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Adapter {

    @Autowired
    private ModelMapper mapper;

    public <Source, Target> Target mapSourceToTarget(Source source, Class<Target> targetClass) {
        return mapper.map(source, targetClass);
    }

    public <Type> Type updateTargetWithSource(Type source, Type target) {
        mapper.map(source, target);
        return target;
    }
}