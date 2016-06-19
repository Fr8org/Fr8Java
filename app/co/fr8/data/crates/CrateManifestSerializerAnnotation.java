package co.fr8.data.crates;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO: Implement
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CrateManifestSerializerAnnotation {
//  public IManifestSerializer serializer() default new DefaultSerializer(null);
}
