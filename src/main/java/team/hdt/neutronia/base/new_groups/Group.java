package team.hdt.neutronia.base.new_groups;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Group {

    String name();

    String icon();

    String version() default "0.0.1";

    String description() default "";

    boolean enabled() default true;

    boolean enabledByDefault() default true;

}
