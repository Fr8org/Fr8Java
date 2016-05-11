package co.fr8.data.crates;

/**
 * Object representation of the CrateManifestType
 */
public class CrateManifestType {

    public static final CrateManifestType UNKNOWN = new CrateManifestType(null, 0);
    public static final CrateManifestType ANY = new CrateManifestType(null, Integer.MIN_VALUE);

    private final String type;
    private final int id;

    /**
     * Constructor for the CrateManifestType
     * @param type sets the type property
     * @param id sets the id property
     */
    public CrateManifestType(String type, int id) {
        this.type = type;
        this.id = id;
    }

    /**
     * Overrides the Object.toString() method
     * @return the concatenation of the type and ID properties
     */
    @Override
    public String toString() {
        return type + " (" + id + '}';
    }

    /**
     * Overrides the Object.equals() method
     * Compares the id property of the obj parameter with the ID of the the current object
     * @param obj the object whose id will be compared with this ID, should be of type CrateManifestType
     * @return true if the parameter name doj is an instance of CrateManifestType and the id properties are equal
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof CrateManifestType) && (((CrateManifestType)obj).getId() == this.id);
    }

    /**
     * Overrides the Object.hashCode() method
     * @return the id property of the current object
     */
    @Override
    public int hashCode() {
        return id;
    }

    /**
     * Getter method for the type property
     * @return the value of the type property
     */
    public String getType() {
        return type;
    }

    /**
     * Getter method for the id property
     * @return the value of the id proeprty
     */
    public int getId() {
        return id;
    }
}
