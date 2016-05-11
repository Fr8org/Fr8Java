package co.fr8.data.crates;

import co.fr8.data.constants.MT;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for use with CrateManifestType objects
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CrateManifestTypeAnnotation {
    public MT manifestType() default MT.UNKNOWN;
}
