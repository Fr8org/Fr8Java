package co.fr8.data.crates;

import co.fr8.data.crates.serializers.DefaultSerializer;
import co.fr8.data.crates.serializers.IManifestSerializer;

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
