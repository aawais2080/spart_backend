package nl.ordina.spart.controller.mapper.spartan;

import org.mapstruct.Qualifier;

import java.lang.annotation.*;

@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface SportWithoutSpartans {
}
