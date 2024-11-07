# Apero Tutorial SDK
The Apero Tutorial SDK is a simple and convenient SDK that helps you shorten development time and minimize errors when implement the Tutorial flow.

The SDK handles everything, including the ads script, remote config and the language of the Tutorial flow. So, you don’t need to do anything except setup configurations (see details: [Config SDK](#config-sdk)).

The SDK provides various templates. Make sure to use the template that matched the requirements. Please try the sample and read the README of the corresponding template before starting.

## Requirement
- Using Apero Module Ads ver 7.x.x or higher. See more: [Apero SDK](https://github.com/Apero-Partner/Apero-Sample-Module-Ads-V2)
- Integrate Firebase Remote Config

## Import Module
In the build.gradle (app)
~~~
implementation 'apero-inhouse:first-open-vsl:$required-version'
implementation 'com.tbuonomo:dotsindicator:4.3'
~~~
**Notes:** Using the specified Tutorial SDK version

## Config SDK
The SDK provides different tutorial templates. Each template has its own configuration, see details below:

**Template 1** (version 1.x.x): see detail: [READE-TEMPLATE-1.md](README-TEMPLATE-1.md)

**Template 2** (version 2.x.x): see detail: [READE-TEMPLATE-2.md](README-TEMPLATE-2.md)

**Template 3** (version 3.x.x): see detail: [READE-TEMPLATE-3.md](README-TEMPLATE-3.md)

## Troubleshoot
We have a [FAQ](https://docs.google.com/spreadsheets/d/17D8XldNymsDUSwAl7T0ZjKEZnAphKVbV1PL9GieH0TY/edit?usp=sharing) that summarizes common problems encountered during development. If you have a problem, look it up in the FAQ first. When you can't find a solution, create an issue and we will support you.
