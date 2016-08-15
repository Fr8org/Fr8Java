# Github Use Case

When a new Terminal is added to Fr8, first thing Fr8 does is to send a post to Terminal's host to discover any Activities in it.
Our routes file has the following line:
```
GET         /discover                           controllers.GitHubTerminalController.discover()
```

This makes our Play application to prepare a JSON response that explains what Activities are being hosted in our Terminal.

As you have learned how our application responds to requests from Fr8 Hub, let's get into more details.

Check [this file](/app/github/util/GitHubTerminalConstants.java) file (GitHubTerminalConstants.java). While this file may not look much, this is a very important file on how we create a Terminal and any Activities in it.
The discover request is initializing a run time version of our Terminal by initiating new Java objects by using the constants in this file. We later serialize these objects into JSON representation that we return to Fr8 hub.
It is very important that you study this file.

Second important file is [GithubTerminal](/app/github/GithubTerminal.java). This file initiates any Activities in our Terminal.

If you want to add a new Activity to Github Terminal, you can do so easily by working on these two files.
