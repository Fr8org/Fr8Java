package co.fr8.data.constants;

/**
 * Enum that defines Manifest Types
 */
public enum MT {

    FieldDescription("Field Description", 3),
    EventOrIncidentReport("Dockyard Terminal Event or Incident Report", 2),
    StandardPayloadKeys("Standard Payload Keys", 4),
    StandardPayloadData("Standard Payload Data", 5),
    StandardConfigurationControls("Standard UI Controls", 6),
    StandardEventReport("Standard Event Report", 7),
    StandardEventSubscription("Standard Event Subscription", 8),
    StandardTableData("Standard Table Data", 9),
    StandardFileHandle("Standard File Handle", 10),
    StandardRoutingDirective("Standard Routing Directive", 11),
    StandardAuthentication("Standard Authentication", 12),
    StandardLoggingCrate("Standard Logging Crate", 13),
    LoggingData("Logging Data", 10013),
    DocuSignEvent("Docusign Event", 14),
    DocuSignEnvelope("Docusign Envelope", 15),
    StandardSecurityCrate("Standard Security Crate", 16),
    StandardQueryCrate("Standard Query Crate", 17),
    StandardEmailMessage("Standard Email Message", 18),
    StandardFr8Plans("Standard Fr8 Plans", 19),
    StandardFr8Hubs("Standard Fr8 Hubs", 20),
    StandardFr8Containers("Standard Fr8 Containers", 21),
    StandardParsingRecord("Standard Parsing Record", 22),
    StandardFr8Terminal("Standard Fr8 Terminal", 23),
    StandardFileList("Standard File List", 24),
    StandardAccountTransaction("Standard Accounting Transaction", 25),
    DocuSignRecipient("Docusign Recipient", 26),
    OperationalStatus("Operational State", 27),
    DocuSignTemplate("Docusign Template", 28),
    ChartOfAccounts("Chart Of Accounts", 29),
    ManifestDescription("Manifest Description", 30),
    CrateDescription("Crate Description", 32),
    TypedFields("Typed Fields", 33),
    ExternalObjectHandles("External Object Handles", 34),
    SalesForceEvent("Salesforce Event", 35),
    DocuSignEnvelope_v2("Docusign Envelope v2", 36),
    StandardBusinessFact("Standard Business Fact", 37),
    UNKNOWN(null, 0),
    ANY(null, Integer.MIN_VALUE);

    private final int id;
    private final String name;

    /**
     * Constructor
     * @param id the id of the MT
     * @param name the name of the MT
     */
    MT(String name, int id) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter for the id property
     * @return the int value of the id property
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the name property
     * @return the string value of the name property
     */
    public String getName() {
        return name;
    }
}
