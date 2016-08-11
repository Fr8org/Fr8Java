![Fr8 Logo](https://github.com/Fr8org/Fr8Core/blob/master/Docs/img/Fr8Logo.png)

# Fr8 Java SDK and Terminals


[![NPM Version][npm-image]][npm-url]
[![Build Status][travis-image]][travis-url]
[![Downloads Stats][npm-downloads]][npm-url]

These Java-based Fr8 Terminals work with the [Fr8 ecosystem](http://www.fr8.co). Fr8 is an open source cloud based integration platform ([iPaaS](https://en.wikipedia.org/wiki/Cloud-based_integration)).

Fr8 Terminals communicate with Fr8 Hubs using RESTful endpoints, Http, and JSON. The SDK portion of this repository provides tools to make it easier for Terminals to be created and to keep things DRY. For example, there are object mapper classes that allow Java developers to avoid having to personally deal with the serialization and deserialization of POJO into JSON.

The Java SDK services are in an early stage of development. (To get a sense of the roadmap, you may want to look at the more mature [.NET SDK](https://github.com/Fr8org/Fr8Core/blob/master/Docs/ForDevelopers/SDK/.NET/Home.md))


Currently, this repository contains a single Terminal, the Fr8 Github Terminal. This Terminal exposes useful Github services to Fr8 Plans via a set of activities like Get Issue.

Join the discussion here in the Issues list and in the #fr8dev-java channel on the [public Fr8 Slack Team](http://slack.fr8.co).


## Developing a Fr8 Terminal


1) You only need to clone this repo if you want to build or modify Fr8 Terminals written in Java. Before doing so, make sure you're familiar with Fr8 as an end user by building Fr8 Plans at the [main Fr8 website](http://fr8.co).

2) Read the [development guides](https://github.com/Fr8org/Fr8Core/blob/master/Docs/ForDevelopers/DevGuideHome.md), especially the [Terminal Development Guide](https://github.com/Fr8org/Fr8Core/blob/master/Docs/ForDevelopers/DevelopmentGuides/TerminalDevelopmentGuide.md).

3) Clone this repo. You'll want to be familiar with the  [Play Framework](https://www.playframework.com). 

4) Choose a [development approach](https://github.com/Fr8org/Fr8Core/blob/master/Docs/ForDevelopers/DevelopmentGuides/ChoosingADevelopmentApproach.md).  The Fr8 service at fr8.co uses Heroku to deploy production and dev Java Terminals.  




6) Setup your dev environment. These environment variables are needed. (Heroku example shown)

| CORE_WEBSERVER_URL: | http://dev.fr8.co |
|---|---|
| TERMINAL_HOST: | http://fr8java.herokuapp.com |

Additional Resources
====================
[Overview of this project](/docs/Home.md).

[Walkthrough of the Github Terminal](/docs/Github_use_case.md)

