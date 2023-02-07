package nl.ordina.spart.controller.mapper.sportcategory;

import org.mapstruct.Qualifier;

import java.lang.annotation.*;

@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface SportWithoutSportCategories {
}
