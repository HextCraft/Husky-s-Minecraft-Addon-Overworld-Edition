package team.hdt.neutronia.base.groups;

public @interface ComponentRegistry {

    String name();

    String iconStack();

    String description() default "";

}
