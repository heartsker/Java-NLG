# Java-NLG

# User stories

As an amateur **content creator**,

I can **have a little chill while doing my work**

So I can **delegate a part of my work to computer**

---

As a **channel owner** I can **configure the system to autogenerate posts faster without my control** so I can **gain new followers**

---

As an artist I can ask the system to generate new ideas for my pictures to get the inspiration

# Project vision

## Overview

`medved` is a CLI java utility for short text generation in natural language manner

It could be used in a variety of ways, starting from generating posts to social networks and more

As a rule, text generators are used to create advertisements that will automatically appear on social networks (for example, on Facebook or VKontakte pages) or on websites

A short text generator is an ad creation tool that generates text in the form of an ad using a template

The template can contain data such as: product name, price, or a link to the website

## For stakeholders

Opportunity to use this utility with unlimited configuration access

No limit on runs

Service is not meant to be monetized by itself. Feel free to apply the product in any sphere of interest


## System scope

**What it is?**

The service is expected to be used to generate short coherent texts with the given prefix and using the given keywords

The generated texts shouldn’t be taken seriously into account

**What it is not?**

This is not a generator of a long text. The service is not guaranteed to have long context memory, so long texts generated via this service may not be meaningful

It's not a product placement platform. By the way the scope of application is limited only by your imagination

## Main question

- Why should the product exist?

    There are no similar products yet available. Our key feature is customization and easy-to-use interface

- Why would it be successful?

    We analyzed the market and noticed the strongly growing demand on neural networks and text generation models.

---

## Documentation

Sample usage:

```shell
$ medved --keywords 'usd', 'rub', 'investments' --prefix 'New budget rule'

> New budget rule 2.0 is comming soon. Do not miss a chance to ride the usd/rub currency pair today
```
