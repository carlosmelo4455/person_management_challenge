package com.carlos.person_management_app.configs;

import com.carlos.person_management_app.business.entities.Person;
import com.carlos.person_management_app.dtos.person.PersonCreateDTO;
import com.carlos.person_management_app.dtos.person.PersonEditDTO;
import com.carlos.person_management_app.exceptions.ParseDataException;
import com.carlos.person_management_app.models.PersonModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        mapper.getConfiguration().setSkipNullEnabled(true);

        // Converter de PersonCreateDTO para Person
        Converter<String, Date> toDate = ctx -> {
            try {
                return new SimpleDateFormat("dd/MM/yyyy").parse(ctx.getSource());
            } catch (ParseException e) {
                try {
                    throw ParseDataException.create("Person", "birthDate");
                } catch (ParseDataException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        mapper.createTypeMap(PersonCreateDTO.class, Person.class)
                .addMappings(mapping -> mapping.using(toDate).map(PersonCreateDTO::getBirthDate, Person::setBirthDate));
        mapper.createTypeMap(PersonEditDTO.class, Person.class)
                .addMappings(mapping -> mapping.using(toDate).map(PersonEditDTO::getBirthDate, Person::setBirthDate));

        // Converter de PersonModel para Person
        Converter<Date, Date> toSameDate = MappingContext::getSource;
        mapper.createTypeMap(PersonModel.class, Person.class)
                .addMappings(mapping -> mapping.using(toSameDate).map(PersonModel::getBirthDate, Person::setBirthDate));

        return mapper;
    }
}