package co.fr8.terminal.bindings;

import java.util.List;

/**
 * Created by Charles Pretzer on 4/18/2016.
 */
public class Activity {
    private String label;
    private List<String> template;
    private String rootRouteNodeId;
    private String parentRouteNodeId;
    private String currentView;
    private int ordering;
    private String id;
    private List<String> crateStorage;
    private List<String> childrenActions;
    private String authToken;
}

/**
 {
 "label": "ConnectToSql"
 "template": {
 "name": "ConnectToSql",
 "version": "1",
 "terminal" : {
 "name" : "terminalFr8Core",
 "version" : "1",
 "endpoint" : "terminals.fr8.co:50705"
 }
 "tags": null,
 "category": "Processors",
 "type": "Standard",
 "minPaneWidth": 0,
 "needsAuthentication": false,
 },

 "rootRouteNodeId": null,
 "parentRouteNodeId": "e36ffaf8-124f-4f97-9564-fe795ce170e4",
 "currentView": null,
 "ordering": 1,
 "id": "45693293-14be-4cb1-8b98-c9d5f442fd80",
 "crateStorage": {
 "crates": [

 ]
 },
 "childrenActions": [],
 "authToken": null,
 },
 */