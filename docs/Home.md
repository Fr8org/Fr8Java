# Fr8 Java Terminal
> Basics


![](/docs/images/java_terminal_application_layout.png)

Above image shows the Application layout of Java Terminal.

Like in a vanilla Play App, our main configuration files are application.conf and routes located under the conf folder. Study these first.

| Folder | Explanation |
|---|---|
| co.fr8  | Enough of the Fr8 main functionality core that our Java Terminal is mapped here. You may not even need to implement anything here.|
| controllers | Routes file routes incoming requests to here |
| github | Github activities and any service that they need are implemented here. If you are integrating another Cloud based service, you probably will create another folder like this.|
| views | This folder is needed for play applications. |
| test  | Our Unit tests are located there. |

You probably won't need to work on the other folders.


Please study our Github use case to learn how we created it. This may help you create your own Cloud integrations.

[Github use case](/docs/Github_use_case.md)
