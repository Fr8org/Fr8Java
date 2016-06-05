package co.fr8.data.interfaces.manifests;

import co.fr8.data.constants.MT;
import co.fr8.data.crates.CrateManifestType;
import co.fr8.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class that represents the Manifest object
 */
abstract public class Manifest {

    private static Map<Class, String[]> PkCache = new HashMap<>();

    private CrateManifestType crateManifestType;


    /**
     * Constructor that sets the crateManifestType property
     * @param crateManifestType the crateManifestType for the Manifest object
     */
    public Manifest(CrateManifestType crateManifestType) {
        this.crateManifestType = crateManifestType;
    }

    /**
     * Constructor that sets the crateManifestType by resolving the
     * CrateManifestType from the MT enum
     * @param manifestType the MT object used to resolve the CrateManifestType
     */
    protected Manifest(MT manifestType) {
        this(manifestType.getId(), manifestType.getFriendlyName());
    }

    /**
     * Constructor that sets the crateManifestType property using id and name
     * proeprties to create a new CrateManifestType
     * @param id the id of the CrateManfiestType
     * @param name the name of the CrateManifestType
     */
    protected Manifest(int id, String name) {
        this(new CrateManifestType(name, id));
    }

    /**
     * Getter method that returns the String array associated with the subclass
     * that extends this Manifest class
     * @return an array of String objects
     */
    public String[] getPrimaryKey() {
        return getPrimaryKey(this.getClass());
    }

    /**
     * Internal method that access the PkCache object to retrieve the array of
     * String objects that is associated with the value of the key parameter
     * @param key an object of type Class that is used as the key in the
     *            PkCache object
     * @return an array of String objects
     */
    private static String[] getPrimaryKey(Class key) {
        synchronized (PkCache) {
            String[] pk;

            if(CollectionUtils.isEmpty(pk = PkCache.get(key))) {
                Field[] fields = key.getFields();
                String[] pkList = new String[fields.length];

                for (int i = 0; i < fields.length; i++) {
                    pkList[i] = fields[i].getName();
                }
                PkCache.put(key, pkList);
            }
            return pk;
        }
    }

    public CrateManifestType getCrateManifestType() {
        return crateManifestType;
    }

}
