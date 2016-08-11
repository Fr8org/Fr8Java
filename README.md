# Fr8 Java Terminal
> Fr8's Java terminal including Github Activities

[![NPM Version][npm-image]][npm-url]
[![Build Status][travis-image]][travis-url]
[![Downloads Stats][npm-downloads]][npm-url]

Fr8 is an open source cloud based integration platform ([IPAAS](https://en.wikipedia.org/wiki/Cloud-based_integration)).

* Fr8 core is written in .NET.
* Fr8 uses JSON extensively.
* This helps other language developers to easily integrate their open source implementations with Fr8.
* Java Terminal holds Object representations that gets serialized into JSON.
* In turn, Fr8 is able to use this Java Terminal.
* This Terminal holds some of the Core functionality.
* It also holds Github Activities that are already available in the PB (Plan Builder) of Fr8.

You can join in on the development of this terminal or you can extend this repo to create your own Terminals.



**Go on reading and learn how you can easily create your own Cloud integrations with Fr8.**

## Installation

 - Start by cloning this repo.
 - Java Terminal is based on [Play Framework](https://www.playframework.com). It is a good idea to learn the basics of Play Framework.
 - This terminal is hosted on Heroku. While you can also use Heroku, you can host your terminal anywhere and integrate it with Fr8.
 - Fr8 Development server is [here](http://dev.fr8.co). Create an account and play with it to get a sense of Fr8. You should use this server first to develop and finalize your Fr8 Java implementation.
 - Fr8 Production server is [here](http://fr8.co). After your work is complete on the Dev server, you submit a request to Fr8's admins to move your implementation to Production server.
 

## Documentation

* Fr8 Core functionality is explained extensively [here](https://github.com/Fr8org/Fr8Core/blob/master/Docs/ForDevelopers/SDK/.NET/Home.md).
* It is a good idea to learn a little about Fr8 architecture in the link above. 
* You can also jump directly to developing your Java Activities by clicking [here](/docs/Home.md).

## Development setup

For using this Terminal, these environment variables are needed in Heroku:

| CORE_WEBSERVER_URL: | http://dev.fr8.co |
|---|---|
| TERMINAL_HOST: | http://fr8java.herokuapp.com |

If you deploy your app on any other server, make appropriate changes.

